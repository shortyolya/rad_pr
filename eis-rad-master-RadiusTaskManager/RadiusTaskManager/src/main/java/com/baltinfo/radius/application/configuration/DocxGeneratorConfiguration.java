package com.baltinfo.radius.application.configuration;


import com.baltinfo.radius.db.controller.DocParamValueController;
import com.baltinfo.radius.db.controller.DocTemplateController;
import com.baltinfo.radius.db.controller.DocumentController;
import com.baltinfo.radius.db.controller.MapRegionController;
import com.baltinfo.radius.db.controller.ReportController;
import com.baltinfo.radius.db.controller.TemplateController;
import com.baltinfo.radius.documents.generator.CreatingDocumentService;
import com.baltinfo.radius.documents.generator.Docx4jUtils;
import com.baltinfo.radius.documents.generator.DocxGeneratorController;
import com.baltinfo.radius.yandex.client.YandexMapApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Import({YandexApiConfiguration.class, MapRegionController.class})
public class DocxGeneratorConfiguration {
    @Value("${radius.path.to.files}")
    private String radiusPathToFiles;

    @Bean
    DocumentController documentController() {
        return new DocumentController();
    }

    @Bean
    DocParamValueController docParamValueController() {
        return new DocParamValueController();
    }

    @Bean
    Docx4jUtils Docx4jUtils(YandexMapApiClient yandexMapApiClient, MapRegionController mapRegionController) {
        return new Docx4jUtils(yandexMapApiClient, mapRegionController);
    }

    @Bean
    TemplateController TemplateController() {
        return new TemplateController();
    }

    @Bean
    DocxGeneratorController DocxGeneratorController(Docx4jUtils docx4jUtils, TemplateController templateController) {
        return new DocxGeneratorController(docx4jUtils, templateController);
    }

    @Bean
    ReportController reportController() {
        return new ReportController();
    }

    @Bean
    CreatingDocumentService creatingDocumentService(DocxGeneratorController docxGeneratorController, DocumentController documentController,
                                                    ReportController reportController) {
        return new CreatingDocumentService(docxGeneratorController, documentController, reportController, radiusPathToFiles);
    }

    @Bean
    DocTemplateController DocTemplateController() {
        return new DocTemplateController();
    }

}
