package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.Param;
import com.baltinfo.radius.fias.parser.impl.ParamParser;
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

public class ParamParserTest {

    private static final Long ID = 674184376L;
    private static final Integer TYPEID = 13;
    private static final String VALUE = "403610000000000016111";
    private static final Integer OBJECTID = 1410332;
    private static final Integer CHANGEID = 157119825;
    private static final Date UPDATEDATE = Date.valueOf("2020-10-01");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_ADDR_OBJ_PARAMS_20201005_569ba586-1f48-429d-8679-0cb39167f2e7.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        ParamParser paramParser = new ParamParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        paramParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<Param> paramList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Param actualParam = paramList.get(0);

        assertNotNull(paramList);
        assertTrue(paramList.size() > 1);

        assertEquals(ID, actualParam.getId());
        assertEquals(TYPEID, actualParam.getTypeId());
        assertEquals(VALUE, actualParam.getValue());
        assertEquals(OBJECTID, actualParam.getObjectId());
        assertEquals(CHANGEID, actualParam.getChangeId());
        assertEquals(UPDATEDATE, actualParam.getUpdateDate());

    }
}