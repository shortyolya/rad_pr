package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.ObjPropertyController;
import com.baltinfo.radius.db.controller.PropertyGroupController;
import com.baltinfo.radius.db.controller.PropertyValueController;
import com.baltinfo.radius.db.pojo.AdditionalProperties;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 18.11.2020
 */
@RunWith(MockitoJUnitRunner.class)
public class ExportAdditionalPropertiesToBkrServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ExportAdditionalPropertiesToBkrServiceTest.class);
    private static final String TEST_FILE_PATH_1 = "src/test/resources/bkr/export/json/TEST(1).JSON";
    private static final String TEST_FILE_PATH_2 = "src/test/resources/bkr/export/json/TEST(2).JSON";
    private static final String TEST_FILE_PATH_3 = "src/test/resources/bkr/export/json/TEST(3).JSON";
    private static final String TEST_FILE_PATH_4 = "src/test/resources/bkr/export/json/TEST(4).JSON";
    private static final String TEST_FILE_PATH_5 = "src/test/resources/bkr/export/json/TEST(5).JSON";

    private AdditionalPropertiesService additionalPropertiesService;

    @Mock
    private PropertyValueController propertyValueController;
    @Mock
    private ObjPropertyController objPropertyController;
    @Mock
    private PropertyGroupController propertyGroupController;
    @Mock
    private LotController lotController;

    @Before
    public void setUp() {
        additionalPropertiesService = new AdditionalPropertiesService(propertyValueController,
                objPropertyController,
                propertyGroupController,
                lotController);
    }

    private String readFileContent(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Can't read file content", e);
            return "";
        }
    }

    @Test
    public void serializeJsonTest() {
        String jsonContent = readFileContent(TEST_FILE_PATH_1);
        Optional<AdditionalProperties> additionalProperties = additionalPropertiesService.deserializeJson(jsonContent);
        Assert.assertNotEquals(Optional.empty(), additionalProperties);
        additionalProperties.ifPresent(props -> {
            Assert.assertNotNull(props);
            Assert.assertEquals(props.getGroups().size(), 1);
            Assert.assertNotNull(props.getGroups().get(0));
            Assert.assertEquals((long) props.getGroups().get(0).getId(), 294);
            Assert.assertEquals((long) props.getGroups().get(0).getId(), 294);
            Assert.assertNotNull(props.getGroups().get(0).getValues());
            Assert.assertEquals(props.getGroups().get(0).getValues().get(0).getPropertyName(), "Марка");
            Assert.assertEquals(props.getGroups().get(0).getValues().get(0).getValue(), "Alfa Romeo");
        });
    }

    @Ignore
    @Test
    public void exportJsonToBkrTest() {
        String jsonTemplate = readFileContent(TEST_FILE_PATH_2);
        Optional<String> jsonResult = additionalPropertiesService.getAdditionalPropertiesJsonByObjUnid(131610L);
        Assert.assertNotEquals(Optional.empty(), jsonResult);
        jsonResult.ifPresent(json -> JSONAssert.assertEquals(jsonTemplate, json, JSONCompareMode.STRICT));
    }

    @Ignore
    @Test
    public void exportJsonToBkrTest1() {
        String jsonTemplate = readFileContent(TEST_FILE_PATH_3);
        Optional<String> jsonResult = additionalPropertiesService.getAdditionalPropertiesJsonByObjUnid(351L);
        Assert.assertNotEquals(Optional.empty(), jsonResult);
        jsonResult.ifPresent(json -> JSONAssert.assertEquals(jsonTemplate, json, JSONCompareMode.STRICT));
    }

    @Ignore
    @Test
    public void exportJsonToBkrTest2() {
        String jsonTemplate = readFileContent(TEST_FILE_PATH_4);
        Optional<String> jsonResult = additionalPropertiesService.getAdditionalPropertiesJsonByObjUnid(134556L);
        Assert.assertNotEquals(Optional.empty(), jsonResult);
        jsonResult.ifPresent(json -> JSONAssert.assertEquals(jsonTemplate, json, JSONCompareMode.STRICT));
    }

    @Ignore
    @Test
    public void importPropertiesFromBkrTest() {
        String jsonContent = readFileContent(TEST_FILE_PATH_5);
        additionalPropertiesService.updateAdditionalPropertiesFromJson(jsonContent, 134556L);
    }
}
