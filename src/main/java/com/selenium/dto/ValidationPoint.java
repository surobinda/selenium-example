package com.selenium.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ValidationPoint {
    private String validationName;
    private String dataElementXpath;
    private String dataElementValidationValue;
    private String dataElementClass;
    private String dataElementValidationRule;

    public String getValidationName() {
        return validationName;
    }

    public void setValidationName(String validationName) {
        this.validationName = validationName;
    }

    public String getDataElementXpath() {
        return dataElementXpath;
    }

    public void setDataElementXpath(String dataElementXpath) {
        this.dataElementXpath = dataElementXpath;
    }

    public String getDataElementValidationValue() {
        return dataElementValidationValue;
    }

    public void setDataElementValidationValue(String dataElementValidationValue) {
        this.dataElementValidationValue = dataElementValidationValue;
    }

    public String getDataElementClass() {
        return dataElementClass;
    }

    public void setDataElementClass(String dataElementClass) {
        this.dataElementClass = dataElementClass;
    }

    public String getDataElementValidationRule() {
        return dataElementValidationRule;
    }

    public void setDataElementValidationRule(String dataElementValidationRule) {
        this.dataElementValidationRule = dataElementValidationRule;
    }
}
