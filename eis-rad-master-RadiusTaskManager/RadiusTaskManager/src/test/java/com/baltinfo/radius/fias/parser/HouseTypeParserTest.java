package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.HouseType;
import com.baltinfo.radius.fias.parser.impl.HouseTypeParser;
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

public class HouseTypeParserTest {

    private static final Integer ID = 1;
    private static final String NAME = "Владение";
    private static final String SHORTNAME = "влд.";
    private static final String DESC = "Владение";
    private static final Date STARTDATE = Date.valueOf("1900-01-01");
    private static final Date ENDDATE = Date.valueOf("2015-11-05");
    private static final Date UPDATEDATE = Date.valueOf("1900-01-01");
    private static final Boolean ISACTIVE = Boolean.valueOf("false");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_HOUSE_TYPES_20201001_5e05e6f6-d53b-4020-86c0-1b04ae710033.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        HouseTypeParser houseTypeParser = new HouseTypeParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        houseTypeParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<HouseType> houseTypeList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        HouseType houseType = houseTypeList.get(0);

        assertNotNull(houseTypeList);
        assertTrue(houseTypeList.size() > 1);

        assertEquals(ID, houseType.getId());
        assertEquals(NAME, houseType.getName());
        assertEquals(SHORTNAME, houseType.getShortName());
        assertEquals(DESC, houseType.getDesc());
        assertEquals(STARTDATE, houseType.getStartDate());
        assertEquals(ENDDATE, houseType.getEndDate());
        assertEquals(UPDATEDATE, houseType.getUpdateDate());
        assertEquals(ISACTIVE, houseType.getIsActive());

    }
}