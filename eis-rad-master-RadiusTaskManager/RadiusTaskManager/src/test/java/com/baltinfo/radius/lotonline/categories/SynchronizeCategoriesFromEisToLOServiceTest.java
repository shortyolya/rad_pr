package com.baltinfo.radius.lotonline.categories;

import com.baltinfo.radius.db.controller.LOCategoryController;
import com.baltinfo.radius.db.controller.SaleCategoryController;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Suvorina Aleksandra
 * @since 03.02.2021
 */
public class SynchronizeCategoriesFromEisToLOServiceTest {
    private static final int TEST_TIMEOUT = 5000;
    @Test
    @Ignore
    public void synchronize() throws Exception {
        SaleCategoryController saleCategoryController = new SaleCategoryController();
        LOCategoryController loCategoryController = new LOCategoryController();
        LoCategoriesClient loCategoriesClient = new LoCategoriesClient("http://office.rad.loc/", "categories/refresh.rest", TEST_TIMEOUT, TEST_TIMEOUT, TEST_TIMEOUT);
        SynchronizeCategoriesFromEisToLOService synchronizeCategoriesFromEisToLOService = new SynchronizeCategoriesFromEisToLOService(saleCategoryController,
                loCategoryController, loCategoriesClient);
        Result<String, String> result = synchronizeCategoriesFromEisToLOService.synchronize();
        if (result.isSuccess()) {

        }
    }

}
