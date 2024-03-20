package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.MinHierarchy;
import com.baltinfo.radius.fias.parser.impl.MinHierarchyParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Ignore;
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

public class MinHierarchyParserTest {

    private static final Long ID = 108380791L;
    private static final Long OBJECTID = 98443248L;
    private static final Long CHANGEID = 156822868L;
    private static final Long PARENTOBJID = 4320L;
    private static final Date STARTDATE = Date.valueOf("2020-09-28");
    private static final Date ENDDATE = Date.valueOf("2079-06-06");
    private static final Date UPDATEDATE = Date.valueOf("2020-09-28");
    private static final Integer ISACTIVE = 1;
    private static final String OKTMO = "79630415106";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_MUN_HIERARCHY_20201005_b627ba17-6a09-45ba-855c-93a020c18631.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        MinHierarchyParser minHierarchyParser = new MinHierarchyParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        minHierarchyParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<MinHierarchy> minHierarchyList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        MinHierarchy minHierarchy = minHierarchyList.get(0);

        assertNotNull(minHierarchyList);
        assertTrue(minHierarchyList.size() > 1);

        assertEquals(ID, minHierarchy.getId());
        assertEquals(OBJECTID, minHierarchy.getObjectId());
        assertEquals(CHANGEID, minHierarchy.getChangeId());
        assertEquals(PARENTOBJID, minHierarchy.getParentObjId());
        assertEquals(STARTDATE, minHierarchy.getStartDate());
        assertEquals(ENDDATE, minHierarchy.getEndDate());
        assertEquals(UPDATEDATE, minHierarchy.getUpdateDate());
        assertEquals(ISACTIVE, minHierarchy.getIsActive());
        assertEquals(OKTMO, minHierarchy.getOktmo());
    }
}