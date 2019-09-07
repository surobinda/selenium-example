package com.selenium.test;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ChromeHeadlessNDTVTest {
    private static String userName = "suro4321" ;
    private static String password = "suro1234" ;

    public static void main(String[] args) throws IOException {
        String chromeDriverPath = "/Users/surobinda/software/chromedriver/chromedriver" ;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(/*"--headless", */"--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
        WebDriver driver = new ChromeDriver(options);

        // Get the login page
        driver.get("https://www.ndtv.com/");


        List<WebElement> elements = driver.findElements(By.cssSelector("[data-tb-owning-region-name='Top Stories']"));
        if (CollectionUtils.isEmpty(elements)) {
            System.out.println("There is no top stories available today");
        } else {
            System.out.println(String.format("There are %s top stories available today %s, following are headlines", elements.size(), new Date()));

            for (WebElement element:elements) {
                System.out.println(element.findElement(By.cssSelector(".item-title")).getText());
            }
        }




        // Take a screenshot of the current page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.png"));

        // Logout
        //driver.findElement(By.id("logout")).click();
        driver.quit();
    }
}
