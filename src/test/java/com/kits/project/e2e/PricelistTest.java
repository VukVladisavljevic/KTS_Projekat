package com.kits.project.e2e;

import com.kits.project.e2e.pages.CurrentPricelistPage;
import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LoginPage;
import com.kits.project.e2e.pages.PricelistPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class PricelistTest {

    private WebDriver browser;
    PricelistPage pricelistPage;
    CurrentPricelistPage currentPricelistPage;
    LoginPage loginPage;
    HomePage homePage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/pricelist");
        pricelistPage = PageFactory.initElements(browser, PricelistPage.class);
        currentPricelistPage = PageFactory.initElements(browser, CurrentPricelistPage.class);
        loginPage = PageFactory.initElements(browser, LoginPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
    }

    @Test
    public void addNewPricelist() {

        loginPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/login?returnUrl=%2Fhome", browser.getCurrentUrl());
        loginPage.loginAs("aa", "aa");
        homePage.ensureBusIsVisible();
        homePage.priceListLinkIsDisplayed();

        homePage.getShowPriceListLink().click();

        assertEquals("http://localhost:4200/showpricelist", browser.getCurrentUrl());
        currentPricelistPage.ensureIsTableVisible();
        int numberOfCurrentPricelists = currentPricelistPage.getPricelistTableSize();
        homePage.getPriceListLink().click();
        assertEquals("http://localhost:4200/pricelist", browser.getCurrentUrl());
        pricelistPage.ensureIsDisplayed();
        int numberOfPreclists = pricelistPage.getPricelistTableSize();
        pricelistPage.getAddButton().click();
        pricelistPage.ensureIsAddDialogDisplayed();
        pricelistPage.getStartDate().click();
        pricelistPage.getEndDate().click();
        pricelistPage.getPrice().sendKeys("65");
        pricelistPage.getAddPricelistButton().click();
        Alert addedAlert = null;
        try {
            addedAlert = pricelistPage.waitForAlert(browser);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;

        assertEquals(addedAlert.getText(), "Pricelist successfully added.");
        addedAlert.accept();
        pricelistPage.ensureIsAddDialogHidden();
        pricelistPage.ensureIsAdded(numberOfPreclists);
        assertEquals(pricelistPage.getPricelistTableSize(), numberOfPreclists + 1);

        numberOfPreclists = pricelistPage.getPricelistTableSize();
        pricelistPage.getAddButton().click();
        pricelistPage.ensureIsAddDialogDisplayed();
        pricelistPage.getStartDate().click();
        pricelistPage.getEndDate().click();
        pricelistPage.getPrice().sendKeys("65");
        pricelistPage.getAddPricelistButton().click();
        addedAlert = null;
        try {
            addedAlert = pricelistPage.waitForAlert(browser);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;

        assertEquals(addedAlert.getText(), "Pricelist for picked data range already exists!");
        addedAlert.accept();
        pricelistPage.getCloseModal().click();
        pricelistPage.ensureIsAddDialogHidden();
        pricelistPage.ensureIsNotAdded(numberOfPreclists);
        assertEquals(pricelistPage.getPricelistTableSize(), numberOfPreclists);

        homePage.priceListLinkIsDisplayed();

        homePage.getShowPriceListLink().click();

        assertEquals("http://localhost:4200/showpricelist", browser.getCurrentUrl());
        currentPricelistPage.ensureIsTableVisible();
        assertEquals(currentPricelistPage.getPricelistTableSize(),numberOfCurrentPricelists+1 );

    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
