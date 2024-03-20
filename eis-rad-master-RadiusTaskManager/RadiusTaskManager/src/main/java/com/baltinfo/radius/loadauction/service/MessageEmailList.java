package com.baltinfo.radius.loadauction.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 02.03.2020
 */

public class MessageEmailList {

    private List<String> recepients;

    public MessageEmailList(String recepients) {
        if (recepients != null) {
            this.recepients = Arrays.asList(recepients.split(" *, *", 0));
        } else {
            this.recepients = new ArrayList<>();
        }
    }

    public List<String> getRecepients() {
        return recepients;
    }
}
