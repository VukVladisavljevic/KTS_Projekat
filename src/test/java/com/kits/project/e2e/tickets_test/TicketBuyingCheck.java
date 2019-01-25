package com.kits.project.e2e.tickets_test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class TicketBuyingCheck {
    private WebDriver browser;
    private MainTicketsPage mainTicketsPage;
    private ShowOwnedTicketsPage ownedTicketsPage;




    @BeforeMethod
    public void setupSelenium() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        ///System.setProperty("webdriver.firefox.driver", "firefoxdriver");
        //  browser = new FirefoxDriver();
        browser = new FirefoxDriver();

        browser.get("http://localhost:4200");
        mainTicketsPage = PageFactory.initElements(browser, MainTicketsPage.class);
        ownedTicketsPage = PageFactory.initElements(browser, ShowOwnedTicketsPage.class);

    }

    @Test
    public void testBuyingOneUseTicket() {
        browser.get("http://localhost:4200");
        assertEquals("http://localhost:4200", browser.getCurrentUrl());
    }

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
