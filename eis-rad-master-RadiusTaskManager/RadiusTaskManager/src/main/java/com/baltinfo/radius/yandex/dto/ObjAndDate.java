package com.baltinfo.radius.yandex.dto;

import com.baltinfo.radius.db.model.ObjectJPA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjAndDate {
    private ObjectJPA objectJPA;
    private Date date;

    public ObjAndDate(ObjectJPA objectJPA, Date date) {
        this.objectJPA = objectJPA;
        this.date = date;
    }

    public String getDateString(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public ObjectJPA getObjectJPA() {
        return objectJPA;
    }

    public void setObjectJPA(ObjectJPA objectJPA) {
        this.objectJPA = objectJPA;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
