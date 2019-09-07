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

public class ChromeHeadlessTest {
    private static String userName = "suro4321" ;
    private static String password = "suro1234" ;

    public static void main(String[] args) throws IOException {
        String chromeDriverPath = "/Users/surobinda/software/chromedriver/chromedriver" ;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(/*"--headless", */"--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
        WebDriver driver = new ChromeDriver(options);

        // Get the login page
        driver.get("https://news.ycombinator.com/login?goto=news");

        // Search for username / password input and fill the inputs
        driver.findElement(By.xpath("//input[@name='acct']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        // Locate the login button and click on it
        driver.findElement(By.xpath("//input[@value='login']")).click();

        if(driver.getCurrentUrl().equals("https://news.ycombinator.com/login")){
            System.out.println("Incorrect credentials");
            driver.quit();
            System.exit(1);
        }else{
            System.out.println("Successfuly logged in");
        }

        // Take a screenshot of the current page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.png"));

        // Logout
        driver.findElement(By.id("logout")).click();
        driver.quit();
    }
}
