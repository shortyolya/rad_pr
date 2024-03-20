package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.Stead;
import com.baltinfo.radius.fias.parser.impl.SteadParser;
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

public class SteadParserTest {

    private static final Long ID = 12520826L;
    private static final Long OBJECTID = 98443248L;
    private static final String OBJECTGUID = "4961d93d-b408-4d05-9a97-cdea74016805";
    private static final Long CHANGEID = 156822868L;
    private static final String NUMBER = "44";
    private static final String OPERTYPEID = "10";
    private static final Long PREVID = 0L;
    private static final Long NEXTID = 0L;
    private static final Date STARTDATE = Date.valueOf("2020-09-28");
    private static final Date ENDDATE = Date.valueOf("2079-06-06");
    private static final Date UPDATEDATE = Date.valueOf("2020-09-28");
    private static final Integer ISACTIVE = 1;
    private static final Integer ISACTUAL = 1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_STEADS_20201005_71393653-b239-49fa-a283-b8be8c6a5e56.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        SteadParser steadParser = new SteadParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        steadParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<Stead> steadList = requestCaptor.getValue();
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Stead stead = steadList.get(0);

        assertNotNull(steadList);
        assertTrue(steadList.size() > 1);

        assertEquals(ID, stead.getId());
        assertEquals(OBJECTID, stead.getObjectId());
        assertEquals(OBJECTGUID, stead.getObjectGuid());
        assertEquals(CHANGEID, stead.getChangeId());
        assertEquals(NUMBER, stead.getNumber());
        assertEquals(OPERTYPEID, stead.getOperTypeId());
        assertEquals(PREVID, stead.getPrevId());
        assertEquals(NEXTID, stead.getNextId());
        assertEquals(STARTDATE, stead.getStartDate());
        assertEquals(ENDDATE, stead.getEndDate());
        assertEquals(UPDATEDATE, stead.getUpdateDate());
        assertEquals(ISACTIVE, stead.getIsActive());
        assertEquals(ISACTUAL, stead.getIsActual());
    }
}