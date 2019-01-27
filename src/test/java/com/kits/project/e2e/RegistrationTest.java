package com.kits.project.e2e;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class RegistrationTest {
    private WebDriver browser;
    RegistrationPage registerPage;
    HomePage homePage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/register");
        registerPage = PageFactory.initElements(browser, RegistrationPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
    }

    @BeforeTest
    public void setupTest() {
        registerPage.ensureFirstNameIsDisplayed();
        registerPage.ensureLastNameIsDisplayed();
        registerPage.ensureEmailIsDisplayed();
        registerPage.ensureUsernameIsDisplayed();
        registerPage.ensurePasswordIsDisplayed();
        registerPage.ensureConfirmPasswordIsDisplayed();
    }

    @Test
    public void registerTest() {
        registerPage.registerAs("Luka", "Bilic", "testmail@gmail.com", "test", "test1", "test1");
        assertEquals("http://localhost:4200/home", browser.getCurrentUrl());

    }

    @Test
    public void invalidEmailTest() {
        registerPage.registerAs("Luka", "Bilic", "testmail", "test", "test1", "test1");
        assertNotNull(registerPage.getEmailValidError());
        assertEquals("http://localhost:4200/register", browser.getCurrentUrl());
    }

    @Test
    public void takenUsernameTest() {
        registerPage.registerAs("Luka", "Bilic", "testmail@gmail.com", "aa", "test1", "test1");
        assertNotNull(registerPage.getUsernameTakenError());
        assertEquals("http://localhost:4200/register", browser.getCurrentUrl());
    }

    @Test
    public void weakPasswordTest() {
        registerPage.registerAs("Luka", "Bilic", "testmail@gmail.com", "test", "test", "test");
        assertNotNull(registerPage.getWeakPasswordError());
        assertEquals("http://localhost:4200/register", browser.getCurrentUrl());
    }

    @Test
    public void usernameContainsSpaceError() {
        registerPage.registerAs("Luka", "Bilic", "testmail@gmail.com", "test test", "test", "test");
        assertNotNull(registerPage.getUsernameContainSpaceError());
        assertEquals("http://localhost:4200/register", browser.getCurrentUrl());
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
