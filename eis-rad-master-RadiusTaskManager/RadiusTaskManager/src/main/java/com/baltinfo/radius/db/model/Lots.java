package com.baltinfo.radius.db.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "lots", catalog = "", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"name_ru"}),
        @UniqueConstraint(columnNames = {"name_en"}),
        @UniqueConstraint(columnNames = {"slug"})})
@SequenceGenerator(name = "lots_id_seq", sequenceName = "lots_id_seq", allocationSize = 1)
@XmlRootElement
public class Lots implements Serializable, ILotsAH {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lots_id_seq")
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "name_ru", length = 255)
    private String nameRu;
    @Column(name = "name_en", length = 255)
    private String nameEn;
    @Basic(optional = false)
    @Column(name = "slug", nullable = false, length = 255)
    private String slug;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "manager_id")
    private Integer managerId;
    @Column(name = "office_id")
    private Integer officeId;
    @Column(name = "sites_id")
    private Integer sitesId;
    @Column(name = "created_by_id")
    private Integer createdById;
    @Column(name = "updated_by_id")
    private Integer updatedById;
    @Column(name = "price_start", precision = 17, scale = 17)
    private Double priceStart;
    @Column(name = "price_cutoff", precision = 17, scale = 17)
    private Double priceCutoff;
    @Column(name = "price_step_up", precision = 17, scale = 17)
    private Double priceStepUp;
    @Column(name = "price_step_down", precision = 17, scale = 17)
    private Double priceStepDown;
    @Column(name = "deposit_amount", precision = 17, scale = 17)
    private Double depositAmount;
    @Column(name = "price_sell", precision = 17, scale = 17)
    private Double priceSell;
    @Column(name = "price_current", precision = 17, scale = 17)
    private Double priceCurrent;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "\"coordinateX\"", precision = 18, scale = 15)
    private BigDecimal coordinateX;
    @Column(name = "\"coordinateY\"", precision = 18, scale = 15)
    private BigDecimal coordinateY;
    @Column(name = "description", nullable = false, length = 2147483647)
    private String description;
    @Column(name = "description_ru", length = 2147483647)
    private String descriptionRu;
    @Column(name = "description_en", length = 2147483647)
    private String descriptionEn;
    @Basic(optional = false)
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Basic(optional = false)
    @Column(name = "is_featured", nullable = false)
    private boolean isFeatured;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "price_infancy", nullable = false)
    private boolean priceInfancy;
    @Basic(optional = false)
    @Column(name = "online_url", nullable = false, length = 255)
    private String onlineUrl;
    @Column(name = "address_ru", length = 255)
    private String addressRu;
    @Column(name = "address_en", length = 255)
    private String addressEn;
    @Column(name = "price_max", precision = 17, scale = 17)
    private Double priceMax;
    @Column(name = "totalarea", precision = 17, scale = 17)
    private Double totalarea;
    @Column(name = "totalarea_metric")
    private int totalareaMetric;
    @Column(name = "meta_keywords", length = 512)
    private String metaKeywords;
    @Column(name = "meta_description", length = 512)
    private String metaDescription;
    @Column(name = "position")
    private Integer position;
    @Basic(optional = false)
    @Column(name = "direct_sale", nullable = false)
    private boolean directSale;
    @Basic(optional = false)
    @Column(name = "lot_number", nullable = false, length = 255)
    private String lotNumber;
    @Basic(optional = false)
    @Column(name = "mode", nullable = false)
    private int mode;
    @Column(name = "presentation", length = 200)
    private String presentation;
    @Column(name = "promo", length = 2147483647)
    private String promo;
    @Column(name = "promo_ru", length = 2147483647)
    private String promoRu;
    @Column(name = "promo_en", length = 2147483647)
    private String promoEn;
    @Column(name = "set_not_active")
    @Temporal(TemporalType.TIMESTAMP)
    private Date setNotActive;
    @Basic(optional = false)
    @Column(name = "add_to_top_list", nullable = false)
    private boolean addToTopList;
    @Column(name = "top_list_position")
    private Integer topListPosition;
    @Column(name = "auction_id")
    private Integer auctionId;
    @Column(name = "roomarea", precision = 17, scale = 17)
    private Double roomarea;
    @Column(name = "youtube_url", length = 2147483647)
    private String youtubeUrl;


    public Lots() {
    }

    public Lots(Integer id) {
        this.id = id;
    }

    public Lots(Integer id, String name, String slug, String description, boolean isActive, boolean isFeatured, Date createdAt, Date updatedAt, boolean priceInfancy, String onlineUrl, boolean directSale, String lotNumber, int mode, boolean addToTopList) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.isActive = isActive;
        this.isFeatured = isFeatured;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.priceInfancy = priceInfancy;
        this.onlineUrl = onlineUrl;
        this.directSale = directSale;
        this.lotNumber = lotNumber;
        this.mode = mode;
        this.addToTopList = addToTopList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public Double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Double priceStart) {
        this.priceStart = priceStart;
    }

    public Double getPriceCutoff() {
        return priceCutoff;
    }

    public void setPriceCutoff(Double priceCutoff) {
        this.priceCutoff = priceCutoff;
    }

    public Double getPriceStepUp() {
        return priceStepUp;
    }

    public void setPriceStepUp(Double priceStepUp) {
        this.priceStepUp = priceStepUp;
    }

    public Double getPriceStepDown() {
        return priceStepDown;
    }

    public void setPriceStepDown(Double priceStepDown) {
        this.priceStepDown = priceStepDown;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(Double priceSell) {
        this.priceSell = priceSell;
    }

    public Double getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(Double priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(BigDecimal coordinateX) {
        this.coordinateX = coordinateX;
    }

    public BigDecimal getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(BigDecimal coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getPriceInfancy() {
        return priceInfancy;
    }

    public void setPriceInfancy(boolean priceInfancy) {
        this.priceInfancy = priceInfancy;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOnlineUrl() {
        return onlineUrl;
    }

    public void setOnlineUrl(String onlineUrl) {
        this.onlineUrl = onlineUrl;
    }

    public String getAddressRu() {
        return addressRu;
    }

    public void setAddressRu(String addressRu) {
        this.addressRu = addressRu;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public Double getTotalarea() {
        return totalarea;
    }

    public void setTotalarea(Double totalarea) {
        this.totalarea = totalarea;
    }

    public int getTotalareaMetric() {
        return totalareaMetric;
    }

    public void setTotalareaMetric(int totalareaMetric) {
        this.totalareaMetric = totalareaMetric;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean getDirectSale() {
        return directSale;
    }

    public void setDirectSale(boolean directSale) {
        this.directSale = directSale;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getPromoRu() {
        return promoRu;
    }

    public void setPromoRu(String promoRu) {
        this.promoRu = promoRu;
    }

    public String getPromoEn() {
        return promoEn;
    }

    public void setPromoEn(String promoEn) {
        this.promoEn = promoEn;
    }

    public Date getSetNotActive() {
        return setNotActive;
    }

    public void setSetNotActive(Date setNotActive) {
        this.setNotActive = setNotActive;
    }

    public boolean getAddToTopList() {
        return addToTopList;
    }

    public void setAddToTopList(boolean addToTopList) {
        this.addToTopList = addToTopList;
    }

    public Integer getTopListPosition() {
        return topListPosition;
    }

    public void setTopListPosition(Integer topListPosition) {
        this.topListPosition = topListPosition;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Integer getSitesId() {
        return sitesId;
    }

    public void setSitesId(Integer sitesId) {
        this.sitesId = sitesId;
    }

    public Double getRoomarea() {
        return roomarea;
    }

    public void setRoomarea(Double roomarea) {
        this.roomarea = roomarea;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lots)) {
            return false;
        }
        Lots other = (Lots) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Lots[ id=" + id + " ]";
    }

}