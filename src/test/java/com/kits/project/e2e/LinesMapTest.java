package com.kits.project.e2e;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LinesMapPage;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LinesMapTest {
    private WebDriver browser;
    private LinesMapPage linesPage;
    private HomePage homePage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/");
        linesPage = PageFactory.initElements(browser, LinesMapPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
    }

    @Test
    public void showLinesOnMapTest() {
        homePage.ensureBusIsVisible();
        homePage.linesMapLinkIsDisplayed();
        homePage.getLinesMapLink().click();
        linesPage.lineSelectDisplayed();
        assertEquals("http://localhost:4200/lines-map", browser.getCurrentUrl());
        linesPage.getSelectLine().click();
        linesPage.firstOptionDisplayed();
        linesPage.getFirstOption().click();
        linesPage.stationsDisplayed();
        List<WebElement> stations = linesPage.findAllStations();

        assertNotEquals(stations.size(), 0);
        linesPage.lineSelectDisplayed();
        linesPage.getSelectLine().click();
        linesPage.secondOptionDisplayed();
        linesPage.getSecondOption().click();

        linesPage.stationsDisplayed();
        List<WebElement> newStations = linesPage.findAllStations();
        assertNotEquals(newStations.size(), 0);
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
