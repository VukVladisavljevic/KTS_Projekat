package com.kits.project.e2e;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LiveLocationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LiveLocationTest {
    private WebDriver browser;
    private LiveLocationPage liveLocationPage;
    private HomePage homePage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/home");
        liveLocationPage = PageFactory.initElements(browser, LiveLocationPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
    }

    @Test
    public void showLinesOnMapTest() {
        homePage.ensureBusIsVisible();
        homePage.getLiveLocationLink().click();
        liveLocationPage.lineSelectDisplayed();
        assertEquals("http://localhost:4200/live-location", browser.getCurrentUrl());
        liveLocationPage.getSelectLine().click();
        liveLocationPage.firstOptionDisplayed();
        liveLocationPage.getFirstOption().click();
        liveLocationPage.stationsDisplayed();
        List<WebElement> stations = liveLocationPage.findAllStations();

        assertNotEquals(stations.size(), 0);
        liveLocationPage.busDisplayed();
        liveLocationPage.lineSelectDisplayed();
        liveLocationPage.getSelectLine().click();
        liveLocationPage.secondOptionDisplayed();
        liveLocationPage.getSecondOption().click();

        liveLocationPage.stationsDisplayed();
        List<WebElement> newStations = liveLocationPage.findAllStations();
        assertNotEquals(newStations.size(), 0);
        liveLocationPage.stationsDisplayed();
        liveLocationPage.busDisplayed();
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
