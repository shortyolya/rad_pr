package com.baltinfo.radius.db.model.bankruptcy;

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
 *
 * @author sas
 */
@Entity
@Table(name = "NON_EXEC_REASON", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VNonExecReason.findAll", query = "SELECT n FROM VNonExecReason n"),
    @NamedQuery(name = "VNonExecReason.findByUnid", query = "SELECT n FROM VNonExecReason n where n.nonExecReasonUnid = :nonExecReasonUnid")})
public class VNonExecReason implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "NON_EXEC_REASON_UNID")
    private long nonExecReasonUnid;
    @Column(name = "NON_EXEC_REASON_NAME")
    private String nonExecReasonName;
    @Column(name = "NON_EXEC_REASON_SNAME")
    private String nonExecReasonSname;

    public VNonExecReason() {
    }

    public long getNonExecReasonUnid() {
        return nonExecReasonUnid;
    }

    public void setNonExecReasonUnid(long nonExecReasonUnid) {
        this.nonExecReasonUnid = nonExecReasonUnid;
    }

    public String getNonExecReasonName() {
        return nonExecReasonName;
    }

    public void setNonExecReasonName(String nonExecReasonName) {
        this.nonExecReasonName = nonExecReasonName;
    }

    public String getNonExecReasonSname() {
        return nonExecReasonSname;
    }

    public void setNonExecReasonSname(String nonExecReasonSname) {
        this.nonExecReasonSname = nonExecReasonSname;
    }

}
