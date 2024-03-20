package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.db.model.FeedObject;

public class FeedDescriptionService {
    public String formDescription(FeedObject obj) {
        return formDescription(obj, true);
    }

    public String formDescription(FeedObject obj, boolean addLink) {
        StringBuilder description = new StringBuilder();
        if (obj.getLotEtpCode() != null && !obj.getLotEtpCode().isEmpty()) {
            description.append("Код лота: " + obj.getLotEtpCode() + ". Реализуется на торгах." + " \n" + obj.getDescription());
        } else {
            description.append(obj.getDescription());
        }
        if (addLink && obj.getEtpLink() != null && !obj.getEtpLink().isEmpty()) {
            description.append("\n" + obj.getEtpLink());
        }
        return description.toString();
    }
}
