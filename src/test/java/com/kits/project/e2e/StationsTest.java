package com.kits.project.e2e;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LinesPage;
import com.kits.project.e2e.pages.LoginPage;
import com.kits.project.e2e.pages.StationsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class StationsTest {
    private WebDriver browser;
    private HomePage homePage;
    private StationsPage stationsPage;
    private LoginPage loginPage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://localhost:4200/login");
        stationsPage = PageFactory.initElements(browser, StationsPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
        loginPage = PageFactory.initElements(browser, LoginPage.class);
    }

    @Test
    public void addStationTest() {
        loginPage.ensureIsDisplayed();
        loginPage.loginAs("aa", "aa");
        homePage.ensureBusIsVisible();
        assertEquals("http://localhost:4200/home", browser.getCurrentUrl());

        homePage.stationsLinkIsDisplayed();
        homePage.getStationsLink().click();
        stationsPage.mapDisplayed();
        WebElement elem =  stationsPage.getMap();

        int width = elem.getSize().getWidth();
        int height = elem.getSize().getHeight();
        System.out.println(width);
        System.out.println((height/2));
        Actions act = new Actions(browser);
        act.moveToElement(elem).moveByOffset((width/2), (height/2)).click().perform();
        stationsPage.stationNameDisplayed();
        assertEquals(stationsPage.isAddStationDisabled(), true);

        stationsPage.addStationNameAndAddress("Station", "Jug Bogdana");
        assertEquals(stationsPage.isAddStationDisabled(), false);
        stationsPage.getAddStationButton().click();
    }

    @After
    public void closeSelenium() {
        browser.quit();
    }
}
