package com.kits.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StationsPage {
    private WebDriver driver;

    @FindBy(xpath = "//div/div/div/div/div[3]")
    private WebElement map;

    @FindBy(id="mat-input-0")
    private WebElement stationName;


    @FindBy(id="mat-input-1")
    private WebElement stationAddress;

    @FindBy(css="button.btn.btn-primary")
    private WebElement addStationButton;


    public StationsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void mapDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(map));
    }

    public void stationNameDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(stationName));
    }

    public WebElement getMap() {
        return map;
    }

    public void setMap(WebElement map) {
        this.map = map;
    }

    public WebElement getStationName() {
        return stationName;
    }

    public boolean isAddStationDisabled() {
        return !this.addStationButton.isEnabled();
    }

    public void setStationName(WebElement stationName) {
        this.stationName = stationName;
    }

    public WebElement getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(WebElement stationAddress) {
        this.stationAddress = stationAddress;
    }

    public WebElement getAddStationButton() {
        return addStationButton;
    }

    public void setAddStationButton(WebElement addStationButton) {
        this.addStationButton = addStationButton;
    }

    public void addStationNameAndAddress(String name, String address) {
        stationName.clear();
        stationName.sendKeys(name);
        stationAddress.clear();
        stationAddress.sendKeys(address);
    }
}
