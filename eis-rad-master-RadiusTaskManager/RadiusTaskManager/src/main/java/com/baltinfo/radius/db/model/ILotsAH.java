package com.baltinfo.radius.db.model;

import java.math.BigDecimal;
import java.util.Date;

public interface ILotsAH {
    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    String getNameRu();

    void setNameRu(String nameRu);

    String getNameEn();

    void setNameEn(String nameEn);

    String getSlug();

    void setSlug(String slug);

    Integer getManagerId();

    void setManagerId(Integer managerId);

    Integer getCreatedById();

    void setCreatedById(Integer createdById);

    Integer getUpdatedById();

    void setUpdatedById(Integer updatedById);

    Double getPriceStart();

    void setPriceStart(Double priceStart);

    Double getPriceCutoff();

    void setPriceCutoff(Double priceCutoff);

    Double getPriceStepUp();

    void setPriceStepUp(Double priceStepUp);

    Double getPriceStepDown();

    void setPriceStepDown(Double priceStepDown);

    Double getDepositAmount();

    void setDepositAmount(Double depositAmount);

    Double getPriceSell();

    void setPriceSell(Double priceSell);

    Double getPriceCurrent();

    void setPriceCurrent(Double priceCurrent);

    String getAddress();

    void setAddress(String address);

    BigDecimal getCoordinateX();

    void setCoordinateX(BigDecimal coordinateX);

    BigDecimal getCoordinateY();

    void setCoordinateY(BigDecimal coordinateY);

    String getDescription();

    void setDescription(String description);

    String getDescriptionRu();

    void setDescriptionRu(String descriptionRu);

    String getDescriptionEn();

    void setDescriptionEn(String descriptionEn);

    boolean getIsActive();

    void setIsActive(boolean isActive);

    boolean getIsFeatured();

    void setIsFeatured(boolean isFeatured);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);

    boolean getPriceInfancy();

    void setPriceInfancy(boolean priceInfancy);

    Integer getOfficeId();

    void setOfficeId(Integer officeId);

    String getOnlineUrl();

    void setOnlineUrl(String onlineUrl);

    String getAddressRu();

    void setAddressRu(String addressRu);

    String getAddressEn();

    void setAddressEn(String addressEn);

    Double getPriceMax();

    void setPriceMax(Double priceMax);

    String getMetaKeywords();

    void setMetaKeywords(String metaKeywords);

    String getMetaDescription();

    void setMetaDescription(String metaDescription);

    Integer getPosition();

    void setPosition(Integer position);

    boolean getDirectSale();

    void setDirectSale(boolean directSale);

    String getLotNumber();

    void setLotNumber(String lotNumber);

    Integer getAuctionId();

    void setAuctionId(Integer auctionId);

    Integer getSitesId();

    void setSitesId(Integer sitesId);

    void setYoutubeUrl(String youtubeUrl);

    String getYoutubeUrl();
}
