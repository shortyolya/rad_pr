package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.RoomType;
import com.baltinfo.radius.fias.parser.impl.RoomTypeParser;
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

public class RoomTypeParserTest {

    private static final Integer ID = 0;
    private static final String NAME = "Не определено";
    private static final String DESC = "Не определено";
    private static final Date STARTDATE = Date.valueOf("1900-01-01");
    private static final Date ENDDATE = Date.valueOf("2015-11-05");
    private static final Date UPDATEDATE = Date.valueOf("2011-01-01");
    private static final Boolean ISACTIVE = Boolean.valueOf("true");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_ROOM_TYPES_20201001_1a0bb28f-1aec-4f38-9762-5afeb80f5691.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        RoomTypeParser roomTypeParser = new RoomTypeParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        roomTypeParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<RoomType> roomTypeList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        RoomType roomType = roomTypeList.get(0);

        assertNotNull(roomTypeList);
        assertTrue(roomTypeList.size() > 1);

        assertEquals(ID, roomType.getId());
        assertEquals(NAME, roomType.getName());
        assertEquals(DESC, roomType.getDesc());
        assertEquals(STARTDATE, roomType.getStartDate());
        assertEquals(ENDDATE, roomType.getEndDate());
        assertEquals(UPDATEDATE, roomType.getUpdateDate());
        assertEquals(ISACTIVE, roomType.getIsActive());
    }
}