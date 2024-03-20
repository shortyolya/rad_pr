package com.baltinfo.radius.db.dto;

import com.baltinfo.radius.db.constants.DepositTypeConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * DTO для работы с загрузкой лотов
 * </p>
 *
 * @author Lapenok Igor
 * @since 17.08.2018
 */
public class LoadLotDto {
    private final String llLotNum;
    private final String llRubric;
    private final String llRegion;
    private final String llLotSname;
    private final String llLotName;
    private final String llReviewDebitorOrder;
    private final BigDecimal llStartCost;
    private final BigDecimal llDepositSum;
    private final BigDecimal llStepValue;
    private final BigDecimal llStepDecreaseValue;
    private final Short llChangePriceTimeH;
    private final Short llChangePriceTimeM;
    private final Integer llChangePricePeriod;
    private final Integer llApplPeriod;
    private final BigDecimal llChangePriceValue;
    private final Short llChangePriceAlg;
    private final DepositTypeConstant llDepositAlg;
    private final String llTypeObject;
    private final String llAddress;
    private final String llClAsv;
    private final Short llIndRightEnsure;
    private final BigDecimal llLandCommonSqr; // Общая площадь ЗУ
    private final BigDecimal llFlatCommonSqr; // Общая площадь помещений
    private final String llMarketingRequirements;
    private final String llContacts;
    private final String llRewardRadius;
    private final String llObjectLinks;
    private final String llAssets;
    private final String llAsvId;
    private final String llAsvLink;
    private final Long scUnid;
    private final String scCode;
    private final BigDecimal llMinPrice;
    private final List<LoadFileDto> llFiles;
    private final String llTzNum;
    private final Date llTzDate;
    private final String llAsvStageId;
    private final Integer llPeriodBeginningDecline;


    private LoadLotDto(String llLotNum, String llRubric, String llRegion,
                       String llLotSname, String llLotName, String llReviewDebitorOrder,
                       BigDecimal llStartCost, BigDecimal llDepositSum, BigDecimal llStepValue,
                       Short llChangePriceTimeH, Short llChangePriceTimeM, Integer llChangePricePeriod,
                       Integer llApplPeriod, BigDecimal llChangePriceValue, Short llChangePriceAlg,
                       DepositTypeConstant llDepositAlg, String llTypeObject, String llAddress,
                       String llClAsv, Short llIndRightEnsure, BigDecimal llLandCommonSqr,
                       BigDecimal llFlatCommonSqr, String llMarketingRequirements, String llContacts,
                       String llRewardRadius, String llObjectLinks, String llAssets, String llAsvId,
                       String llAsvLink, Long scUnid, String scCode, List<LoadFileDto> llFiles,
                       BigDecimal llStepDecreaseValue, BigDecimal llMinPrice, String llTzNum,
                       Date llTzDate, String llAsvStageId, Integer llPeriodBeginningDecline) {
        this.llLotNum = llLotNum;
        this.llRubric = llRubric;
        this.llRegion = llRegion;
        this.llLotSname = llLotSname;
        this.llLotName = llLotName;
        this.llReviewDebitorOrder = llReviewDebitorOrder;
        this.llStartCost = llStartCost;
        this.llDepositSum = llDepositSum;
        this.llStepValue = llStepValue;
        this.llChangePriceTimeH = llChangePriceTimeH;
        this.llChangePriceTimeM = llChangePriceTimeM;
        this.llChangePricePeriod = llChangePricePeriod;
        this.llApplPeriod = llApplPeriod;
        this.llChangePriceValue = llChangePriceValue;
        this.llChangePriceAlg = llChangePriceAlg;
        this.llDepositAlg = llDepositAlg;
        this.llTypeObject = llTypeObject;
        this.llAddress = llAddress;
        this.llClAsv = llClAsv;
        this.llIndRightEnsure = llIndRightEnsure;
        this.llLandCommonSqr = llLandCommonSqr;
        this.llFlatCommonSqr = llFlatCommonSqr;
        this.llMarketingRequirements = llMarketingRequirements;
        this.llContacts = llContacts;
        this.llObjectLinks = llObjectLinks;
        this.llRewardRadius = llRewardRadius;
        this.llAssets = llAssets;
        this.llAsvId = llAsvId;
        this.llAsvLink = llAsvLink;
        this.scUnid = scUnid;
        this.scCode = scCode;
        this.llStepDecreaseValue = llStepDecreaseValue;
        this.llMinPrice = llMinPrice;
        this.llFiles = llFiles == null ? new ArrayList<>() : llFiles;
        this.llTzNum = llTzNum;
        this.llTzDate = llTzDate;
        this.llAsvStageId = llAsvStageId;
        this.llPeriodBeginningDecline = llPeriodBeginningDecline;
    }

    public static LoadLotDtoBuilder builder() {
        return new LoadLotDtoBuilder();
    }

    public String getLlMarketingRequirements() {
        return llMarketingRequirements;
    }

    public String getLlLotNum() {
        return llLotNum;
    }

    public String getLlRubric() {
        return llRubric;
    }

    public String getLlRegion() {
        return llRegion;
    }

    public String getLlLotSname() {
        return llLotSname;
    }

    public String getLlLotName() {
        return llLotName;
    }

    public String getLlReviewDebitorOrder() {
        return llReviewDebitorOrder;
    }

    public BigDecimal getLlStartCost() {
        return llStartCost;
    }

    public BigDecimal getLlDepositSum() {
        return llDepositSum;
    }

    public BigDecimal getLlStepValue() {
        return llStepValue;
    }

    public Short getLlChangePriceTimeH() {
        return llChangePriceTimeH;
    }

    public Short getLlChangePriceTimeM() {
        return llChangePriceTimeM;
    }

    public Integer getLlChangePricePeriod() {
        return llChangePricePeriod;
    }

    public Integer getLlApplPeriod() {
        return llApplPeriod;
    }

    public BigDecimal getLlChangePriceValue() {
        return llChangePriceValue;
    }

    public Short getLlChangePriceAlg() {
        return llChangePriceAlg;
    }

    public DepositTypeConstant getLlDepositAlg() {
        return llDepositAlg;
    }

    public String getLlTypeObject() {
        return llTypeObject;
    }

    public String getLlAddress() {
        return llAddress;
    }

    public String getLlClAsv() {
        return llClAsv;
    }

    public Short getLlIndRightEnsure() {
        return llIndRightEnsure;
    }

    public BigDecimal getLlLandCommonSqr() {
        return llLandCommonSqr;
    }

    public BigDecimal getLlFlatCommonSqr() {
        return llFlatCommonSqr;
    }

    public String getLlContacts() {
        return llContacts;
    }

    public String getLlRewardRadius() {
        return llRewardRadius;
    }

    public String getLlObjectLinks() {
        return llObjectLinks;
    }

    public String getLlAssets() {
        return llAssets;
    }

    public List<LoadFileDto> getLlFiles() {
        return llFiles;
    }

    public String getLlAsvId() {
        return llAsvId;
    }

    public Long getScUnid() {
        return scUnid;
    }

    public String getScCode() {
        return scCode;
    }

    public String getLlAsvLink() {
        return llAsvLink;
    }

    public BigDecimal getLlStepDecreaseValue() {
        return llStepDecreaseValue;
    }

    public BigDecimal getLlMinPrice() {
        return llMinPrice;
    }

    public String getLlTzNum() {
        return llTzNum;
    }

    public Date getLlTzDate() {
        return llTzDate;
    }

    public String getLlAsvStageId() {
        return llAsvStageId;
    }

    public Integer getLlPeriodBeginningDecline() {
        return llPeriodBeginningDecline;
    }

    public static final class LoadLotDtoBuilder {
        private String llLotNum;
        private String llRubric;
        private String llRegion;
        private String llLotSname;
        private String llLotName;
        private String llReviewDebitorOrder;
        private BigDecimal llStartCost;
        private BigDecimal llDepositSum;
        private BigDecimal llStepValue;
        private BigDecimal llStepDecreaseValue;
        private Short llChangePriceTimeH;
        private Short llChangePriceTimeM;
        private Integer llChangePricePeriod;
        private Integer llApplPeriod;
        private BigDecimal llChangePriceValue;
        private Short llChangePriceAlg;
        private DepositTypeConstant llDepositAlg;
        private String llTypeObject;
        private String llAddress;
        private String llClAsv;
        private Short llIndRightEnsure;
        private BigDecimal llLandCommonSqr;
        private BigDecimal llFlatCommonSqr;
        private String llMarketingRequirement;
        private String llContacts;
        private String llRewardRadius;
        private String llObjectLinks;
        private String llAssets;
        private String llAsvId;
        private String llAsvLink;
        private Long scUnid;
        private String scCode;
        private BigDecimal llMinPrice;
        private List<LoadFileDto> llFiles;
        private String llTzNum;
        private Date llTzDate;
        private String llAsvStageId;
        private Integer llPeriodBeginningDecline;

        private LoadLotDtoBuilder() {
        }

        public LoadLotDtoBuilder withLlLotNum(String llLotNum) {
            this.llLotNum = llLotNum;
            return this;
        }

        public LoadLotDtoBuilder withLlRubric(String llRubric) {
            this.llRubric = llRubric;
            return this;
        }

        public LoadLotDtoBuilder withLlRegion(String llRegion) {
            this.llRegion = llRegion;
            return this;
        }

        public LoadLotDtoBuilder withLlLotSname(String llLotSname) {
            this.llLotSname = llLotSname;
            return this;
        }

        public LoadLotDtoBuilder withLlLotName(String llLotName) {
            this.llLotName = llLotName;
            return this;
        }

        public LoadLotDtoBuilder withLlReviewDebitorOrder(String llReviewDebitorOrder) {
            this.llReviewDebitorOrder = llReviewDebitorOrder;
            return this;
        }

        public LoadLotDtoBuilder withLlStartCost(BigDecimal llStartCost) {
            this.llStartCost = llStartCost;
            return this;
        }

        public LoadLotDtoBuilder withLlDepositSum(BigDecimal llDepositSum) {
            this.llDepositSum = llDepositSum;
            return this;
        }

        public LoadLotDtoBuilder withLlStepValue(BigDecimal llStepValue) {
            this.llStepValue = llStepValue;
            return this;
        }

        public LoadLotDtoBuilder withLlStepDecreaseValue(BigDecimal llStepDecreaseValue) {
            this.llStepDecreaseValue = llStepDecreaseValue;
            return this;
        }

        public LoadLotDtoBuilder withLlMinPrice(BigDecimal llMinPrice) {
            this.llMinPrice = llMinPrice;
            return this;
        }

        public LoadLotDtoBuilder withLlChangePriceTimeH(Short llChangePriceTimeH) {
            this.llChangePriceTimeH = llChangePriceTimeH;
            return this;
        }

        public LoadLotDtoBuilder withLlChangePriceTimeM(Short llChangePriceTimeM) {
            this.llChangePriceTimeM = llChangePriceTimeM;
            return this;
        }

        public LoadLotDtoBuilder withLlChangePricePeriod(Integer llChangePricePeriod) {
            this.llChangePricePeriod = llChangePricePeriod;
            return this;
        }

        public LoadLotDtoBuilder withLlApplPeriod(Integer llApplPeriod) {
            this.llApplPeriod = llApplPeriod;
            return this;
        }

        public LoadLotDtoBuilder withLlChangePriceValue(BigDecimal llChangePriceValue) {
            this.llChangePriceValue = llChangePriceValue;
            return this;
        }

        public LoadLotDtoBuilder withLlChangePriceAlg(Short llChangePriceAlg) {
            this.llChangePriceAlg = llChangePriceAlg;
            return this;
        }

        public LoadLotDtoBuilder withLlDepositAlg(DepositTypeConstant llDepositAlg) {
            this.llDepositAlg = llDepositAlg;
            return this;
        }

        public LoadLotDtoBuilder withLlTypeObject(String llTypeObject) {
            this.llTypeObject = llTypeObject;
            return this;
        }

        public LoadLotDtoBuilder withLlAddress(String llAddress) {
            this.llAddress = llAddress;
            return this;
        }

        public LoadLotDtoBuilder withLlClAsv(String llClAsv) {
            this.llClAsv = llClAsv;
            return this;
        }

        public LoadLotDtoBuilder withLlIndRightEnsure(Short llIndRightEnsure) {
            this.llIndRightEnsure = llIndRightEnsure;
            return this;
        }

        public LoadLotDtoBuilder withLlLandCommonSqr(BigDecimal llLandCommonSqr) {
            this.llLandCommonSqr = llLandCommonSqr;
            return this;
        }

        public LoadLotDtoBuilder withLlFlatCommonSqr(BigDecimal llFlatCommonSqr) {
            this.llFlatCommonSqr = llFlatCommonSqr;
            return this;
        }

        public LoadLotDtoBuilder withLlMarketingRequirement(String llMarketingRequirement) {
            this.llMarketingRequirement = llMarketingRequirement;
            return this;
        }

        public LoadLotDtoBuilder withLlContacts(String llContacts) {
            this.llContacts = llContacts;
            return this;
        }

        public LoadLotDtoBuilder withLlObjectLinks(String llObjectLinks) {
            this.llObjectLinks = llObjectLinks;
            return this;
        }

        public LoadLotDtoBuilder withLlRewardRadius(String llRewardRadius) {
            this.llRewardRadius = llRewardRadius;
            return this;
        }

        public LoadLotDtoBuilder withLlAssets(String llAssets) {
            this.llAssets = llAssets;
            return this;
        }

        public LoadLotDtoBuilder withLlAsvId(String llAsvId) {
            this.llAsvId = llAsvId;
            return this;
        }

        public LoadLotDtoBuilder withLlAsvLink(String llAsvLink) {
            this.llAsvLink = llAsvLink;
            return this;
        }

        public LoadLotDtoBuilder withLlSaleCategory(Long scUnid) {
            this.scUnid = scUnid;
            return this;
        }

        public LoadLotDtoBuilder withLlScCode(String scCode) {
            this.scCode = scCode;
            return this;
        }

        public LoadLotDtoBuilder withLlFiles(List<LoadFileDto> llFiles) {
            this.llFiles = llFiles;
            return this;
        }

        public LoadLotDtoBuilder withLlTzNum(String llTzNum) {
            this.llTzNum = llTzNum;
            return this;
        }

        public LoadLotDtoBuilder withLlTzDate(Date llTzDate) {
            this.llTzDate = llTzDate;
            return this;
        }

        public LoadLotDtoBuilder withLlAsvStageId(String llAsvStageId) {
            this.llAsvStageId = llAsvStageId;
            return this;
        }

        public LoadLotDtoBuilder withLlPeriodBeginningDecline(Integer llPeriodBeginningDecline) {
            this.llPeriodBeginningDecline = llPeriodBeginningDecline;
            return this;
        }

        public LoadLotDto build() {
            return new LoadLotDto(llLotNum, llRubric, llRegion, llLotSname, llLotName, llReviewDebitorOrder, llStartCost,
                    llDepositSum, llStepValue, llChangePriceTimeH, llChangePriceTimeM, llChangePricePeriod, llApplPeriod,
                    llChangePriceValue, llChangePriceAlg, llDepositAlg, llTypeObject, llAddress, llClAsv,llIndRightEnsure,
                    llLandCommonSqr, llFlatCommonSqr, llMarketingRequirement, llContacts, llRewardRadius, llObjectLinks,
                    llAssets, llAsvId, llAsvLink, scUnid, scCode, llFiles, llStepDecreaseValue, llMinPrice, llTzNum,
                    llTzDate, llAsvStageId, llPeriodBeginningDecline);
        }
    }
}
