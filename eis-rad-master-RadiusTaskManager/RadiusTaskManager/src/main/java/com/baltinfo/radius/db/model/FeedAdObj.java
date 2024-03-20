package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */

@Entity
@Table(name = "feed_ad_obj", schema = "web")
@SequenceGenerator(name = "seq_feed_ad_obj", sequenceName = "seq_feed_ad_obj", allocationSize = 1)
public class FeedAdObj {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_feed_ad_obj")
    @Column(name = "fao_unid")
    private Long faoUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "fad_unid")
    private Long fadUnid;
    @Column(name = "fao_ind_success")
    private Boolean faoIndSuccess;
    @Column(name = "fao_error_msg")
    private String faoErrorMsg;

    public Long getFaoUnid() {
        return faoUnid;
    }

    public void setFaoUnid(Long faoUnid) {
        this.faoUnid = faoUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getFadUnid() {
        return fadUnid;
    }

    public void setFadUnid(Long fadUnid) {
        this.fadUnid = fadUnid;
    }

    public Boolean getFaoIndSuccess() {
        return faoIndSuccess;
    }

    public void setFaoIndSuccess(Boolean faoIndSuccess) {
        this.faoIndSuccess = faoIndSuccess;
    }

    public String getFaoErrorMsg() {
        return faoErrorMsg;
    }

    public void setFaoErrorMsg(String faoErrorMsg) {
        this.faoErrorMsg = faoErrorMsg;
    }
}
