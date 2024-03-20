package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.Room;
import com.baltinfo.radius.fias.parser.impl.RoomParser;
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

public class RoomParserTest {

    private static final Long ID = 485247L;
    private static final Long OBJECTID = 98443979L;
    private static final String OBJECTGUID = "ca2dc416-fff7-42fe-84f4-0bfb2a2a0c72";
    private static final Long CHANGEID = 156823739L;
    private static final String NUMBER = "2";
    private static final String ROOMTYPE = "1";
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
        String resourcesFile = "src/test/resources/fias/xml/AS_ROOMS_20201005_281ce971-e25e-43c6-9b99-6b8c1950bb06.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        RoomParser roomParser = new RoomParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        roomParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<Room> roomList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Room room = roomList.get(0);

        assertNotNull(roomList);
        assertTrue(roomList.size() > 1);

        assertEquals(ID, room.getId());
        assertEquals(OBJECTID, room.getObjectId());
        assertEquals(OBJECTGUID, room.getObjectGuid());
        assertEquals(CHANGEID, room.getChangeId());
        assertEquals(NUMBER, room.getNumber());
        assertEquals(ROOMTYPE, room.getRoomType());
        assertEquals(OPERTYPEID, room.getOperTypeId());
        assertEquals(PREVID, room.getPrevId());
        assertEquals(NEXTID, room.getNextId());
        assertEquals(STARTDATE, room.getStartDate());
        assertEquals(ENDDATE, room.getEndDate());
        assertEquals(UPDATEDATE, room.getUpdateDate());
        assertEquals(ISACTIVE, room.getIsActive());
        assertEquals(ISACTUAL, room.getIsActual());
    }
}