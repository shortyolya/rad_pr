package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.model.AddressObjectType;
import com.baltinfo.radius.fias.parser.impl.AddressObjectTypeParser;
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

public class AddressObjectTypeParserTest {

    private static final Integer ID = 5;
    private static final Integer LEVEL = 1;
    private static final String NAME = "Автономная область";
    private static final String SHORTNAME = "Аобл";
    private static final String DESC = "Автономная область";
    private static final Date STARTDATE = Date.valueOf("1900-01-01");
    private static final Date ENDDATE = Date.valueOf("2015-11-05");
    private static final Date UPDATEDATE = Date.valueOf("1900-01-01");
    private static final Boolean ISACTIVE = Boolean.valueOf("true");

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest() throws IOException, XMLStreamException {

        Path tempPath = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), UUID.randomUUID().toString());
        String tempDir = Files.createDirectories(tempPath).toFile().getAbsolutePath();
        String resourcesFile = "src/test/resources/fias/xml/AS_ADDR_OBJ_TYPES_20201001_e359277c-8bac-4ef3-b462-2a49ce5c0bb2.XML";
        File fileSrc = new File(resourcesFile);
        File destDir = Files.createDirectories(Paths.get(tempDir + File.separator)).toFile();
        String fileName = FilenameUtils.getName(resourcesFile);
        File destFile = new File(destDir, fileName);

        FileUtils.copyFile(fileSrc, destFile);

        AddressObjectTypeParser addressObjectTypeParser = new AddressObjectTypeParser();

        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        BiConsumer biConsumerMock = Mockito.mock(BiConsumer.class);
        ArgumentCaptor<List> requestCaptor = ArgumentCaptor.forClass(List.class);

        addressObjectTypeParser.parse(entityManagerMock, resourcesFile, biConsumerMock);
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        Mockito.verify(biConsumerMock).accept(any(), requestCaptor.capture());

        List<AddressObjectType> addressObjectTypeList = requestCaptor.getValue();
        FileSystemUtils.deleteRecursively(Paths.get(destDir.getAbsolutePath()));

        AddressObjectType actualAddressObjectType = addressObjectTypeList.get(0);

        assertNotNull(addressObjectTypeList);
        assertTrue(addressObjectTypeList.size() > 1);

        assertEquals(ID, actualAddressObjectType.getId());
        assertEquals(LEVEL, actualAddressObjectType.getLevel());
        assertEquals(NAME, actualAddressObjectType.getName());
        assertEquals(SHORTNAME, actualAddressObjectType.getShortName());
        assertEquals(DESC, actualAddressObjectType.getDesc());
        assertEquals(STARTDATE, actualAddressObjectType.getStartDate());
        assertEquals(ENDDATE, actualAddressObjectType.getEndDate());
        assertEquals(UPDATEDATE, actualAddressObjectType.getUpdateDate());
        assertEquals(ISACTIVE, actualAddressObjectType.getIsActive());
    }
}