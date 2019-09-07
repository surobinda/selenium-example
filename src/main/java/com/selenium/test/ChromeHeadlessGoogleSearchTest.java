package com.selenium.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class ChromeHeadlessGoogleSearchTest {
    private static String searchText = "selenium headless chrome java" ;

    public static void main(String[] args) throws IOException {
        String chromeDriverPath = "/Users/surobinda/software/chromedriver/chromedriver" ;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(/*"--headless", */"--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
        WebDriver driver = new ChromeDriver(options);

        // Get the login page
        driver.get("https://www.google.com/");

        // Search for search text
        driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(searchText);

        // Locate the login button and click on it
        driver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[1]")).click();

        int size = driver.findElements(By.xpath("//*[@id='rso']/div[3]/div/div[1]")) != null ? driver.findElements(By.xpath("//*[@id='rso']/div[3]/div/div[1]")).size() : 0;

        if (size > 0) {
            System.out.println("Found search results on : " + searchText);
        } else {
            System.out.println("Didn't find anything based on your search text: " + searchText);
        }

        // Take a screenshot of the current page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.png"));

        // Logout
        driver.findElement(By.id("logout")).click();
        driver.quit();
    }
}
