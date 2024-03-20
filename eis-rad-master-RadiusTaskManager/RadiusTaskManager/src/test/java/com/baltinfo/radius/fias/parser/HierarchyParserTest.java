package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.Hierarchy;
import com.baltinfo.radius.fias.parser.impl.HierarchyParser;
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

public class HierarchyParserTest {

    private static final Long ID = 115617409L;
    private static final Long OBJECTID = 98443248L;
    private static final Long CHANGEID = 156822868L;
    private static final Long PARENTOBJID = 4320L;
    private static final Date STARTDATE = Date.valueOf("2020-09-28");
    private static final Date ENDDATE = Date.valueOf("2079-06-06");
    private static final Date UPDATEDATE = Date.valueOf("2020-09-28");
    private static final Integer ISACTIVE = 1;
    private static final String REGIONCODE = "1";
    private static final String AREACODE = "5";
    private static final String CITYCODE = "0";
    private static final String PLACECODE = "9";
    private static final String PLANCODE = "0";
    private static final String STREETCODE = "21";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_ADM_HIERARCHY_20201005_06e6e7e6-82e2-477a-8e3b-2b3b1636d786.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);
        HierarchyParser hierarchyParser = new HierarchyParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        hierarchyParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<Hierarchy> hierarchyList = requestCaptor.getValue();

        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Hierarchy hierarchy = hierarchyList.get(0);

        assertNotNull(hierarchyList);
        assertTrue(hierarchyList.size() > 1);

        assertEquals(ID, hierarchy.getId());
        assertEquals(OBJECTID, hierarchy.getObjectId());
        assertEquals(CHANGEID, hierarchy.getChangeId());
        assertEquals(PARENTOBJID, hierarchy.getParentObjId());
        assertEquals(STARTDATE, hierarchy.getStartDate());
        assertEquals(ENDDATE, hierarchy.getEndDate());
        assertEquals(UPDATEDATE, hierarchy.getUpdateDate());
        assertEquals(ISACTIVE, hierarchy.getIsActive());
        assertEquals(REGIONCODE, hierarchy.getRegionCode());
        assertEquals(AREACODE, hierarchy.getAreaCode());
        assertEquals(CITYCODE, hierarchy.getCityCode());
        assertEquals(PLACECODE, hierarchy.getPlaceCode());
        assertEquals(PLANCODE, hierarchy.getPlanCode());
        assertEquals(STREETCODE, hierarchy.getStreetCode());
    }
}