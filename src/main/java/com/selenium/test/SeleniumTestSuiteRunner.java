package com.selenium.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.selenium.dto.TestCase;
import com.selenium.dto.TestSuite;
import com.selenium.dto.ValidationPoint;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SeleniumTestSuiteRunner {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            TestSuite testSuite = mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("am-web-test-case.yaml"), TestSuite.class);
            System.out.println(ReflectionToStringBuilder.toString(testSuite, ToStringStyle.MULTI_LINE_STYLE));
            executeTestSuite(testSuite);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void executeTestSuite(TestSuite testSuite) throws IOException {
        String chromeDriverPath = "/Users/surobinda/software/chromedriver/chromedriver" ;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(/*"--headless", */"--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
        WebDriver driver = new ChromeDriver(options);

        List<TestCase> testCases = testSuite.getTestCases();
        for (TestCase testCase: testCases) {
            System.out.println("going to execute test case: " + testCase.getTestCaseName());
            driver.get(testCase.getUrl());
            WebElement element = driver.findElement(By.xpath(testCase.getPageValidationWebElement()));
            if (element == null) {
                System.out.println("Failed to load page via element: " + testCase.getPageValidationWebElement());
                driver.quit();
                System.exit(1);
            } else {
                List<ValidationPoint> validationPoints = testCase.getValidationPoints();
                for (ValidationPoint validationPoint: validationPoints) {
                    System.out.println("Running validation point: " + validationPoint.getValidationName());
                    if (validationPoint.getDataElementXpath() != null) {
                        WebElement validationPointWebElement = driver.findElement(By.xpath(validationPoint.getDataElementXpath()));
                        validateDataElement(validationPointWebElement, validationPoint.getDataElementValidationValue());
                    } else if (validationPoint.getDataElementClass() != null) {
                        WebElement validationPointWebElement = driver.findElement(By.cssSelector(validationPoint.getDataElementClass()));
                        validateElementRule(validationPointWebElement, validationPoint.getDataElementValidationRule());
                    }

                }
            }
        }
        System.out.println("Finishing test suite: ");
        driver.quit();
    }

    public static void validateDataElement(WebElement webElement, String validationData) {
        if (webElement == null) {
            System.out.println("Validation failed for data  or rule:" + validationData);
        } else {
            System.out.println(webElement.getText());
            if (webElement.getText().equals(validationData)) {
                System.out.println("Validation passed, expected: " + validationData + " and found: " + webElement.getText());
            }  else {
                System.out.println("Validation failed, expected: " + validationData + " but found: " + webElement.getText());            }
        }
    }

    public static void validateElementRule(WebElement webElement, final String validationRule) {
        if (webElement == null) {
            System.out.println("Validation failed for rule:" + validationRule);
        } else {
            String webElementText = webElement.getText();
            System.out.println(webElementText);
            if (validationRule.equalsIgnoreCase("currentDate")) {
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                if (dateFormat.format(new Date()).equalsIgnoreCase(webElementText)) {
                    System.out.println("Validation passed, expected: " + dateFormat.format(new Date()) + " and found: " + webElementText);
                } else {
                    System.out.println("Validation failed, expected: " + dateFormat.format(new Date()) + " but found: " + webElementText);
                }
            }
        }
    }
}
