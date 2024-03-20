package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 29.01.2020
 */
@Entity
@Table(catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"veh_unid"})})
@SequenceGenerator(name = "seq_vehicle", sequenceName = "seq_vehicle", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Vehicle.none", attributeNodes = {
        }),
        @NamedEntityGraph(name = "graph.Vehicle.all", attributeNodes = {
                @NamedAttributeNode("tvUnid")
        })
})
@XmlRootElement
public class Vehicle implements IHistory, Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehicle")
    @Column(name = "veh_unid", nullable = false, precision = 2147483647, scale = 0)
    private long vehUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b", length = 500)
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "veh_brand", length = 500)
    private String vehBrand;
    @Column(name = "veh_model", length = 500)
    private String vehModel;
    @Column(name = "veh_release_year")
    private Short vehReleaseYear;
    @Column(name = "veh_run", precision = 18, scale = 2)
    private BigDecimal vehRun;
    @Column(name = "veh_motor_type", length = 500)
    private String vehMotorType;
    @Column(name = "veh_motor_power", precision = 18, scale = 2)
    private BigDecimal vehMotorPower;
    @Column(name = "veh_motor_size", precision = 18, scale = 2)
    private BigDecimal vehMotorSize;
    @Column(name = "veh_type_gearbox", length = 500)
    private String vehTypeGearbox;
    @Column(name = "veh_type_drive", length = 500)
    private String vehTypeDrive;
    @Column(name = "veh_vin", length = 50)
    private String vehVin;
    @Column(name = "veh_motor_num", length = 50)
    private String vehMotorNum;
    @Column(name = "veh_body", length = 50)
    private String vehBody;
    @Column(name = "veh_body_color", length = 500)
    private String vehBodyColor;
    @Column(name = "veh_gov_num", length = 50)
    private String vehGovNum;
    @Column(name = "veh_passport", length = 50)
    private String vehPassport;
    @Column(name = "veh_state", length = 500)
    private String vehState;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "tv_unid", referencedColumnName = "tv_unid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeVehicle tvUnid;
    @Column(name = "veh_max_wieght", precision = 18, scale = 2)
    private BigDecimal vehMaxWieght;
    @Column(name = "veh_note")
    private String vehNote;

    public Vehicle() {
    }

    public Vehicle(Vehicle sourceVehicle) {
        this.vehBrand = sourceVehicle.vehBrand;
        this.vehModel = sourceVehicle.vehModel;
        this.vehReleaseYear = sourceVehicle.vehReleaseYear;
        this.vehRun = sourceVehicle.vehRun;
        this.vehMotorType = sourceVehicle.vehMotorType;
        this.vehMotorPower = sourceVehicle.vehMotorPower;
        this.vehMotorSize = sourceVehicle.vehMotorSize;
        this.vehTypeGearbox = sourceVehicle.vehTypeGearbox;
        this.vehTypeDrive = sourceVehicle.vehTypeDrive;
        this.vehVin = sourceVehicle.vehVin;
        this.vehMotorNum = sourceVehicle.vehMotorNum;
        this.vehBody = sourceVehicle.vehBody;
        this.vehBodyColor = sourceVehicle.vehBodyColor;
        this.vehGovNum = sourceVehicle.vehGovNum;
        this.vehPassport = sourceVehicle.vehPassport;
        this.vehState = sourceVehicle.vehState;
        this.tvUnid = sourceVehicle.tvUnid;
        this.vehMaxWieght = sourceVehicle.vehMaxWieght;
        this.vehNote = sourceVehicle.vehNote;
    }

    public long getVehUnid() {
        return vehUnid;
    }

    public void setVehUnid(long vehUnid) {
        this.vehUnid = vehUnid;
    }

    public Integer getIndActual() {
        return indActual;
    }

    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    public String getFoundB() {
        return foundB;
    }

    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    public Long getPersCodeB() {
        return persCodeB;
    }

    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    public Date getDateBRec() {
        return dateBRec;
    }

    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getVehBrand() {
        return vehBrand;
    }

    public void setVehBrand(String vehBrand) {
        this.vehBrand = vehBrand;
    }

    public String getVehModel() {
        return vehModel;
    }

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public Short getVehReleaseYear() {
        return vehReleaseYear;
    }

    public void setVehReleaseYear(Short vehReleaseYear) {
        this.vehReleaseYear = vehReleaseYear;
    }

    public BigDecimal getVehRun() {
        return vehRun;
    }

    public void setVehRun(BigDecimal vehRun) {
        this.vehRun = vehRun;
    }

    public String getVehMotorType() {
        return vehMotorType;
    }

    public void setVehMotorType(String vehMotorType) {
        this.vehMotorType = vehMotorType;
    }

    public BigDecimal getVehMotorPower() {
        return vehMotorPower;
    }

    public void setVehMotorPower(BigDecimal vehMotorPower) {
        this.vehMotorPower = vehMotorPower;
    }

    public BigDecimal getVehMotorSize() {
        return vehMotorSize;
    }

    public void setVehMotorSize(BigDecimal vehMotorSize) {
        this.vehMotorSize = vehMotorSize;
    }

    public String getVehTypeGearbox() {
        return vehTypeGearbox;
    }

    public void setVehTypeGearbox(String vehTypeGearbox) {
        this.vehTypeGearbox = vehTypeGearbox;
    }

    public String getVehTypeDrive() {
        return vehTypeDrive;
    }

    public void setVehTypeDrive(String vehTypeDrive) {
        this.vehTypeDrive = vehTypeDrive;
    }

    public String getVehVin() {
        return vehVin;
    }

    public void setVehVin(String vehVin) {
        this.vehVin = vehVin;
    }

    public String getVehMotorNum() {
        return vehMotorNum;
    }

    public void setVehMotorNum(String vehMotorNum) {
        this.vehMotorNum = vehMotorNum;
    }

    public String getVehBody() {
        return vehBody;
    }

    public void setVehBody(String vehBody) {
        this.vehBody = vehBody;
    }

    public String getVehBodyColor() {
        return vehBodyColor;
    }

    public void setVehBodyColor(String vehBodyColor) {
        this.vehBodyColor = vehBodyColor;
    }

    public String getVehGovNum() {
        return vehGovNum;
    }

    public void setVehGovNum(String vehGovNum) {
        this.vehGovNum = vehGovNum;
    }

    public String getVehPassport() {
        return vehPassport;
    }

    public void setVehPassport(String vehPassport) {
        this.vehPassport = vehPassport;
    }

    public String getVehState() {
        return vehState;
    }

    public void setVehState(String vehState) {
        this.vehState = vehState;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public TypeVehicle getTvUnid() {
        return tvUnid;
    }

    public void setTvUnid(TypeVehicle tvUnid) {
        this.tvUnid = tvUnid;
    }

    public BigDecimal getVehMaxWieght() {
        return vehMaxWieght;
    }

    public void setVehMaxWieght(BigDecimal vehMaxWieght) {
        this.vehMaxWieght = vehMaxWieght;
    }

    public String getVehNote() {
        return vehNote;
    }

    public void setVehNote(String vehNote) {
        this.vehNote = vehNote;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Vehicle[ vehUnid=" + vehUnid + " ]";
    }

}
