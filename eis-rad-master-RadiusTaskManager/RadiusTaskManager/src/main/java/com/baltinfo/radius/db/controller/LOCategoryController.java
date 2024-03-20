package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.lotonline.Category;
import com.baltinfo.radius.db.model.lotonline.UserProfile;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 02.02.2021
 */
public class LOCategoryController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LOCategoryController.class);

    public List<Category> getChildCategories(Long parentId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerLOLS3();
            return em.createNamedQuery("Category.findChildCategories", Category.class)
                    .setParameter("parentId", parentId)
                    .getResultList();
        } catch (Throwable ex) {
            logger.error("Error getChildCategories.", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Result<Category, String> createCategory(Category category) {
        EntityManager emLs1 = null;
        EntityManager emLs2 = null;
        EntityManager emLs3 = null;
        EntityManager emLs4 = null;
        EntityManager emLs5 = null;
        try {
            emLs1 = HibernateUtil.getInstance().getEntityManagerLOLS1();
            emLs1.getTransaction().begin();
            emLs1.persist(category);

            emLs2 = HibernateUtil.getInstance().getEntityManagerLOLS2();
            emLs2.getTransaction().begin();
            emLs2.persist(category);

            emLs3 = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emLs3.getTransaction().begin();
            emLs3.persist(category);

            emLs4 = HibernateUtil.getInstance().getEntityManagerLOLS4();
            emLs4.getTransaction().begin();
            emLs4.persist(category);

            emLs5 = HibernateUtil.getInstance().getEntityManagerLOLS5();
            emLs5.getTransaction().begin();
            emLs5.persist(category);

            emLs1.getTransaction().commit();
            emLs2.getTransaction().commit();
            emLs3.getTransaction().commit();
            emLs4.getTransaction().commit();
            emLs5.getTransaction().commit();

            return Result.ok(emLs3.find(Category.class, category.getId()));
        } catch (Throwable ex) {
            logger.error("Error getAllCategories.", ex);
            rollbackTransaction(emLs1);
            rollbackTransaction(emLs2);
            rollbackTransaction(emLs3);
            rollbackTransaction(emLs4);
            rollbackTransaction(emLs5);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(emLs1);
            closeEntityManager(emLs2);
            closeEntityManager(emLs3);
            closeEntityManager(emLs4);
            closeEntityManager(emLs5);
        }
    }

    public Result<Category, String> updateCategory(Category category) {

        EntityManager emLs1 = null;
        EntityManager emLs2 = null;
        EntityManager emLs3 = null;
        EntityManager emLs4 = null;
        EntityManager emLs5 = null;
        try {
            emLs1 = HibernateUtil.getInstance().getEntityManagerLOLS1();
            emLs1.getTransaction().begin();
            emLs1.merge(category);

            emLs2 = HibernateUtil.getInstance().getEntityManagerLOLS2();
            emLs2.getTransaction().begin();
            emLs2.merge(category);

            emLs3 = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emLs3.getTransaction().begin();
            emLs3.merge(category);

            emLs4 = HibernateUtil.getInstance().getEntityManagerLOLS4();
            emLs4.getTransaction().begin();
            emLs4.merge(category);

            emLs5 = HibernateUtil.getInstance().getEntityManagerLOLS5();
            emLs5.getTransaction().begin();
            emLs5.merge(category);

            emLs1.getTransaction().commit();
            emLs2.getTransaction().commit();
            emLs3.getTransaction().commit();
            emLs4.getTransaction().commit();
            emLs5.getTransaction().commit();

            return Result.ok(emLs3.find(Category.class, category.getId()));

        } catch (Throwable ex) {
            logger.error("Error getAllCategories.", ex);
            rollbackTransaction(emLs1);
            rollbackTransaction(emLs2);
            rollbackTransaction(emLs3);
            rollbackTransaction(emLs4);
            rollbackTransaction(emLs5);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(emLs1);
            closeEntityManager(emLs2);
            closeEntityManager(emLs3);
            closeEntityManager(emLs4);
            closeEntityManager(emLs5);
        }
    }

    public Result<Void, String> deleteCategory(Category category, Long newCategoryId) {
        EntityManager emLs1 = null;
        EntityManager emLs2 = null;
        EntityManager emLs3 = null;
        EntityManager emLs4 = null;
        EntityManager emLs5 = null;
        EntityManager emUs1 = null;
        try {
            emLs1 = HibernateUtil.getInstance().getEntityManagerLOLS1();
            emLs2 = HibernateUtil.getInstance().getEntityManagerLOLS2();
            emLs3 = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emLs4 = HibernateUtil.getInstance().getEntityManagerLOLS4();
            emLs5 = HibernateUtil.getInstance().getEntityManagerLOLS5();
            emUs1 = HibernateUtil.getInstance().getEntityManagerLOUS1();

            emLs1.getTransaction().begin();
            emLs2.getTransaction().begin();
            emLs3.getTransaction().begin();
            emLs4.getTransaction().begin();
            emLs5.getTransaction().begin();
            emUs1.getTransaction().begin();

            updateLotInfoCategory(emLs1, newCategoryId , category.getId());
            updateLotInfoCategory(emLs2, newCategoryId , category.getId());
            updateLotInfoCategory(emLs3, newCategoryId , category.getId());
            updateLotInfoCategory(emLs4, newCategoryId , category.getId());
            updateLotInfoCategory(emLs5, newCategoryId , category.getId());

            updateProfileCategory(emUs1, newCategoryId , category.getId());

            deleteCategory(emLs1, category.getId());
            deleteCategory(emLs2, category.getId());
            deleteCategory(emLs3, category.getId());
            deleteCategory(emLs4, category.getId());
            deleteCategory(emLs5, category.getId());

            emLs1.getTransaction().commit();
            emLs2.getTransaction().commit();
            emLs3.getTransaction().commit();
            emLs4.getTransaction().commit();
            emLs5.getTransaction().commit();
            emUs1.getTransaction().commit();

            return Result.ok();
        } catch (Throwable ex) {
            logger.error("Error getAllCategories.", ex);
            rollbackTransaction(emLs1);
            rollbackTransaction(emLs2);
            rollbackTransaction(emLs3);
            rollbackTransaction(emLs4);
            rollbackTransaction(emLs5);
            rollbackTransaction(emUs1);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(emLs1);
            closeEntityManager(emLs2);
            closeEntityManager(emLs3);
            closeEntityManager(emLs4);
            closeEntityManager(emLs5);
            closeEntityManager(emUs1);
        }
    }

    private void updateLotInfoCategory(EntityManager em, Long newCategoryId, Long oldCategoryId) {
        em.createQuery("Update LotInfo set categoryFk = :newCategoryId where categoryFk = :oldCategoryId")
                .setParameter("newCategoryId", newCategoryId)
                .setParameter("oldCategoryId", oldCategoryId)
                .executeUpdate();
    }

    private void deleteCategory(EntityManager em, Long categoryId) {
        em.createQuery("delete from Category where id = :categoryId")
                .setParameter("categoryId", categoryId)
                .executeUpdate();
    }

    private void updateProfileCategory(EntityManager em, Long newCategoryId, Long oldCategoryId) {
        List<UserProfile> profiles = em.createQuery("select up from UserProfile up where up.companySpeciality like :oldCategoryId1 " +
                "                                       or up.companySpeciality like :oldCategoryId2 " +
                "                                       or up.companySpeciality like :oldCategoryId3 " +
                "                                       or up.companySpeciality like :oldCategoryId4 ", UserProfile.class)
                .setParameter("oldCategoryId1", "%," + oldCategoryId + ",%")
                .setParameter("oldCategoryId2",  oldCategoryId + ",%")
                .setParameter("oldCategoryId3", "%," + oldCategoryId)
                .setParameter("oldCategoryId4", oldCategoryId + "")
                .getResultList();

        for (UserProfile profile : profiles) {
            List<String> categoryIdsAsString = Arrays.asList(profile.getCompanySpeciality().split(","));
            categoryIdsAsString = categoryIdsAsString.stream()
                    .filter(c -> !c.equals(oldCategoryId + ""))
                    .collect(Collectors.toList());
            categoryIdsAsString.add(newCategoryId + "");
            profile.setCompanySpeciality(String.join(",", categoryIdsAsString));
            em.merge(profile);
        }
    }
}
