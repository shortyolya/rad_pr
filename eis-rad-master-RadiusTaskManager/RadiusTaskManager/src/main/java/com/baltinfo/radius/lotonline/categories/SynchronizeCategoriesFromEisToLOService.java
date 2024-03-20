package com.baltinfo.radius.lotonline.categories;

import com.baltinfo.radius.db.controller.LOCategoryController;
import com.baltinfo.radius.db.controller.SaleCategoryController;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.db.model.lotonline.Category;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 02.02.2021
 */
public class SynchronizeCategoriesFromEisToLOService {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeCategoriesFromEisToLOService.class);
    private final static Long LO_BASE_CATEGORY_ID = 0L;
    private final SaleCategoryController saleCategoryController;
    private final LOCategoryController loCategoryController;
    private final LoCategoriesClient loCategoriesClient;

    public SynchronizeCategoriesFromEisToLOService(SaleCategoryController saleCategoryController, LOCategoryController loCategoryController, LoCategoriesClient loCategoriesClient) {
        this.saleCategoryController = saleCategoryController;
        this.loCategoryController = loCategoryController;
        this.loCategoriesClient = loCategoriesClient;
    }

    public Result<String, String> synchronize() {
        try {

            List<String> success = new ArrayList<>();
            List<String> failure = new ArrayList<>();

            List<SaleCategory> firstLevelEisCategories = saleCategoryController.getFirstLevelCategories();
            List<Category> firstLevelLoCategories = loCategoryController.getChildCategories(LO_BASE_CATEGORY_ID);
            Category parentCategory = new Category();
            parentCategory.setId(LO_BASE_CATEGORY_ID);

            mergeCategories(firstLevelEisCategories, firstLevelLoCategories, parentCategory, success, failure);

            if (failure.isEmpty()) {
                Result<Void, String> refreshResult = loCategoriesClient.refreshCategories();
                if (refreshResult.isError()) {
                    failure.add("Ошибка при обновлении кеша категорий на ЭТП lot-online: " + refreshResult.getError());
                } else {
                    success.add("Кеш категорий на ЭТП lot-online обновлен.");
                }
            }

            if (failure.isEmpty()) {
                return Result.ok(String.join("\n", success));
            } else {
                String result = String.join("\n", failure);
                result += "\n";
                result += String.join("\n", success);
                return Result.error(result);
            }
        } catch(Exception ex) {
            logger.error("Error synchronize lot-online categories", ex);
            return Result.error(ex.getMessage());
        }
    }

    private void mergeCategories(List<SaleCategory> eisCategories, List<Category> lOCategories, Category parentLoCategory, List<String> success, List<String> failure) {
        for (SaleCategory saleCategory : eisCategories) {
            Category category = lOCategories.stream()
                    .filter(c -> c.getId().toString().equals(saleCategory.getScCode()))
                    .findFirst()
                    .orElse(null);
            if (saleCategory.getScIndDelete() == null || !saleCategory.getScIndDelete()) {
                if (category == null) {
                    category = createCategory(saleCategory, parentLoCategory, success, failure);
                } else {
                    lOCategories.remove(category);
                    category = updateCategory(saleCategory, category, parentLoCategory, success, failure);
                }
                List<SaleCategory> childEisCategories = saleCategoryController.getChildCategories(saleCategory.getScUnid());
                List<Category> childLOCategories = loCategoryController.getChildCategories(category.getId());
                mergeCategories(childEisCategories, childLOCategories, category, success, failure);
            } else {
                if (category != null) {
                    List<SaleCategory> childEisCategories = saleCategoryController.getChildCategories(saleCategory.getScUnid());
                    List<Category> childLOCategories = loCategoryController.getChildCategories(category.getId());
                    mergeCategories(childEisCategories, childLOCategories, category, success, failure);
                    lOCategories.remove(category);
                }
                deleteCategory(saleCategory, category, success, failure);
            }
        }

        if (!lOCategories.isEmpty()) {
            for (Category c : lOCategories) {
                success.add("Категория " + c.getId() + " " + c.getName() + " присутствует на ЭТП lot-online, но отсутствует в ЕИС РАД.");
            }
        }
    }

    private Category createCategory(SaleCategory eisCategory, Category parentLoCategory, List<String> success, List<String> failure) {
        logger.info("create category. parentId = {}, name = {}, id = {} ",
                parentLoCategory.getId(), eisCategory.getScName(), eisCategory.getScCode());
        Category category = new Category();
        category.setId(new Long(eisCategory.getScCode()));
        category.setParentId(parentLoCategory.getId());
        category.setName(eisCategory.getScName());
        category.setInterName(eisCategory.getScName());

        Result<Category, String> createResult = loCategoryController.createCategory(category);
        if (createResult.isError()) {
            failure.add("Ошибка при создании категории с кодом " + category.getId() + ". Ошибка: " + createResult.getError());
        } else {
            success.add("Добавлена категория " + category.getId() + " " + category.getName() + ".");
            category = createResult.getResult();
        }
        return category;
    }

    private Category updateCategory(SaleCategory eisCategory, Category loCategory, Category parentLoCategory, List<String> success, List<String> failure) {
        if (!checkParent(loCategory, parentLoCategory)
                || !checkName(loCategory, eisCategory)) {
            logger.info("update category. parentIdOld = {}, parentIdNew = {}, name = {}, id = {} ",
                    loCategory.getParentId(), parentLoCategory.getId(), eisCategory.getScName(), eisCategory.getScCode());
            loCategory.setParentId(parentLoCategory.getId());
            loCategory.setName(eisCategory.getScName());
            loCategory.setInterName(eisCategory.getScName());
            Result<Category, String> updateResult = loCategoryController.updateCategory(loCategory);
            if (updateResult.isError()) {
                failure.add("Ошибка при обновлении категории с кодом " + loCategory.getId() + ". Ошибка: " + updateResult.getError());
            } else {
                success.add("Обновлена категория " + loCategory.getId() + " " + loCategory.getName() + ".");
                loCategory = updateResult.getResult();
            }
        }
        return loCategory;
    }

    private void deleteCategory(SaleCategory eisCategory, Category loCategory, List<String> success, List<String> failure) {
        if (loCategory == null) {
            success.add("Категория для удаления " + eisCategory.getScCode() + " " + eisCategory.getScName() + " отсутствует на ЭТП lot-online");
        } else {
            Result<Void, String> deleteResult = loCategoryController.deleteCategory(loCategory, new Long(eisCategory.getScNewCode()));
            if (deleteResult.isError()) {
                failure.add("Ошибка при удалении категории с кодом " + loCategory.getId() + ". Ошибка: " + deleteResult.getError());
            } else {
                success.add("Удалена категория " + loCategory.getId() + " " + loCategory.getName() + ".");
            }
        }
    }

    private boolean checkParent(Category currentCategory, Category parentCategory) {
        return parentCategory.getId().equals(currentCategory.getParentId());
    }

    private boolean checkName(Category currentCategory, SaleCategory saleCategory) {
        return currentCategory.getName() != null && currentCategory.getName().equals(saleCategory.getScName());
    }


}
