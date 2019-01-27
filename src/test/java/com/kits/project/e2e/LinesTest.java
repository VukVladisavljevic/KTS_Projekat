package com.kits.project.e2e;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LinesPage;
import com.kits.project.e2e.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import sun.rmi.runtime.Log;

import static org.junit.Assert.assertEquals;

public class LinesTest {
    private WebDriver browser;
    private HomePage homePage;
    private LinesPage linesPage;
    private LoginPage loginPage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/lines");
        linesPage = PageFactory.initElements(browser, LinesPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
        loginPage = PageFactory.initElements(browser, LoginPage.class);
    }

    @Test
    public void addNewLineTest() {
        loginPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/login?returnUrl=%2Fhome", browser.getCurrentUrl());
        loginPage.loginAs("aa", "aa");
        homePage.ensureBusIsVisible();
        homePage.linesLinkIsDisplayed();
        homePage.getLinesLink().click();
        assertEquals("http://localhost:4200/lines", browser.getCurrentUrl());
        linesPage.ensureIsDisplayed();
        int numberOfLines = linesPage.getLinesTableSize();
        linesPage.getAddButton().click();
        linesPage.ensureIsAddDialogDisplayed();
        linesPage.ensureIsLineNameDisplayed();
        assertEquals(linesPage.isAddLineDisabled(), true);
        assertEquals(linesPage.isAddStationDisabled(), true);
        assertEquals(linesPage.isLineNameEmpty(), true);

        linesPage.ensureIsSelectStationDisplayed();
        linesPage.addLineName("New Line");

        assertEquals(linesPage.isAddLineDisabled(), true);
        assertEquals(linesPage.isAddStationDisabled(), true);

        linesPage.getStationSelect().click();
        linesPage.getFirstOption().click();
        assertEquals(linesPage.isAddLineDisabled(), true);
        assertEquals(linesPage.isAddStationDisabled(), false);
        linesPage.getAddStationButton().click();
        assertEquals(linesPage.isAddLineDisabled(), true);
        assertEquals(linesPage.isAddStationDisabled(), true);
        assertEquals(linesPage.getStationsTableSize(), 1);

        linesPage.getStationSelect().click();
        linesPage.getFirstOption().click();
        assertEquals(linesPage.isAddLineDisabled(), true);
        assertEquals(linesPage.isAddStationDisabled(), false);
        linesPage.getAddStationButton().click();
        assertEquals(linesPage.isAddLineDisabled(), false);
        assertEquals(linesPage.isAddStationDisabled(), true);
        assertEquals(linesPage.getStationsTableSize(), 2);

        linesPage.ensureDeleteStationIsDisplayed();
        linesPage.getDeleteStaton().click();

        assertEquals(linesPage.getStationsTableSize(), 1);

        linesPage.getStationSelect().click();
        linesPage.getFirstOption().click();
        assertEquals(linesPage.isAddLineDisabled(), true);
        assertEquals(linesPage.isAddStationDisabled(), false);
        linesPage.getAddStationButton().click();
        assertEquals(linesPage.isAddLineDisabled(), false);
        assertEquals(linesPage.isAddStationDisabled(), true);
        assertEquals(linesPage.getStationsTableSize(), 2);

        linesPage.getAddLineButton().click();
        linesPage.ensureIsDisplayed();
        linesPage.ensureIsAddDialogHidden();
        linesPage.ensureIsAdded(numberOfLines);
        assertEquals(linesPage.getLinesTableSize(), numberOfLines+1);
        numberOfLines = numberOfLines + 1;

        assertEquals(linesPage.getLastTableElement(numberOfLines-1).getText(), "New Line");

        linesPage.getDeleteButton(numberOfLines-1).click();
        linesPage.ensureIsDeleted(numberOfLines);
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
