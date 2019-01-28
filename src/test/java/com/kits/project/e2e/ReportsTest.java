package com.kits.project.e2e;

import com.kits.project.e2e.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReportsTest {
    private WebDriver browser;
    ReportsPage reportsPage;
    LoginPage loginPage;
    HomePage homePage;
    DashboardPage dashboardPage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/login");
        reportsPage = PageFactory.initElements(browser, ReportsPage.class);
        loginPage = PageFactory.initElements(browser, LoginPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
        dashboardPage = PageFactory.initElements(browser, DashboardPage.class);
    }

    @Test
    public void reportsTest() {
        loginPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/login?returnUrl=%2Fhome", browser.getCurrentUrl());
        loginPage.loginAs("aa", "aa");
        homePage.logoutDropdownDisplayed();
        homePage.logoutDropDownClickable();
        homePage.getLogoutDropdown().click();
        homePage.logoutLinkClickable();
        homePage.getDashboardLink().click();
        assertEquals("http://localhost:4200/admin-dashboard", browser.getCurrentUrl());

        dashboardPage.ensureShowReportsButtonIsDisplayed();
        dashboardPage.getShowReportsButton().click();
        assertEquals("http://localhost:4200/admin-dashboard/reports", browser.getCurrentUrl());
        reportsPage.ensureStartDateIsDisplayed();
        reportsPage.getReports(new Date(), new Date());
        reportsPage.getSubmitButton().click();
        assert(Float.parseFloat(reportsPage.getTotal().getText()) >= 0);
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
