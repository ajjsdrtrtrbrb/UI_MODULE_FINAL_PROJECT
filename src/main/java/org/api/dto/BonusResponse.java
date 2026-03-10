package org.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BonusResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("bonusStatus")
    private String bonusStatus;

    @JsonProperty("archived")
    private Boolean archived;

    @JsonProperty("startTime")
    private long startTime;

    @JsonProperty("endTime")
    private long endTime;

    @JsonProperty("bonusType")
    private String bonusType;

    @JsonProperty("localizedInfoList")
    private List<BonusRequest.LocalizedInfo> localizedInfoList;

    public String getId() {
        return id;
    }

    public String getBonusStatus() {
        return bonusStatus;
    }

    public Boolean isArchived() {
        return archived;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getBonusType() {
        return bonusType;
    }

    public List<BonusRequest.LocalizedInfo> getLocalizedInfoList() {
        return localizedInfoList;
    }
}