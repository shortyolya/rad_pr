package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author css
 */
@Entity
@Table(name = "V_PA_PROFILE", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VPaProfile.checkAccessProfileByPa", query = "SELECT count(v) FROM VPaProfile v WHERE v.paUnid = :paUnid and v.apUnid = :apUnid")})
public class VPaProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AP_UNID", nullable = false)
    private long apUnid;
    @Column(name = "RP_IND_ALLOW_SHOW")
    private Short rpIndAllowShow;
    @Column(name = "RP_IND_ALLOW_CREATE")
    private Short rpIndAllowCreate;
    @Column(name = "RP_IND_ALLOW_EDIT")
    private Short rpIndAllowEdit;
    @Column(name = "RP_IND_ALLOW_DELETE")
    private Short rpIndAllowDelete;
    @Column(name = "RP_IND_ALLOW_EXEC")
    private Short rpIndAllowExec;
    @Column(name = "PAR_UNID")
    private Long parUnid;
    @Id
    @Basic(optional = false)
    @Column(name = "PA_UNID", nullable = false)
    private long paUnid;

    public VPaProfile() {
    }

    public long getApUnid() {
        return apUnid;
    }

    public void setApUnid(long apUnid) {
        this.apUnid = apUnid;
    }

    public Short getRpIndAllowShow() {
        return rpIndAllowShow;
    }

    public void setRpIndAllowShow(Short rpIndAllowShow) {
        this.rpIndAllowShow = rpIndAllowShow;
    }

    public Short getRpIndAllowCreate() {
        return rpIndAllowCreate;
    }

    public void setRpIndAllowCreate(Short rpIndAllowCreate) {
        this.rpIndAllowCreate = rpIndAllowCreate;
    }

    public Short getRpIndAllowEdit() {
        return rpIndAllowEdit;
    }

    public void setRpIndAllowEdit(Short rpIndAllowEdit) {
        this.rpIndAllowEdit = rpIndAllowEdit;
    }

    public Short getRpIndAllowDelete() {
        return rpIndAllowDelete;
    }

    public void setRpIndAllowDelete(Short rpIndAllowDelete) {
        this.rpIndAllowDelete = rpIndAllowDelete;
    }

    public Short getRpIndAllowExec() {
        return rpIndAllowExec;
    }

    public void setRpIndAllowExec(Short rpIndAllowExec) {
        this.rpIndAllowExec = rpIndAllowExec;
    }

    public Long getParUnid() {
        return parUnid;
    }

    public void setParUnid(Long parUnid) {
        this.parUnid = parUnid;
    }


    public long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(long paUnid) {
        this.paUnid = paUnid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.apUnid ^ (this.apUnid >>> 32));
        hash = 67 * hash + (int) (this.paUnid ^ (this.paUnid >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VPaProfile)) return false;

        VPaProfile that = (VPaProfile) o;

        if (apUnid != that.apUnid) return false;
        if (paUnid != that.paUnid) return false;
        if (rpIndAllowShow != null ? !rpIndAllowShow.equals(that.rpIndAllowShow) : that.rpIndAllowShow != null)
            return false;
        if (rpIndAllowCreate != null ? !rpIndAllowCreate.equals(that.rpIndAllowCreate) : that.rpIndAllowCreate != null)
            return false;
        if (rpIndAllowEdit != null ? !rpIndAllowEdit.equals(that.rpIndAllowEdit) : that.rpIndAllowEdit != null)
            return false;
        if (rpIndAllowDelete != null ? !rpIndAllowDelete.equals(that.rpIndAllowDelete) : that.rpIndAllowDelete != null)
            return false;
        if (rpIndAllowExec != null ? !rpIndAllowExec.equals(that.rpIndAllowExec) : that.rpIndAllowExec != null)
            return false;
        return parUnid != null ? parUnid.equals(that.parUnid) : that.parUnid == null;
    }
}
