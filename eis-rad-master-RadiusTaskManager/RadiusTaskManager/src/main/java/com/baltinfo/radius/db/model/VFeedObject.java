package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "v_feed_object")
@NamedQuery(name = "VFeedObject.findAllWithoutCategories",
        query = "select v from VFeedObject v " +
                "where v.mevUnid = :mevUnid " +
                "  and (v.fcUnid = null or v.scUnid = null)" +
                "  and v.orgSubUnid = :subUnid")
public class VFeedObject {

    @Id
    @Column(name = "ome_unid")
    private Long omeUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "mev_unid")
    private Long mevUnid;
    @Column(name = "sc_unid")
    private Long scUnid;
    @Column(name = "fc_unid")
    private Long fcUnid;
    @Column(name = "org_sub_unid")
    private Long orgSubUnid;

    public Long getOmeUnid() {
        return omeUnid;
    }

    public void setOmeUnid(Long omeUnid) {
        this.omeUnid = omeUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
    }

    public Long getScUnid() {
        return scUnid;
    }

    public void setScUnid(Long scUnid) {
        this.scUnid = scUnid;
    }

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public Long getOrgSubUnid() {
        return orgSubUnid;
    }

    public void setOrgSubUnid(Long orgSubUnid) {
        this.orgSubUnid = orgSubUnid;
    }
}
