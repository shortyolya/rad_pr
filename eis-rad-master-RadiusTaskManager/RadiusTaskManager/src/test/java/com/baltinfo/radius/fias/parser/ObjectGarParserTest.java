package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.ObjectGar;
import com.baltinfo.radius.fias.parser.impl.ObjectGarParser;
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

public class ObjectGarParserTest {

    private static final Long ID = 1823941L;
    private static final Long OBJECTID = 95252044L;
    private static final String OBJECTGUID = "1563f48d-f64b-45a6-b7e3-cc59f7347234";
    private static final Long CHANGEID = 138208561L;
    private static final String NAME = "Балаклавский муниципальный округ";
    private static final String TYPENAME = "вн.тер.г.";
    private static final String LEVEL = "3";
    private static final Integer OPERTYPEID = 0;
    private static final Long PREVID = 0L;
    private static final Long NEXTID = 0L;
    private static final Date STARTDATE = Date.valueOf("2011-01-01");
    private static final Date ENDDATE = Date.valueOf("2079-06-06");
    private static final Date UPDATEDATE = Date.valueOf("1900-01-01");
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
        String resourcesFile = "src/test/resources/fias/xml/AS_ADDR_OBJ_20201005_cdefebc9-d982-479b-9d9b-6926b0a29649.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        ObjectGarParser objectGarParser = new ObjectGarParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        objectGarParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<ObjectGar> objectGarList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        ObjectGar objectGar = objectGarList.get(0);

        assertNotNull(objectGarList);
        assertTrue(objectGarList.size() > 1);

        assertEquals(ID, objectGar.getId());
        assertEquals(OBJECTID, objectGar.getObjectId());
        assertEquals(OBJECTGUID, objectGar.getObjectGuid());
        assertEquals(CHANGEID, objectGar.getChangeId());
        assertEquals(NAME, objectGar.getName());
        assertEquals(TYPENAME, objectGar.getTypeName());
        assertEquals(LEVEL, objectGar.getLevel());
        assertEquals(OPERTYPEID, objectGar.getOperTypeId());
        assertEquals(PREVID, objectGar.getPrevId());
        assertEquals(NEXTID, objectGar.getNextId());
        assertEquals(STARTDATE, objectGar.getStartDate());
        assertEquals(ENDDATE, objectGar.getEndDate());
        assertEquals(UPDATEDATE, objectGar.getUpdateDate());
        assertEquals(ISACTIVE, objectGar.getIsActive());
        assertEquals(ISACTUAL, objectGar.getIsActual());
    }
}