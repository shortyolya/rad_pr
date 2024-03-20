package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.NdocKind;
import com.baltinfo.radius.fias.parser.impl.NdocKindParser;
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

public class NdocKindParserTest {

    private static final Integer ID = 0;
    private static final String NAME = "Не определено";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_NORMATIVE_DOCS_KINDS_20201001_c7c08d6f-80b4-4358-b1ce-ac99924eab73.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        NdocKindParser ndocKindParser = new NdocKindParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        ndocKindParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<NdocKind> ndocKindList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        NdocKind ndocKind = ndocKindList.get(0);

        assertNotNull(ndocKindList);
        assertTrue(ndocKindList.size() > 1);

        assertEquals(ID, ndocKind.getId());
        assertEquals(NAME, ndocKind.getName());
    }
}