package com.samaksh.weather_api.model;

public class VisualCrossingsResponse {
    private String resolveAddress;
    private CurrentCondition currentConditions;
    private String description;

    public String getResolvedAddress() {
        return resolveAddress;
    }

    public void setResolvedAddress(String resolveAddress) {
        this.resolveAddress = resolveAddress;
    }

    public CurrentCondition getCurrentConditions() {
        return currentConditions;
    }

    public void setCurrentConditions(CurrentCondition currentConditions) {
        this.currentConditions = currentConditions;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
