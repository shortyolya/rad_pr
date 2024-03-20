package com.baltinfo.radius.bankruptcy.categories;

import com.baltinfo.radius.db.controller.BkrRubricatorController;
import com.baltinfo.radius.db.controller.SaleCategoryController;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Suvorina Aleksandra
 * @since 27.01.2021
 */
public class UpdateCategoriesFromEisToBkrServiceTest {

    @Test
    @Ignore
    public void runProcedure() throws Exception {
        SaleCategoryController saleCategoryController = new SaleCategoryController();
        BkrRubricatorController bkrRubricatorController = new BkrRubricatorController();
        SynchronizeCategoriesFromEisToBkrService updateCategoriesFromEisToBkrService = new SynchronizeCategoriesFromEisToBkrService(saleCategoryController,
                bkrRubricatorController);
        Result<String, String> result = updateCategoriesFromEisToBkrService.synchronize();
        if (result.isSuccess()) {

        }

    }

}
