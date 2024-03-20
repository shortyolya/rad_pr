package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.VersionDao;
import com.baltinfo.radius.fias.model.Version;

import javax.persistence.EntityManager;

/**
 * @author Andrei Shymko
 * @since 16.10.2020
 */
public class VersionService {

    private final VersionDao versionDao;

    public VersionService(VersionDao versionDao) {
        this.versionDao = versionDao;
    }

    public void saveOrUpdate(EntityManager entityManager, Version version) {
        versionDao.saveOrUpdate(entityManager, version);
    }

    public Version findByLatestDateAndLoadSign(EntityManager entityManager, Integer loadSign) {
        return versionDao.findVersionByLatestDateAndLoadSign(entityManager, loadSign);
    }

    public Version findVersionByStatus(EntityManager entityManager, Integer loadSign) {
        return versionDao.findVersionByStatus(entityManager, loadSign);
    }
}
