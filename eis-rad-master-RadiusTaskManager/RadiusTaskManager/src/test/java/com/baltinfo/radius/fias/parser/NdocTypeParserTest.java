package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.NdocType;
import com.baltinfo.radius.fias.parser.impl.NdocTypeParser;
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

public class NdocTypeParserTest {

    private static final Integer ID = 0;
    private static final String NAME = "Не указан";
    private static final Date STARTDATE = Date.valueOf("1900-01-01");
    private static final Date ENDDATE = Date.valueOf("2016-03-31");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_NORMATIVE_DOCS_TYPES_20201001_f98e38c7-62b8-438e-92ef-6bf93112ef3a.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        NdocTypeParser nDocTypeParser = new NdocTypeParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        nDocTypeParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<NdocType> ndocTypeList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        NdocType ndocType = ndocTypeList.get(0);

        assertNotNull(ndocTypeList);
        assertTrue(ndocTypeList.size() > 1);

        assertEquals(ID, ndocType.getId());
        assertEquals(NAME, ndocType.getName());
        assertEquals(STARTDATE, ndocType.getStartDate());
        assertEquals(ENDDATE, ndocType.getEndDate());
    }
}