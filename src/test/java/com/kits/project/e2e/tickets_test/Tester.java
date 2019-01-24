package com.kits.project.e2e.tickets_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Tester {
    private WebDriver browser;
//    LoginPage loginPage;
//    HomePage homePage;
//    ResultPage resultPage;
//
//    private String homeURL = "http://automationpractice.com/index.php";
//    private String authURL = "http://automationpractice.com/index.php?controller=authentication";
//
//
//    @BeforeMethod
//    public void setupSelenium() {
//        //  System.setProperty("webdriver.gecko.driver", "geckodriver");
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        //  browser = new FirefoxDriver();
//        browser = new FirefoxDriver();
//
//        browser.get(homeURL);
//        loginPage = PageFactory.initElements(browser, LoginPage.class);
//        homePage = PageFactory.initElements(browser, HomePage.class);
//        resultPage = PageFactory.initElements(browser, ResultPage.class);
//
//    }
//
//    @Test
//    public void testLogin() {
//
//        assertEquals(homeURL, browser.getCurrentUrl());
//        homePage.ensureIsClickable();
//        homePage.getLoginButton().click();
//
//        //login without email
//        // assertEquals("http://automationpractice.com/index.php?controller=authentication&back=my-account", browser.getCurrentUrl());
//        loginPage.ensureIsClickable();
//        loginPage.loginAs("", "invalid");
//
//        assertEquals(authURL, browser.getCurrentUrl());
//        loginPage.alertElementDisplayed();
//
//        //login without password
//        loginPage.loginAs("test@kts.com", "");
//
//        assertEquals(authURL, browser.getCurrentUrl());
//        loginPage.alertElementDisplayed();
//
//
//        //login with invalid credentials
//        loginPage.loginAs("test_invalid@kts.com", "johnsmith");
//
//        assertEquals(authURL, browser.getCurrentUrl());
//        loginPage.alertElementDisplayed();
//
//        //successful login
//        loginPage.loginAs("test@kts.com", "johnsmith");
//        assertEquals("http://automationpractice.com/index.php?controller=my-account", browser.getCurrentUrl());
//        browser.get(homeURL);
//
//        homePage.ensureIsSearchClickable();
//        homePage.getSearchField().sendKeys("shirt");
//        homePage.getSearchButton().click();
//
//
//        resultPage.ensureIsResultVisible();
//        assertEquals(resultPage.resultSize(), "1 result has been found.");
//
//        browser.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//    }

    @AfterMethod
    public void closeSelenium() {
        browser.quit();
    }

}
