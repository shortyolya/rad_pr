package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.ObjDivision;
import com.baltinfo.radius.fias.parser.impl.ObjDivisionParser;
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
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class ObjDivisionParserTest {

    private static final Long ID = 1357L;
    private static final Long PARENTID = 168389L;
    private static final Long CHILDID = 168395L;
    private static final Integer CHANGEID = 449870;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_ADDR_OBJ_DIVISION_20201001_f1a9c62a-1720-421c-886f-60574099058e.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        ObjDivisionParser objDivisionParser = new ObjDivisionParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        objDivisionParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<ObjDivision> objDivisionList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        ObjDivision objDivision = objDivisionList.get(0);

        assertNotNull(objDivisionList);
        assertTrue(objDivisionList.size() > 1);

        assertEquals(ID, objDivision.getId());
        assertEquals(PARENTID, objDivision.getParentId());
        assertEquals(CHILDID, objDivision.getChildId());
        assertEquals(CHANGEID, objDivision.getChangeId());

    }
}