package com.baltinfo.radius.bankruptcy.categories;

import com.baltinfo.radius.db.constants.BaseSaleCategory;
import com.baltinfo.radius.db.constants.BkrObjectCategory;
import com.baltinfo.radius.db.controller.BkrRubricatorController;
import com.baltinfo.radius.db.controller.SaleCategoryController;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.db.model.bankruptcy.Rubricator;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 25.01.2021
 */
public class SynchronizeCategoriesFromEisToBkrService {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeCategoriesFromEisToBkrService.class);
    private final SaleCategoryController saleCategoryController;
    private final BkrRubricatorController bkrRubricatorController;

    public SynchronizeCategoriesFromEisToBkrService(SaleCategoryController saleCategoryController, BkrRubricatorController bkrRubricatorController) {
        this.saleCategoryController = saleCategoryController;
        this.bkrRubricatorController = bkrRubricatorController;
    }

    public Result<String, String> synchronize() {
        try {

            Map<BkrObjectCategory, String> baseCategories = new HashMap<>();
            baseCategories.put(BkrObjectCategory.REAL_ESTATE, BaseSaleCategory.REAL_ESTATE.getScCode());
            baseCategories.put(BkrObjectCategory.MOVABLE_PROPERTY, BaseSaleCategory.MOVABLE_PROPERTY.getScCode());
            baseCategories.put(BkrObjectCategory.INTANGIBLE_ASSETS, BaseSaleCategory.INTANGIBLE_ASSETS.getScCode());

            List<String> failure = new ArrayList<>();
            List<String> success = new ArrayList<>();
            List<Rubricator> allRubrics = bkrRubricatorController.getAllRubricator();
            for (Map.Entry<BkrObjectCategory, String> entry : baseCategories.entrySet()) {
                SaleCategory saleCategory = saleCategoryController.getSaleCategoryByScCode(entry.getValue());

                List<SaleCategory> childCategories = saleCategoryController.getChildCategories(saleCategory.getScUnid());
                BkrObjectCategory bkrObjectCategory = entry.getKey();
                Rubricator baseRubricator = new Rubricator();
                baseRubricator.setOcUnid(bkrObjectCategory.getUnid());
                baseRubricator.setRubrName(bkrObjectCategory.getName());
                baseRubricator.setRubrFullName(bkrObjectCategory.getName());
                baseRubricator.setRubrLevel(0);

                mergeCategories(childCategories, allRubrics, baseRubricator, success, failure);
            }

            List<SaleCategory> saleCategoriesForDelete = saleCategoryController.getSaleCategoriesForDelete();
            for (SaleCategory saleCategory : saleCategoriesForDelete) {
                Rubricator rubricator = allRubrics.stream()
                        .filter(r -> r.getRubrSingleClassifCode().equals(saleCategory.getScCode()))
                        .findFirst()
                        .orElse(null);
                if (rubricator == null) {
                    success.add("Рубрика для удаления " + saleCategory.getScCode() + " " + saleCategory.getScName() + " отсутствует на ЭТП Банкротство");
                } else {
                    Result<Void, String> deleteResult = bkrRubricatorController.deleteRubricator(rubricator, saleCategory.getScNewCode());
                    if (deleteResult.isError()) {
                        failure.add("Ошибка при удалении рубрики с кодом " + rubricator.getRubrSingleClassifCode() + ". Ошибка: " + deleteResult.getError());
                        continue;
                    } else {
                        success.add("Удалена рубрика " + rubricator.getRubrSingleClassifCode() + " " + rubricator.getRubrName() + ".");
                    }
                    allRubrics.remove(rubricator);
                }
            }

            if (!allRubrics.isEmpty()) {
                for (Rubricator rubr : allRubrics) {
                    success.add("Рубрика " + rubr.getRubrSingleClassifCode() + " " + rubr.getRubrName() + " присутствует на ЭТП Банкротство, но отсутствует в ЕИС РАД.");
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
            logger.error("Error synchronize bkr rubricator", ex);
            return Result.error(ex.getMessage());
        }
    }

    private void mergeCategories(List<SaleCategory> eisCategories, List<Rubricator> allRubrics, Rubricator parentRubricator, List<String> success, List<String> failure) {

        List<SaleCategory> categoriesForUpdate = eisCategories.stream()
                .filter(sc -> sc.getScIndDelete() == null || !sc.getScIndDelete())
                .collect(Collectors.toList());

        for (SaleCategory saleCategory : categoriesForUpdate) {
            Rubricator rubricator = allRubrics.stream()
                    .filter(r -> r.getRubrSingleClassifCode().equals(saleCategory.getScCode()))
                    .findFirst()
                    .orElse(null);
            if (rubricator == null) {
                logger.info("create rubricator. parentRubrUnid = {}, rubrName = {}, singleCode = {}, rubrLevel = {} ",
                        parentRubricator.getRubrUnid(), saleCategory.getScName(), saleCategory.getScCode(), parentRubricator.getRubrLevel() + 1);
                rubricator = new Rubricator();
                rubricator.setRubrLevel(parentRubricator.getRubrLevel() + 1);
                rubricator.setRubRubrUnid(parentRubricator.getRubrUnid());
                rubricator.setRubrName(saleCategory.getScName());
                rubricator.setRubrFullName(parentRubricator.getRubrFullName() + " > " + saleCategory.getScName());
                rubricator.setOcUnid(parentRubricator.getOcUnid());
                rubricator.setRubrSingleClassifCode(saleCategory.getScCode());
                rubricator.setRubrCode(saleCategory.getScEfrsbCode());
                rubricator.setRubrAsvCode(saleCategory.getScAsvCode());
                Result<Rubricator, String> createResult = bkrRubricatorController.createRubricator(rubricator);
                if (createResult.isError()) {
                    failure.add("Ошибка при создании рубрики с кодом " + rubricator.getRubrSingleClassifCode() + ". Ошибка: " + createResult.getError());
                    continue;
                } else {
                    success.add("Добавлена рубрика " + rubricator.getRubrSingleClassifCode() + " " + rubricator.getRubrName() + ".");
                    rubricator = createResult.getResult();
                }
            } else {
                allRubrics.remove(rubricator);
                if (!checkParentRubric(rubricator, parentRubricator)
                        || !checkRubrName(rubricator, saleCategory)
                        || !checkRubrEfrsbCode(rubricator, saleCategory)
                        || !checkRubrAsvCode(rubricator, saleCategory)) {
                    logger.info("update rubricator. parentRubrUnidOld = {},  parentRubrUnidNew = {}, rubrName = {}, singleCode = {}, rubrLevel = {} ",
                            rubricator.getRubRubrUnid(), parentRubricator.getRubrUnid(), saleCategory.getScName(), saleCategory.getScCode(), rubricator.getRubrLevel());
                    rubricator.setRubrLevel(parentRubricator.getRubrLevel() + 1);
                    rubricator.setRubRubrUnid(parentRubricator.getRubrUnid());
                    rubricator.setRubrName(saleCategory.getScName());
                    rubricator.setRubrFullName(parentRubricator.getRubrFullName() + " > " + saleCategory.getScName());
                    rubricator.setRubrCode(saleCategory.getScEfrsbCode());
                    rubricator.setRubrAsvCode(saleCategory.getScAsvCode());
                    Result<Rubricator, String> updateResult = bkrRubricatorController.updateRubricator(rubricator);
                    if (updateResult.isError()) {
                        failure.add("Ошибка при обновлении рубрикатора с кодом " + rubricator.getRubrSingleClassifCode() + ". Ошибка: " + updateResult.getError());
                        continue;
                    } else {
                        success.add("Обновлена рубрика " + rubricator.getRubrSingleClassifCode() + " " + rubricator.getRubrName() + ".");
                        rubricator = updateResult.getResult();
                    }
                }
            }
            List<SaleCategory> childCategories = saleCategoryController.getChildCategories(saleCategory.getScUnid());
            mergeCategories(childCategories, allRubrics, rubricator, success, failure);
        }


    }

    private boolean checkParentRubric(Rubricator currentRubricator, Rubricator parentRubricator) {
        return (currentRubricator.getRubRubrUnid() == null && parentRubricator.getRubrUnid() == null)
                || (currentRubricator.getRubRubrUnid().equals(parentRubricator.getRubrUnid()));
    }

    private boolean checkRubrName(Rubricator currentRubricator, SaleCategory saleCategory) {
        return currentRubricator.getRubrName().equals(saleCategory.getScName()) && currentRubricator.getRubrFullName() != null
                && !currentRubricator.getRubrFullName().isEmpty();
    }

    private boolean checkRubrEfrsbCode(Rubricator currentRubricator, SaleCategory saleCategory) {
        return currentRubricator.getRubrCode() != null
                && !currentRubricator.getRubrCode().isEmpty()
                && currentRubricator.getRubrCode().equals(saleCategory.getScEfrsbCode());
    }

    private boolean checkRubrAsvCode(Rubricator currentRubricator, SaleCategory saleCategory) {
        return currentRubricator.getRubrAsvCode() != null
                && !currentRubricator.getRubrAsvCode().isEmpty()
                && currentRubricator.getRubrAsvCode().equals(saleCategory.getScAsvCode());
    }

}
