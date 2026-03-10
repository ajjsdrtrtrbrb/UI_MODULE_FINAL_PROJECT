package org.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BonusRequest {

    @JsonProperty("bonus")
    private Bonus bonus;

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public static class Bonus {

        @JsonProperty("id")
        private Long id; // <- добавлено для UPDATE

        @JsonProperty("localizedInfoList")
        private List<LocalizedInfo> localizedInfoList;

        @JsonProperty("productType")
        private String productType;

        @JsonProperty("visible")
        private Boolean visible;

        @JsonProperty("startTime")
        private Long startTime;

        @JsonProperty("endTime")
        private Long endTime;

        @JsonProperty("daysToWager")
        private Integer daysToWager;

        @JsonProperty("hoursToWager")
        private Integer hoursToWager;

        @JsonProperty("localizedImageInfoList")
        private List<Object> localizedImageInfoList;

        @JsonProperty("financeDataListKeys")
        private List<String> financeDataListKeys;

        @JsonProperty("wager")
        private Integer wager;

        @JsonProperty("sportLimit")
        private Object sportLimit;

        @JsonProperty("requiredBetsToWager")
        private Integer requiredBetsToWager;

        @JsonProperty("basicLimit")
        private Object basicLimit;

        @JsonProperty("depositMultiplier")
        private Object depositMultiplier;

        @JsonProperty("financeDataList")
        private List<FinanceData> financeDataList;

        @JsonProperty("bonusType")
        private String bonusType;

        @JsonProperty("activationMethod")
        private String activationMethod;

        @JsonProperty("debitMethod")
        private String debitMethod;

        @JsonProperty("wagerType")
        private String wagerType;

        @JsonProperty("defaultLanguage")
        private String defaultLanguage;

        @JsonProperty("timeToWager")
        private Long timeToWager;

        @JsonProperty("timeToUse")
        private Object timeToUse;


        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public List<LocalizedInfo> getLocalizedInfoList() { return localizedInfoList; }
        public void setLocalizedInfoList(List<LocalizedInfo> localizedInfoList) { this.localizedInfoList = localizedInfoList; }

        public String getProductType() { return productType; }
        public void setProductType(String productType) { this.productType = productType; }

        public Boolean getVisible() { return visible; }
        public void setVisible(Boolean visible) { this.visible = visible; }

        public Long getStartTime() { return startTime; }
        public void setStartTime(Long startTime) { this.startTime = startTime; }

        public Long getEndTime() { return endTime; }
        public void setEndTime(Long endTime) { this.endTime = endTime; }

        public Integer getDaysToWager() { return daysToWager; }
        public void setDaysToWager(Integer daysToWager) { this.daysToWager = daysToWager; }

        public Integer getHoursToWager() { return hoursToWager; }
        public void setHoursToWager(Integer hoursToWager) { this.hoursToWager = hoursToWager; }

        public List<Object> getLocalizedImageInfoList() { return localizedImageInfoList; }
        public void setLocalizedImageInfoList(List<Object> localizedImageInfoList) { this.localizedImageInfoList = localizedImageInfoList; }

        public List<String> getFinanceDataListKeys() { return financeDataListKeys; }
        public void setFinanceDataListKeys(List<String> financeDataListKeys) { this.financeDataListKeys = financeDataListKeys; }

        public Integer getWager() { return wager; }
        public void setWager(Integer wager) { this.wager = wager; }

        public Object getSportLimit() { return sportLimit; }
        public void setSportLimit(Object sportLimit) { this.sportLimit = sportLimit; }

        public Integer getRequiredBetsToWager() { return requiredBetsToWager; }
        public void setRequiredBetsToWager(Integer requiredBetsToWager) { this.requiredBetsToWager = requiredBetsToWager; }

        public Object getBasicLimit() { return basicLimit; }
        public void setBasicLimit(Object basicLimit) { this.basicLimit = basicLimit; }

        public Object getDepositMultiplier() { return depositMultiplier; }
        public void setDepositMultiplier(Object depositMultiplier) { this.depositMultiplier = depositMultiplier; }

        public List<FinanceData> getFinanceDataList() { return financeDataList; }
        public void setFinanceDataList(List<FinanceData> financeDataList) { this.financeDataList = financeDataList; }

        public String getBonusType() { return bonusType; }
        public void setBonusType(String bonusType) { this.bonusType = bonusType; }

        public String getActivationMethod() { return activationMethod; }
        public void setActivationMethod(String activationMethod) { this.activationMethod = activationMethod; }

        public String getDebitMethod() { return debitMethod; }
        public void setDebitMethod(String debitMethod) { this.debitMethod = debitMethod; }

        public String getWagerType() { return wagerType; }
        public void setWagerType(String wagerType) { this.wagerType = wagerType; }

        public String getDefaultLanguage() { return defaultLanguage; }
        public void setDefaultLanguage(String defaultLanguage) { this.defaultLanguage = defaultLanguage; }

        public Long getTimeToWager() { return timeToWager; }
        public void setTimeToWager(Long timeToWager) { this.timeToWager = timeToWager; }

        public Object getTimeToUse() { return timeToUse; }
        public void setTimeToUse(Object timeToUse) { this.timeToUse = timeToUse; }
    }

    public static class LocalizedInfo {
        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("language")
        private String language;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
    }

    public static class FinanceData {
        @JsonProperty("maxBonusAmount")
        private Integer maxBonusAmount;

        @JsonProperty("currency")
        private String currency;

        public Integer getMaxBonusAmount() { return maxBonusAmount; }
        public void setMaxBonusAmount(Integer maxBonusAmount) { this.maxBonusAmount = maxBonusAmount; }

        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
    }
}