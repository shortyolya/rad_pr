package com.baltinfo.radius.documents.generator;


import com.baltinfo.radius.db.controller.MapRegionController;
import com.baltinfo.radius.db.model.MapRegion;
import com.baltinfo.radius.documents.image.ImageScale;
import com.baltinfo.radius.yandex.client.YandexMapApiClient;
import org.apache.commons.io.FileUtils;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.dml.CTHyperlink;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;
import org.docx4j.org.xhtmlrenderer.util.XRLog;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.imgscalr.Scalr;
import org.jvnet.jaxb2_commons.ppp.Child;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author css
 */
public class Docx4jUtils {

    static {
        XRLog.setLevel(XRLog.CONFIG, Level.SEVERE);
        XRLog.setLevel(XRLog.EXCEPTION, Level.SEVERE);
        XRLog.setLevel(XRLog.GENERAL, Level.SEVERE);
        XRLog.setLevel(XRLog.INIT, Level.SEVERE);
        XRLog.setLevel(XRLog.JUNIT, Level.SEVERE);
        XRLog.setLevel(XRLog.LOAD, Level.SEVERE);
        XRLog.setLevel(XRLog.MATCH, Level.SEVERE);
        XRLog.setLevel(XRLog.CASCADE, Level.SEVERE);
        XRLog.setLevel(XRLog.XML_ENTITIES, Level.SEVERE);
        XRLog.setLevel(XRLog.CSS_PARSE, Level.SEVERE);
        XRLog.setLevel(XRLog.LAYOUT, Level.SEVERE);
        XRLog.setLevel(XRLog.RENDER, Level.SEVERE);

    }

    private static Logger logger = LoggerFactory.getLogger(Docx4jUtils.class);
    private final YandexMapApiClient yandexMapApiClient;
    private final MapRegionController mapRegionController;
    private static final String OBJ_CODE = "obj_code";

    public Docx4jUtils(YandexMapApiClient yandexMapApiClient, MapRegionController mapRegionController) {
        this.yandexMapApiClient = yandexMapApiClient;
        this.mapRegionController = mapRegionController;
    }

    public DocumentResult createReport(Template template) throws Docx4JException, IOException, DocxGeneratorException {

        WordprocessingMLPackage docx = WordprocessingMLPackage.load(template.getIs());
        List<String> invalidObjectList = new ArrayList<>();
        HeaderPart headerPart = getHeaderPart(docx);

        for (SubReportInfo subreportinfo : template.getSubreports()) {
            if (template.isMultiple()) {
                fillSubReport(subreportinfo, docx.getMainDocumentPart().getContent(),
                        template.getHtmlCorrection(), template.getImgFilePath(), docx, template, invalidObjectList);
            } else {
                fillSubReport(subreportinfo, docx.getMainDocumentPart().getContent(),
                        template.getHtmlCorrection(), template.getImgFilePath(), docx, invalidObjectList);
            }
        }

        if (!template.isMultiple()) {
            List<Object> texts = getAllElementFromObject(docx.getMainDocumentPart(), Text.class);
            texts.addAll(getAllElementFromObject(headerPart, Text.class));
            searchAndReplaceTags(texts, template.getInfo().get(0), template.getHtmlCorrection(), template.getImgFilePath(), docx, invalidObjectList);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        docx.save(out);
        out.close();
        List<String> invalidObjects = new ArrayList<>(invalidObjectList);
        invalidObjectList.clear();
        return new DocumentResult(out.toByteArray(), invalidObjects);
    }

    private void fillSubReport(SubReportInfo subreportinfo,
                               List<Object> content,
                               Map<String, String> corrections,
                               String imgFilePath,
                               WordprocessingMLPackage docx,
                               Template template, List<String> invalidObjectList) throws DocxGeneratorException, Docx4JException, UnsupportedEncodingException {

        int beg = -1;
        int end = -1;
        String subReportTag = subreportinfo.getName().trim().toLowerCase();
        for (int i = 0; i < content.size(); i++) {
            Object prow = content.get(i);
            if (prow instanceof P) {
                if (prow.toString().trim().toLowerCase().equals("<" + subReportTag + ">")) {
                    beg = i;
                }
                if (beg > -1 && prow.toString().trim().toLowerCase().equals("</" + subReportTag + ">")) {
                    end = i;
                }
                if (beg > -1 && end > -1) {
                    break;
                }
            } else {
                Object obj = fromJAXB(prow);
                if (obj instanceof ContentAccessor) {
                    List<Object> subContent = ((ContentAccessor) obj).getContent();
                    fillSubReport(subreportinfo, subContent, corrections, imgFilePath, docx, template, invalidObjectList);
                }
            }
        }
        if (beg < 0 || end < 0) {
            return;
        }

        content.remove(beg);
        content.remove(--end);


        List<Object> iterableList = new ArrayList<>(); //Объекты таблицы подотчёта
        List<Object> mainP = new ArrayList<>(); //Объекты отчёта
        mainP.addAll(docx.getMainDocumentPart().getContent());

        for (int i = 0; i < mainP.size(); i++) {
            if (!(mainP.get(i) instanceof P)) {
                mainP.remove(i);
                i--;
            }
        }
        docx.getMainDocumentPart().getContent().removeAll(mainP);

        for (int i = beg; i < end; i++) {
            Object p = content.get(i);
            iterableList.add(p);
        }
        content.removeAll(iterableList);


        List<List<Object>> parts = new ArrayList<>();
        List<List<Object>> mainParts = new ArrayList<>();

        for (int i = 0; i < template.getInfo().size(); i++) {
            List<Object> partMain = new ArrayList<>();
            for (Object object : mainP) {
                Object toAdd = XmlUtils.deepCopy(object);
                partMain.add(toAdd);
            }
            mainParts.add(partMain);
            content.addAll(partMain);


            for (int j = 0; j < template.getSrInfo().get(i).size(); j++) {
                List<Object> part = new ArrayList<>();
                for (Object p : iterableList) {
                    Object toAdd = XmlUtils.deepCopy(p);
                    part.add(toAdd);
                }
                parts.add(part);
                content.addAll(part);
            }
        }

        for (int j = 0; j < template.getInfo().size(); j++) {
            List<Object> part = mainParts.get(j);
            List<Object> texts = getAllElementFromList(part, content);
            searchAndReplaceTags(texts, template.getInfo().get(j), template.getHtmlCorrection(), template.getImgFilePath(), docx, invalidObjectList);
        }
        for (int j = 0; j < subreportinfo.getInfo().size(); j++) {
            List<Object> part = parts.get(j);
            List<Object> textsrow = getAllElementFromList(part, content);
            Map<String, String> val = subreportinfo.getInfo().get(j);
            searchAndReplaceTags(textsrow, val, corrections, imgFilePath, docx, invalidObjectList);
        }
    }

    private void fillSubReport(SubReportInfo subreportinfo,
                               List<Object> content,
                               Map<String, String> corrections,
                               String imgFilePath,
                               WordprocessingMLPackage docx, List<String> invalidObjectList) throws DocxGeneratorException, Docx4JException, UnsupportedEncodingException {

        int beg = -1;
        int end = -1;
        String subReportTag = subreportinfo.getName().trim().toLowerCase();
        for (int i = 0; i < content.size(); i++) {
            Object prow = content.get(i);
            if (prow instanceof P) {
                if (prow.toString().trim().toLowerCase().equals("<" + subReportTag + ">")) {
                    beg = i;
                }
                if (beg > -1 && prow.toString().trim().toLowerCase().equals("</" + subReportTag + ">")) {
                    end = i;
                }
                if (beg > -1 && end > -1) {
                    break;
                }
            } else {
                Object obj = fromJAXB(prow);
                if (obj instanceof ContentAccessor) {
                    List<Object> subContent = ((ContentAccessor) obj).getContent();
                    fillSubReport(subreportinfo, subContent, corrections, imgFilePath, docx, invalidObjectList);
                }
            }
        }
        if (beg < 0 || end < 0) {
            return;
            // throw new DocxGeneratorException("Subreport " + subreportinfo.getName() + " tags not found");
        }

        content.remove(beg);

        content.remove(--end);

        if (subreportinfo.getIndList() == null || subreportinfo.getIndList() == 0) {
            Tbl table = null;

            for (int i = beg; i < end; i++) {
                Object obj = content.get(i);
                obj = fromJAXB(obj);

                if (obj instanceof Tbl) {
                    table = (Tbl) obj;
                }
            }
            if (table == null) {
                throw new DocxGeneratorException("Subreport " + subreportinfo.getName() + " table not found");
            }
            int firstRowIndex = table.getContent().size() > 1 ? 1 : 0;
            Tr tr = (Tr) table.getContent().get(firstRowIndex);
            if (subreportinfo.getInfo() == null || subreportinfo.getInfo().size() == 0) {
                table.getContent().remove(tr);
                return;
            }
            for (int j = 0; j < subreportinfo.getInfo().size() - 1; j++) {
                Tr newRow = (Tr) XmlUtils.deepCopy(tr);
                table.getContent().add(newRow);
            }
            for (int j = 0; j < subreportinfo.getInfo().size(); j++) {
                List<Object> textsrow = getAllElementFromObject(table.getContent().get(j + firstRowIndex), Text.class);
                Map<String, String> val = subreportinfo.getInfo().get(j);
                searchAndReplaceTags(textsrow, val, corrections, imgFilePath, docx, invalidObjectList);
            }

        } else {
            List<Object> iterableList = new ArrayList<>();

            for (int i = beg; i < end; i++) {
                Object p = content.get(i);
                iterableList.add(p);
            }

            content.removeAll(iterableList);

            List<List<Object>> parts = new ArrayList<>();
            for (int j = 0; j < subreportinfo.getInfo().size(); j++) {
                List<Object> part = new ArrayList<>();
                for (Object p : iterableList) {
                    Object toAdd = XmlUtils.deepCopy(p);
                    part.add(toAdd);
                }
                parts.add(part);
                content.addAll(beg + j * iterableList.size(), part);
            }

            for (int j = 0; j < subreportinfo.getInfo().size(); j++) {
                List<Object> part = parts.get(j);
                List<Object> textsrow = getAllElementFromList(part, content);
                Map<String, String> val = subreportinfo.getInfo().get(j);
                searchAndReplaceTags(textsrow, val, corrections, imgFilePath, docx, invalidObjectList);
            }

        }

    }

    private void insertImage(WordprocessingMLPackage docx, byte[] imgFileContent, Object text, String val) {
        try {
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
                    .createImagePart(docx, imgFileContent);

            int docPrId = 1;
            int cNvPrId = 2;
            Inline inline = imagePart.createImageInline("", "", docPrId, cNvPrId, false);

            Object parent = getParentParagraph(text);
            ContentAccessor ancestor = getAncestor(parent, docx);
            if (parent != null && ancestor != null) {
                int pos = ancestor.getContent().indexOf(parent);
                if (pos > -1) {
                    ancestor.getContent().remove(parent);
                    ancestor.getContent().add(pos, addInlineImageToParagraph(inline));
                }
            }
        } catch (Exception ex) {
            logger.error("Error in method Docx4Utils.insertImage", ex);
        }
    }

    private void insertHyperlinkImage(WordprocessingMLPackage docx, byte[] imgFileContent, Object text, String url) {
        try {
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
                    .createImagePart(docx, imgFileContent);

            int docPrId = 1;
            int cNvPrId = 2;
            Inline inline = imagePart.createImageInline("", "", docPrId, cNvPrId, false);
            inline.getDocPr().setHlinkClick(createImageHyperLink(docx, url));

            Object parent = getParentParagraph(text);
            ContentAccessor ancestor = getAncestor(parent, docx);
            if (parent != null && ancestor != null) {
                int pos = ancestor.getContent().indexOf(parent);
                if (pos > -1) {
                    ancestor.getContent().remove(parent);
                    ancestor.getContent().add(pos, addInlineImageToParagraph(inline));
                }
            }
        } catch (Exception ex) {
            logger.error("Error in method Docx4Utils.insertImage", ex);
        }
    }

    private void insertHyperlinkImage(WordprocessingMLPackage docx, File file, Object text, String url) {
        try {
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
                    .createImagePart(docx, file);

            int docPrId = 1;
            int cNvPrId = 2;
            Inline inline = imagePart.createImageInline("", "", docPrId, cNvPrId, false);
            inline.getDocPr().setHlinkClick(createImageHyperLink(docx, url));

            Object parent = getParentParagraph(text);
            ContentAccessor ancestor = getAncestor(parent, docx);
            if (parent != null && ancestor != null) {
                int pos = ancestor.getContent().indexOf(parent);
                if (pos > -1) {
                    ancestor.getContent().remove(parent);
                    ancestor.getContent().add(pos, addInlineImageToParagraph(inline));
                }
            }
        } catch (Exception ex) {
            logger.error("Error in method Docx4Utils.insertImage", ex);
        }
    }

    private CTHyperlink createImageHyperLink(WordprocessingMLPackage docx, String url) throws JAXBException {
        try {
            org.docx4j.relationships.ObjectFactory factory = new org.docx4j.relationships.ObjectFactory();
            org.docx4j.relationships.Relationship relationship = factory.createRelationship();
            relationship.setType(Namespaces.HYPERLINK);
            relationship.setTarget(url);
            relationship.setTargetMode("External");

            docx.getMainDocumentPart().getRelationshipsPart().addRelationship(relationship);

            String hpl = "<a:hlinkClick r:id=\"" + relationship.getId() + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" />";
            return (CTHyperlink) XmlUtils.unmarshalString(hpl, Context.jc, CTHyperlink.class);
        } catch (Exception ex) {
            logger.error("Can't create hyperlink image with url = {}", url, ex);
            return null;
        }

    }

    private void searchAndReplaceTags(List<Object> texts,
                                      Map<String, String> values,
                                      Map<String, String> corrections,
                                      String imgFilePath,
                                      WordprocessingMLPackage docx, List<String> invalidObjectList) throws Docx4JException, UnsupportedEncodingException {

        // -- scan all expressions  
        // Will later contain all the expressions used though not used at the moment
        List<String> els = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int PASS = 0;
        int PREPARE = 1;
        int READ = 2;
        int mode = PASS;

        ObjectFactory factory = new ObjectFactory();

        // to nullify
        List<int[]> toNullify = new ArrayList<int[]>();
        int[] currentNullifyProps = new int[4];
        int tempNullifyProps0 = 0, tempNullifyProps1 = 0;
        // Do scan of els and immediately insert value
        for (int i = 0; i < texts.size(); i++) {
            Object text = texts.get(i);
            Text textElement = (Text) text;
            String newVal = "";
            String v = textElement.getValue();

            StringBuilder textSofar = new StringBuilder();
            int extra = 0;
            char[] vchars = v.toCharArray();
            for (int col = 0; col < vchars.length; col++) {
                char c = vchars[col];
                textSofar.append(c);

                switch (c) {
                    case '#': {
                        mode = PREPARE;
                        sb.append(c);
                        tempNullifyProps0 = i;
                        tempNullifyProps1 = col + extra;
                        //                  extra = 0;
                    }
                    break;
                    case '{': {
                        if (mode == PREPARE) {
                            sb.append(c);
                            mode = READ;
                            currentNullifyProps[0] = tempNullifyProps0;
                            currentNullifyProps[1] = tempNullifyProps1;
                        } else if (mode == READ) {
                            // consecutive opening curl found. just read it
                            // but supposedly throw error
                            sb = new StringBuilder();
                            mode = PASS;
                        }
                    }
                    break;
                    case '}': {
                        if (mode == READ) {
                            mode = PASS;
                            sb.append(c);
                            String tag = sb.toString();
                            tag = tag.substring(2, tag.length() - 1);
                            els.add(sb.toString());
                            if (tag.startsWith("img")) {
                                // check if tag has size for image
                                String tagWithoutSize = null;
                                Integer imgTargetSize = null;
                                Pattern pattern = Pattern.compile("^(.+)_(\\d+)$");
                                Matcher matcher = pattern.matcher(tag);
                                if (matcher.find()) {
                                    tagWithoutSize = matcher.group(1);
                                    imgTargetSize = Integer.parseInt(matcher.group(2));
                                } else {
                                    tagWithoutSize = tag;
                                }
                                String val = extractValue(tagWithoutSize, values);
                                if (val == null) {
                                    val = extractValue(tag, values);
                                    if (val != null) {
                                        imgTargetSize = null;
                                    }
                                }

                                if (val == null) {
                                    newVal += textSofar.toString() + sb.toString();
                                } else if (val.isEmpty()) {
                                    newVal += textSofar.toString();
                                } else {
                                    try {
                                        newVal += textSofar.toString();
                                        String fileName = imgFilePath + File.separator + val;
                                        File imgFile = new File(fileName);
                                        byte[] imgFileContent = null;
                                        if (imgTargetSize != null) {
                                            // scale image
                                            BufferedImage bufferedImage = ImageIO.read(imgFile);
                                            BufferedImage resizedImage = ImageScale.scaleImage(bufferedImage, imgTargetSize);
                                            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                                                ImageIO.write(resizedImage, "jpg", baos);
                                                imgFileContent = baos.toByteArray();
                                            }
                                        } else {
                                            imgFileContent = Files.readAllBytes(imgFile.toPath());
                                        }
                                        insertImage(docx, imgFileContent, text, val);
                                    } catch (Exception ex) {
                                        logger.error("Error when inserting image to document. image file name: {}", val, ex);
                                        throw new Docx4JException("Error when inserting image to document", ex);
                                    }
                                }
                            } else if (tag.toLowerCase().startsWith("map")) {
                                // check if tag has size for image and z
                                String tagWithoutSize;
                                Integer imgTargetSize = null;
                                Integer z = 17;
                                Pattern pattern = Pattern.compile("^(.+)_(\\d+)_z(\\d{1,2})$");
                                Matcher matcher = pattern.matcher(tag);
                                if (matcher.find()) {
                                    tagWithoutSize = matcher.group(1);
                                    imgTargetSize = Integer.parseInt(matcher.group(2));
                                    z = Integer.parseInt(matcher.group(3));
                                } else {
                                    pattern = Pattern.compile("^(.+)_(\\d+)$");
                                    matcher = pattern.matcher(tag);
                                    if (matcher.find()) {
                                        tagWithoutSize = matcher.group(1);
                                        imgTargetSize = Integer.parseInt(matcher.group(2));
                                    } else {
                                        tagWithoutSize = tag;
                                    }
                                }
                                String val = extractValue(tagWithoutSize, values);
                                if (val == null) {
                                    val = extractValue(tag, values);
                                    if (val != null) {
                                        imgTargetSize = null;
                                    }
                                }

                                if (val == null) {
                                    newVal += textSofar.toString() + sb.toString();
                                } else if (val.isEmpty()) {
                                    newVal += textSofar.toString();
                                } else {
                                    try {
                                        newVal += textSofar.toString();
                                        String lat = val.substring(0, val.indexOf(','));
                                        String lon = val.substring(val.indexOf(',') + 1);
                                        Optional<byte[]> imgFile;
                                        byte[] imgFileContent;
                                        if (imgTargetSize != null) {
                                            imgFile = yandexMapApiClient.getMapImage(lon, lat, imgTargetSize, imgTargetSize, z);
                                        } else {
                                            imgFile = yandexMapApiClient.getMapImage(lon, lat);
                                        }
                                        if (!imgFile.isPresent()) {
                                            throw new Docx4JException("Error get map image by lon = "
                                                    + lon + ", lat = " + lat
                                                    + ", imgTargetSize = " + (imgTargetSize != null ? imgTargetSize.toString() : "null"));
                                        }
                                        ByteArrayInputStream bais = new ByteArrayInputStream(imgFile.get());
                                        BufferedImage bufferedImage = ImageIO.read(bais);
                                        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                                            ImageIO.write(bufferedImage, "jpg", baos);
                                            imgFileContent = baos.toByteArray();
                                        }
                                        insertHyperlinkImage(docx, imgFileContent, text, formYandexMapLink(lat, lon));
                                    } catch (Exception ex) {
                                        logger.error("Error when inserting image to document. image file name: {}", val, ex);
                                        throw new Docx4JException("Error when inserting image to document", ex);
                                    }
                                }
                            } else if (tag.toLowerCase().startsWith("borders")) {
                                String tagWithoutSize;
                                Integer imgTargetSize = null;
                                Pattern pattern = Pattern.compile("^(.+)_(\\d+)$");
                                Matcher matcher = pattern.matcher(tag);
                                if (matcher.find()) {
                                    tagWithoutSize = matcher.group(1);
                                    imgTargetSize = Integer.parseInt(matcher.group(2));
                                } else {
                                    tagWithoutSize = tag;
                                    imgTargetSize = 150;
                                }
                                MapRegion mapRegion = null;
                                String regionCode = extractValue(tagWithoutSize, values);

                                if (regionCode == null) {
                                    newVal += textSofar.toString() + sb.toString();
                                } else if (regionCode.isEmpty()) {
                                    newVal += textSofar.toString();
                                } else {
                                    mapRegion = mapRegionController.getMapRegionByMrCode(regionCode);
                                }

                                if (mapRegion != null) {
                                    try {
                                        newVal += textSofar.toString();

                                        String pointer = extractValue("map_location", values);
                                        String delta = mapRegion.getMrDelta();

                                        String lat = pointer.substring(0, pointer.indexOf(','));
                                        String lon = pointer.substring(pointer.indexOf(',') + 1);

                                        double startX=Double.valueOf(mapRegion.getMrLong());
                                        double startY=Double.valueOf(mapRegion.getMrLat());

                                        double pointX=Double.valueOf(lon);
                                        double pointY=Double.valueOf(lat);

                                        double scaleX = Double.valueOf(delta.substring(0, delta.indexOf(',')));
                                        double scaleY = Double.valueOf(delta.substring(delta.indexOf(',') + 1));

                                        double coordX = (pointX-startX)/scaleX;
                                        double coordY = (startY-pointY)/scaleY;

                                        byte[] imgFileContent;

                                        File bordersFile = new File(imgFilePath + File.separator + mapRegion.getMrPath());
                                        BufferedImage bordersImage = ImageIO.read(bordersFile);

                                        Graphics2D g = bordersImage.createGraphics();
                                        g.setColor(Color.RED);
                                        Ellipse2D.Double circle = new Ellipse2D.Double(coordX, coordY, 15, 15);
                                        g.fill(circle);

                                        bordersImage = Scalr.resize(bordersImage, Scalr.Method.ULTRA_QUALITY, imgTargetSize);

                                        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                                            ImageIO.write(bordersImage, "png", baos);
                                            imgFileContent = baos.toByteArray();
                                        }

                                        File dir = new File(imgFilePath + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
                                        if (!dir.exists()) {
                                            dir.mkdir();
                                        }

                                        File file = File.createTempFile("tmp-", ".jpg", dir);
                                        FileUtils.writeByteArrayToFile(file, imgFileContent);

                                        insertHyperlinkImage(docx, file, text, formYandexMapLink(lat, lon));

                                    } catch (Exception ex) {
                                        logger.error("Error when inserting borders image to document. regionCode: {}", regionCode, ex);
                                        throw new Docx4JException("Error when inserting borders image to document", ex);
                                    }
                                }
                            } else if (tag.toLowerCase().startsWith("html")) {
                                try {
                                    String val = extractValue(tag, values);
                                    if (val == null) {
                                        newVal += textSofar.toString() + sb.toString();
                                    } else {
                                        newVal += textSofar.toString();
                                        if (corrections != null) {
                                            for (String key : corrections.keySet()) {
                                                val = val.replace(key, corrections.get(key));
                                            }
                                        }
                                        XHTMLImporterImpl xHTMLImporter = new XHTMLImporterImpl(docx);
                                        List<Object> html = xHTMLImporter.convert(new ByteArrayInputStream(("<html><body>" + val + "</body></html>").getBytes("utf-8")), null);
                                        for (Object par : html) {
                                            par = fillObjectStyle(par);
                                        }

                                        Object parent = getParentParagraph(text);
                                        ContentAccessor ancestor = getAncestor(parent, docx);
                                        if (parent != null && ancestor != null) {
                                            int pos = ancestor.getContent().indexOf(parent);
                                            if (pos > -1) {
                                                ancestor.getContent().remove(parent);
                                                ancestor.getContent().addAll(pos, html);
                                            }
                                        }

                                    }
                                } catch (Exception ex) {
                                    String objCode = values.get(OBJ_CODE);
                                    logger.error("Can't create html description for object ", objCode, ex);
                                    if (objCode != null && !objCode.isEmpty()) {
                                        invalidObjectList.add(objCode);
                                    }
                                }
                            } else if (tag.toLowerCase().startsWith("link")) {
                                String val = extractValue(tag, values);
                                P.Hyperlink hpl = null;
                                if (val != null && !val.isEmpty()) {
                                    hpl = createHyperlink(docx.getMainDocumentPart(), val);
                                }
                                newVal += textSofar.toString();
                                P parent = (P) getParentParagraph(text);
                                Integer targetIndex = parent.getContent().indexOf(((Text) text).getParent());
                                if (hpl != null) {
                                    R hplR = factory.createR();
                                    hplR.getContent().add(hpl);
                                    parent.getContent().add(targetIndex, hplR);
                                }
                            } else {
                                String val = extractValue(tag, values);
                                newVal += textSofar.toString() + (val == null ? sb.toString() : val);
                            }
                            textSofar = new StringBuilder();
                            currentNullifyProps[2] = i;
                            currentNullifyProps[3] = col + extra;
                            toNullify.add(currentNullifyProps);
                            currentNullifyProps = new int[4];
                            extra += sb.toString().length();
                            sb = new StringBuilder();
                        } else if (mode == PREPARE) {
                            mode = PASS;
                            sb = new StringBuilder();
                        }
                    }
                    default: {
                        if (mode == READ) {
                            sb.append(c);
                        } else if (mode == PREPARE) {
                            mode = PASS;
                            sb = new StringBuilder();
                        }
                    }
                }
            }
            newVal += textSofar.toString();
            textElement.setValue(newVal);
        }
        // remove original expressions
        if (toNullify.size() > 0) {
            for (int i = 0; i < texts.size(); i++) {
                if (toNullify.size() == 0) {
                    break;
                }
                currentNullifyProps = toNullify.get(0);
                if (i < currentNullifyProps[0]) {
                    continue;
                }
                Object text = texts.get(i);
                Text textElement = (Text) text;
                String v = textElement.getValue();
                StringBuilder nvalSB = new StringBuilder();
                char[] textChars = v.toCharArray();
                for (int j = 0; j < textChars.length; j++) {
                    char c = textChars[j];
                    if (null == currentNullifyProps) {
                        nvalSB.append(c);
                        continue;
                    }
                    // I know 100000 is too much!!! And so what???
                    int floor = currentNullifyProps[0] * 100000 + currentNullifyProps[1];
                    int ceil = currentNullifyProps[2] * 100000 + currentNullifyProps[3];
                    int head = i * 100000 + j;
                    if (!(head >= floor && head <= ceil)) {
                        nvalSB.append(c);
                    }

                    if (j >= currentNullifyProps[3] && i >= currentNullifyProps[2]) {
                        toNullify.remove(0);
                        if (toNullify.size() == 0) {
                            currentNullifyProps = null;
                            continue;
                        }
                        currentNullifyProps = toNullify.get(0);
                    }
                }
                textElement.setValue(nvalSB.toString());
            }
        }

    }

    public P.Hyperlink createHyperlink(MainDocumentPart mdp, String url) {
        try {
            org.docx4j.relationships.ObjectFactory factory =
                    new org.docx4j.relationships.ObjectFactory();
            org.docx4j.relationships.Relationship rel = factory.createRelationship();
            rel.setType(Namespaces.HYPERLINK);
            rel.setTarget(url);
            rel.setTargetMode("External");
            mdp.getRelationshipsPart().addRelationship(rel);
            String clearUrl = url.replaceAll("&", "&amp;");
            String hpl = "<w:hyperlink r:id=\"" + rel.getId() + "\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" " +
                    "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" >" +
                    "<w:r>" +
                    "<w:rPr>" +
                    "<w:rStyle w:val=\"Hyperlink\" />" +
                    "<w:u w:val=\"single\"/>" +
                    "</w:rPr>" +
                    "<w:t>" + clearUrl + "</w:t>" +
                    "</w:r>" +
                    "</w:hyperlink>";
            return (P.Hyperlink) XmlUtils.unmarshalString(hpl);
        } catch (Exception e) {
            logger.error("Can't create hyperlink for url = {}", url, e);
            return null;
        }
    }

    public String formYandexMapLink(String lat, String lon) {
        String resultLink;
        String baseUrl = "https://maps.yandex.com/?";
        String coordinate = lon.replace(',', '.') + "," + lat.replace(',', '.');
        resultLink = baseUrl
                + "ll=" + coordinate
                + "&pt=" + coordinate + ",org"
                + "&z=15";
        return resultLink;
    }

    private static P addInlineImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

    private String extractValue(String tag, Map<String, String> values) {
        String val = values.get(tag);
        if (val == null) {
            val = values.get(tag.trim().toLowerCase());
        }
        if (val == null) {
            val = values.get(tag.trim().toUpperCase());
        }
        return val;
    }

    private List<Object> getAllElementFromObject(Object obj, Object parent) {
        List<Object> result = new ArrayList<>();
        obj = fromJAXB(obj);

        if (obj.getClass().equals(Text.class)) {
            if (parent != null) {
                ((Text) obj).setParent(parent);
            }
            result.add(obj);
        } else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, obj));
            }

        }
        return result;
    }

    private static List getAllElementFromObjectByClass(Object obj, Class toSearch) {
        List result = new ArrayList();
        if (obj instanceof JAXBElement)
            obj = ((JAXBElement) obj).getValue();

        if (obj.getClass().equals(toSearch)){
            result.add(obj);
        }
        else if (obj instanceof ContentAccessor) {
            List children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObjectByClass(child, toSearch));
            }

        }
        return result;
    }

    private Object getParentParagraph(Object obj) {
        obj = fromJAXB(obj);

        if (obj instanceof Child) {
            if (((Child) obj).getParent() instanceof P) {
                return ((Child) obj).getParent();
            } else {
                return getParentParagraph(((Child) obj).getParent());

            }
        }
        return obj;
    }

    private ContentAccessor getAncestor(Object obj, WordprocessingMLPackage docx) {
        obj = fromJAXB(obj);
        if (obj instanceof Child) {
            if (((Child) obj).getParent() instanceof ContentAccessor) {
                return (ContentAccessor) ((Child) obj).getParent();
            } else if (((Child) obj).getParent() instanceof List && docx.getMainDocumentPart().getContent().contains(obj)) {
                return docx.getMainDocumentPart();
            } else {
                return getAncestor(((Child) obj).getParent(), docx);
            }
        }
        return null;
    }

    private List<Object> getAllElementFromList(List<Object> list, Object parent) {
        List<Object> result = new ArrayList<>();
        for (Object row : list) {
            result.addAll(getAllElementFromObject(row, parent));
        }
        return result;
    }

    public Object fromJAXB(Object obj) {
        if (obj instanceof JAXBElement) {
            return ((JAXBElement<?>) obj).getValue();
        }
        return obj;
    }

    private Object fillObjectStyle(Object obj) {
        Object result = obj;
        obj = fromJAXB(obj);

        if (obj.getClass().equals(R.class)) {
            R r = (R) obj;
            if (r.getRPr() != null && r.getRPr().getRFonts() != null && r.getRPr().getRFonts().getAscii() != null) {
                if (r.getRPr().getRFonts().getHAnsi() == null) {
                    r.getRPr().getRFonts().setHAnsi(r.getRPr().getRFonts().getAscii());
                }
                if (r.getRPr().getRFonts().getEastAsia() == null) {
                    r.getRPr().getRFonts().setEastAsia(r.getRPr().getRFonts().getAscii());
                }
                if (r.getRPr().getRFonts().getCs() == null) {
                    r.getRPr().getRFonts().setCs(r.getRPr().getRFonts().getAscii());
                }
            }
            result = r;
        } else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                child = fillObjectStyle(child);
            }

        }
        return result;
    }

    private static HeaderPart getHeaderPart(WordprocessingMLPackage document) throws InvalidFormatException {
        RelationshipsPart relPart = document.getMainDocumentPart().getRelationshipsPart();
        Relationship rel = relPart.getRelationshipByType(Namespaces.HEADER);
        if(rel != null) return (HeaderPart) relPart.getPart(rel);
        return new HeaderPart();
    }
}
