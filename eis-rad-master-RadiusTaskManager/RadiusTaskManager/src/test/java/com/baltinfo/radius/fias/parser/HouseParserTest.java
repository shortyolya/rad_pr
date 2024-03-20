package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.House;
import com.baltinfo.radius.fias.parser.impl.HouseParser;
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

public class HouseParserTest {

    private static final Long ID = 16201831L;
    private static final Long OBJECTID = 27269246L;
    private static final String OBJECTGUID = "aa5a321d-3a6f-45b7-bb4e-eaa796503136";
    private static final Long CHANGEID = 42049989L;
    private static final String HOUSENUM = "15-г";
    private static final Integer HOUSETYPE = 2;
    private static final Integer OPERTYPEID = 10;
    private static final Long PREVID = 0L;
    private static final Long NEXTID = 70051707L;
    private static final Date STARTDATE = Date.valueOf("2012-01-01");
    private static final Date ENDDATE = Date.valueOf("2020-09-30");
    private static final Date UPDATEDATE = Date.valueOf("2020-09-30");
    private static final Integer ISACTIVE = 0;
    private static final Integer ISACTUAL = 0;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_HOUSES_20201005_9d8ab99f-30de-4e6c-942a-0f160d8bcc4c.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        HouseParser houseParser = new HouseParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        houseParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<House> houseList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        House apartment = houseList.get(0);

        assertNotNull(houseList);
        assertTrue(houseList.size() > 1);

        assertEquals(ID, apartment.getId());
        assertEquals(OBJECTID, apartment.getObjectId());
        assertEquals(OBJECTGUID, apartment.getObjectGuid());
        assertEquals(CHANGEID, apartment.getChangeId());
        assertEquals(HOUSENUM, apartment.getHouseNum());
        assertEquals(HOUSETYPE, apartment.getHouseType());
        assertEquals(OPERTYPEID, apartment.getOperTypeId());
        assertEquals(PREVID, apartment.getPrevId());
        assertEquals(NEXTID, apartment.getNextId());
        assertEquals(STARTDATE, apartment.getStartDate());
        assertEquals(ENDDATE, apartment.getEndDate());
        assertEquals(UPDATEDATE, apartment.getUpdateDate());
        assertEquals(ISACTIVE, apartment.getIsActive());
        assertEquals(ISACTUAL, apartment.getIsActual());
    }
}