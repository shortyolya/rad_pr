package com.baltinfo.radius.subject;


import com.baltinfo.radius.db.constants.TypeSubject;
import com.baltinfo.radius.db.controller.SubjectController;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.SubjectHistory;

import java.util.ArrayList;
import java.util.List;

public class SubjectUtils implements java.io.Serializable {

    private final SubjectController subjectController;

    public SubjectUtils(SubjectController subjectController) {
        this.subjectController = subjectController;
    }

    public List<Subject> findSubject(SubjectHistory sh) {
        List<Subject> subs = new ArrayList<>();
        if (isFl(sh)) {
            if (isInnFilled(sh) && ifFIOFilled(sh)) {
                subs = subjectController.findYlByInnAndFIO(sh.getShSubInn(), sh.getShSubName());
            } else if (isPassportFilled(sh)) {
                if (isSnilsFilled(sh)) {
                    subs = subjectController.findFlByPassportOrSnils(sh.getShSubDocumSeries(), sh.getShSubDocumNumber(), sh.getShSubSnils());
                } else {
                    subs = subjectController.findFlByPassport(sh.getShSubDocumSeries(), sh.getShSubDocumNumber());
                }
            } else if (isSnilsFilled(sh)) {
                subs = subjectController.findFlBySnils(sh.getShSubSnils());
            }
        } else if (isIp(sh)) {
            if (isInnFilled(sh)) {
                subs = subjectController.findIpByInn(sh.getShSubInn());
            } else if (ifOgrnFilled(sh)) {
                subs = subjectController.findByOgrn(sh.getShSubOgrn(), TypeSubject.IP.getUnid());
            }
        } else if (isYl(sh)) {
            if (isInnFilled(sh) && isKppFilled(sh)) {
                subs = subjectController.findYlByInnAndKpp(sh.getShSubInn(), sh.getShSubCodeKpp());
            } else if (ifOgrnFilled(sh)) {
                subs = subjectController.findByOgrn(sh.getShSubOgrn(), TypeSubject.YL.getUnid());
            }
        }
        return subs;
    }

    private boolean isFl(SubjectHistory sh) {
        return sh.getTypesUnid().equals(TypeSubject.FL.getUnid());
    }

    private boolean isIp(SubjectHistory sh) {
        return sh.getTypesUnid().equals(TypeSubject.IP.getUnid());
    }

    private boolean isYl(SubjectHistory sh) {
        return sh.getTypesUnid().equals(TypeSubject.YL.getUnid());
    }

    private boolean isPassportFilled(SubjectHistory sh) {
        return sh.getShSubDocumSeries() != null
                && !sh.getShSubDocumSeries().isEmpty()
                && sh.getShSubDocumNumber() != null
                && !sh.getShSubDocumNumber().isEmpty();
    }

    private boolean isSnilsFilled(SubjectHistory sh) {
        return sh.getShSubSnils() != null && !sh.getShSubSnils().isEmpty();
    }

    private boolean isInnFilled(SubjectHistory sh) {
        return sh.getShSubInn() != null && !sh.getShSubInn().isEmpty();
    }

    private boolean isKppFilled(SubjectHistory sh) {
        return sh.getShSubCodeKpp() != null && !sh.getShSubCodeKpp().isEmpty();
    }

    private boolean ifFIOFilled(SubjectHistory sh) {
        return sh.getShSubName() != null && !sh.getShSubName().isEmpty();
    }

    private boolean ifOgrnFilled(SubjectHistory sh) {
        return sh.getShSubOgrn() != null && !sh.getShSubOgrn().isEmpty();
    }
}
