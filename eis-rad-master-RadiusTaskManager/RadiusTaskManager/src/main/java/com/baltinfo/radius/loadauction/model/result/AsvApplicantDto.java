package com.baltinfo.radius.loadauction.model.result;

import com.baltinfo.radius.db.model.DocFile;

import java.util.List;

public class AsvApplicantDto {
    private final Applicant applicant;
    private final List<DocFile> docFiles;

    public AsvApplicantDto(Applicant applicant, List<DocFile> docFiles) {
        this.applicant = applicant;
        this.docFiles = docFiles;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public List<DocFile> getDocFiles() {
        return docFiles;
    }
}
