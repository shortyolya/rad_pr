package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.CarPlace;
import com.baltinfo.radius.fias.parser.impl.CarPlaceParser;
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

public class CarPlaceParserTest {

    private static final Long ID = 12182167L;
    private static final Long OBJECTID = 21716952L;
    private static final String OBJECTGUID = "1dad4eff-59a8-4041-acac-04d6ed02f465";
    private static final Long CHANGEID = 33904555L;
    private static final String NUMBER = "352";
    private static final String OPERTYPEID = "10";
    private static final Long PREVID = 0L;
    private static final Long NEXTID = 0L;
    private static final Date STARTDATE = Date.valueOf("2019-09-12");
    private static final Date ENDDATE = Date.valueOf("2079-06-06");
    private static final Date UPDATEDATE = Date.valueOf("2019-09-12");
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
        String resourcesFile = "src/test/resources/fias/xml/AS_CARPLACES_20201001_8adc6852-679a-4d82-b9ee-ece62aaff6a3.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        CarPlaceParser carPlaceParser = new CarPlaceParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        carPlaceParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<CarPlace> carPlaceList = requestCaptor.getValue();
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        CarPlace carPlace = carPlaceList.get(0);

        assertNotNull(carPlaceList);
        assertTrue(carPlaceList.size() > 1);

        assertEquals(ID, carPlace.getId());
        assertEquals(OBJECTID, carPlace.getObjectId());
        assertEquals(OBJECTGUID, carPlace.getObjectGuid());
        assertEquals(CHANGEID, carPlace.getChangeId());
        assertEquals(NUMBER, carPlace.getNumber());
        assertEquals(OPERTYPEID, carPlace.getOperTypeId());
        assertEquals(PREVID, carPlace.getPrevId());
        assertEquals(NEXTID, carPlace.getNextId());
        assertEquals(STARTDATE, carPlace.getStartDate());
        assertEquals(ENDDATE, carPlace.getEndDate());
        assertEquals(UPDATEDATE, carPlace.getUpdateDate());
        assertEquals(ISACTIVE, carPlace.getIsActive());
        assertEquals(ISACTUAL, carPlace.getIsActual());
    }
}