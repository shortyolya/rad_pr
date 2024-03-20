package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.ParamType;
import com.baltinfo.radius.fias.parser.impl.ParamTypeParser;
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

public class ParamTypeParserTest {

    private static final Integer ID = 3;
    private static final String NAME = "ИНН ФЛ ТЕР УЧ";
    private static final String DESC = "Территориальный участок ИФНС ЮЛ";
    private static final String CODE = "territorialifnsflcode";
    private static final Date STARTDATE = Date.valueOf("2011-11-01");
    private static final Date ENDDATE = Date.valueOf("2079-06-06");
    private static final Date UPDATEDATE = Date.valueOf("2018-06-15");
    private static final Boolean ISACTIVE = Boolean.valueOf("true");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_PARAM_TYPES_20201001_2c7e8eb7-2d43-4a05-9d2c-10bdbdb50f7b.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        ParamTypeParser paramTypeParser = new ParamTypeParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        paramTypeParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<ParamType> paramTypeList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        ParamType paramType = paramTypeList.get(0);

        assertNotNull(paramTypeList);
        assertTrue(paramTypeList.size() > 1);

        assertEquals(ID, paramType.getId());
        assertEquals(NAME, paramType.getName());
        assertEquals(DESC, paramType.getDesc());
        assertEquals(CODE, paramType.getCode());
        assertEquals(STARTDATE, paramType.getStartDate());
        assertEquals(ENDDATE, paramType.getEndDate());
        assertEquals(UPDATEDATE, paramType.getUpdateDate());
        assertEquals(ISACTIVE, paramType.getIsActive());
    }
}