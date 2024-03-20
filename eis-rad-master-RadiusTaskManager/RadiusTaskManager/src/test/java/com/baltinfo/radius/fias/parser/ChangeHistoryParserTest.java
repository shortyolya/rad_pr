package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.ChangeHistory;
import com.baltinfo.radius.fias.parser.impl.ChangeHistoryParser;
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

public class ChangeHistoryParserTest {

    private static final Integer CHANGEID = 156822868;
    private static final Long OBJECTID = 98443248L;
    private static final String ADROBJECTID = "4961d93d-b408-4d05-9a97-cdea74016805";
    private static final Long OPERTYPEID = 10L;
    private static final Long NDOCID = 19821136L;
    private static final Date CHANGEDATE = Date.valueOf("2020-09-28");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_CHANGE_HISTORY_20201005_5d055b3b-ff8e-48b5-a875-dd70ac264740.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        ChangeHistoryParser changeHistoryParser = new ChangeHistoryParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        changeHistoryParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<ChangeHistory> changeHistoryList = requestCaptor.getValue();
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        ChangeHistory changeHistory = changeHistoryList.get(0);

        assertNotNull(changeHistoryList);
        assertTrue(changeHistoryList.size() > 1);

        assertEquals(CHANGEID, changeHistory.getId());
        assertEquals(OBJECTID, changeHistory.getObjectId());
        assertEquals(ADROBJECTID, changeHistory.getAdrobjectid());
        assertEquals(OPERTYPEID, changeHistory.getOperTypeId());
        assertEquals(NDOCID, changeHistory.getNdocId());
        assertEquals(CHANGEDATE, changeHistory.getChangeDate());
    }
}