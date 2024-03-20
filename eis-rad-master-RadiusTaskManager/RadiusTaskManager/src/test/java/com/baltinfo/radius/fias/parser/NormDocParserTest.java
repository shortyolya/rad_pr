package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.NormDoc;
import com.baltinfo.radius.fias.parser.impl.NormDocParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.FileSystemUtils;

import javax.persistence.EntityManager;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class NormDocParserTest {

    private static final Long ID = 19822023L;
    private static final String NAME = "О присвоении адресов земельным участкам по адресам: Российская Федерация, Республика Адыгея (Адыгея), Майкопский муниципальный район, Сельское поселение Тимирязевское, поселке Шунтук, ул.40 лет Октября, ул.Полевая, ул. Павлова, ул. Дзержинского, з/у";
    private static final String NUMBER = "64";
    private static final Date DATE = Date.valueOf("2020-09-25");
    private static final Integer TYPE = 21;
    private static final Integer KIND = 2;
    private static final Date UPDATEDATE = Date.valueOf("2020-09-28");
    private static final String ORGNAME = "Администрация муниципального образования &quot;Тимирязевское сельское поселение&quot;";
    private static final Date ACCDATE = Date.valueOf("2020-09-25");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_NORMATIVE_DOCS_20201005_b2b8333b-08c8-407f-8712-0e2131657aff.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        NormDocParser normDocParser = new NormDocParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        normDocParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<NormDoc> normDocList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        NormDoc normDoc = normDocList.get(0);

        assertNotNull(normDocList);
        assertTrue(normDocList.size() > 1);

        assertEquals(ID, normDoc.getId());
        assertEquals(NAME, normDoc.getName());
        assertEquals(NUMBER, normDoc.getNumber());
        assertEquals(DATE, normDoc.getDate());
        assertEquals(TYPE, normDoc.getType());
        assertEquals(KIND, normDoc.getKind());
        assertEquals(UPDATEDATE, normDoc.getUpdateDate());
        assertEquals(ORGNAME.replaceAll("&quot;", "\""), normDoc.getOrgName());
        assertEquals(ACCDATE, normDoc.getAccDate());
    }
}