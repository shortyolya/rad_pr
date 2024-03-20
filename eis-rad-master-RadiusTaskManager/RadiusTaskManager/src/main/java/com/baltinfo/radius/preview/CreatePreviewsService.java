package com.baltinfo.radius.preview;

import com.baltinfo.radius.db.controller.DocFileController;
import com.baltinfo.radius.db.controller.PictureController;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class CreatePreviewsService {

    private static final Logger logger = LoggerFactory.getLogger(CreatePreviewsService.class);

    private PictureController pictureController;
    private DocFileController docFileController;
    private String pathToFiles;

    public CreatePreviewsService(PictureController pictureController, DocFileController docFileController, String pathToFiles) {
        this.pictureController = pictureController;
        this.docFileController = docFileController;
        this.pathToFiles = pathToFiles;
    }

    public void createPreviews() {
        List<Picture> pictures = pictureController.getPicturesWithoutPreview();
        for (Picture pic: pictures) {
            DocFile docFile = pic.getDfUnid();

            DocFile previewFile = ImageUtils.createPreviewFile(docFile, pathToFiles);
            if (previewFile != null) {
                docFileController.createDocFile(previewFile);
            }
        }
    }
}
