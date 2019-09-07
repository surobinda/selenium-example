package com.selenium.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class TestCase {
    private String testCaseName;
    private String url;
    private String pageValidationWebElement;
    List<ValidationPoint> validationPoints;

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageValidationWebElement() {
        return pageValidationWebElement;
    }

    public void setPageValidationWebElement(String pageValidationWebElement) {
        this.pageValidationWebElement = pageValidationWebElement;
    }

    public List<ValidationPoint> getValidationPoints() {
        return validationPoints;
    }

    public void setValidationPoints(List<ValidationPoint> validationPoints) {
        this.validationPoints = validationPoints;
    }
}
