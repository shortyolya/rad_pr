package com.baltinfo.radius.feed.xml.export.services;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.loadauction.model.Lot;
import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetArt;
import com.baltinfo.radius.loadauction.service.XmlExportService;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * <p>
 *     Тест для проверки работоспособности сервиса сериализации xml-файлов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class XmlExportServiceTest {

    @Ignore
    @Test
    public void xmlDeserializeTest() throws IOException {
        XmlExportService xmlExportService = new XmlExportService();
        String filePath = "F:\\temp\\Tender_1025200000352_03022022.xml";
        String content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        Publication publication = xmlExportService.loadXmlFromString(content, filePath).getResult();
//        List<Lot> lots = publication.getTenderFulls().getTenders().stream()
//                        .flatMap(tender -> tender.getLots().stream())
//                                .filter(l -> l.getAssetFullList() == null)
//                                        .collect(Collectors.toList());
//        Assert.assertEquals(publication.getBank().getBankName(), "ПУБЛИЧНОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО НИЖЕГОРОДСКИЙ КОММЕРЧЕСКИЙ БАНК \"РАДИОТЕХБАНК\"");
//        Assert.assertEquals(publication.getTenderFulls().getTenderType(), "Блочный торг");
//        System.out.println(publication.getTenderFulls().getTenders().get(0).getPeriods().get(0).getTimeAuction());
//        Assert.assertEquals(publication.getTenderFulls().getTenders().get(2).getLots().size(), 8);
//        Assert.assertEquals(publication.getTenderFulls().getTenders().get(2).getLots().get(0).getId(), (Long) 519L);
//        Assert.assertEquals(publication.getTenderFulls().getTenders().get(2).getLots().get(0).getLocation(), "Московская область");
//        Assert.assertEquals(publication.getTenderFulls().getTenders().get(2).getLots().get(5).getLotName(), "Мерседес-Веns E 200 4 MATIK");
//        Assert.assertEquals((int) publication.getTenderFulls().getTenders().get(0).getLots().get(0).getAssetFullList().get(0).getTypeAsset(), 4);
//        Assert.assertEquals(publication.getTenderFulls().getTenders().get(0).getLots().get(0).getAssetFullList().get(0).getAddress(), "Нижегородская обл, р-н Ветлужский, г Ветлуга, ул М.Горького, д 5 Г");
//        Assert.assertEquals(publication.getTenderFulls().getTenders().get(0).getLots().get(0).getAssetFullList().get(1).getAssessmentDate(), "24.06.2019");
    }

    @Ignore
    @Test
    public void pojoToJson() {
        ObjectMapper mapper = new ObjectMapper();
        AssetArt.AssetArtBuilder assetArt = AssetArt.builder()
                .withTypeOfObject("Art")
                .withTypeAsset(12)
                .withAboutAsset("Some info")
                .withProductName("Template")
                .withAuthor("Rafael")
                .withDate("12.12.1248")
                .withSize("123см")
                .withAddress("Lensovet 23")
                .withIdSya("1234132897149")
                .withRestrictionsAsset("Some info info")
                .withOther("other")
                .withAboutAsset("Information about asset");
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(assetArt);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
