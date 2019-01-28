package com.kits.project.e2e;
import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest {
    private WebDriver browser;
    LoginPage loginPage;
    HomePage homePage;

    @Before
    public void setupSelenium() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver");

        System.setProperty("webdriver.chrome.driver", "chromedriver");


        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/login");
        loginPage = PageFactory.initElements(browser, LoginPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
    }

    @Test
    public void testLogin() {
        loginPage.ensureIsDisplayed();
        loginPage.ensurePasswordIsDisplayed();
        loginPage.ensureLoginButtonIsDisplayed();
        loginPage.ensureIsClickable();
        loginPage.getLoginbutton().click();
        loginPage.loginAs("user", "password");
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());

        loginPage.loginAs("aa", "password");
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());

        loginPage.loginAs("aa", "aa");

        //Ukoliko se admin uloguje treba da mu se prikazu sledeci linkovi, ako je samo user ne treba
        homePage.stationsLinkIsDisplayed();
        homePage.linesLinkIsDisplayed();
        homePage.priceListLinkIsDisplayed();
        homePage.stationsLinkIsDisplayed();
        boolean admin = homePage.adminLinksVisible();
        assertEquals(admin, true);
        assertEquals("http://localhost:4200/home", browser.getCurrentUrl());
        homePage.logoutDropdownDisplayed();
        homePage.logoutDropDownClickable();
        homePage.getLogoutDropdown().click();
        homePage.logoutLinkClickable();
        homePage.getSignoutLink().click();
        //Posle logout vraca se na login stranicu ali linkovi za admina se ne vide
        loginPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
        boolean publicUser = homePage.adminLinksVisible();
        assertEquals(publicUser, false);

        loginPage.loginAs("user", "aa");
        homePage.logoutDropdownDisplayed();
        homePage.logoutDropDownClickable();
        assertEquals("http://localhost:4200/home", browser.getCurrentUrl());
        boolean loggedIdUser = homePage.adminLinksVisible();
        assertEquals(loggedIdUser, false);
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }

}

