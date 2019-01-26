package com.kits.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LiveLocationPage {
    private WebDriver driver;

    @FindBy(xpath= "(//img[contains(@src,'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png')])")
    private WebElement stations;

    @FindBy(xpath="(//img[contains(@src,'https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi-dotless2_hdpi.png')])")
    private WebElement busLocation;

    @FindBy(id= "mat-select-0")
    private WebElement selectLine;

    @FindBy(xpath = "//mat-option[@id='mat-option-0']/span")
    private WebElement firstOption;

    @FindBy(xpath = "//mat-option[@id='mat-option-1']/span")
    private WebElement secondOption;

    public LiveLocationPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> findAllStations() {
        return driver.findElements(By.xpath("(//img[contains(@src,'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png')])"));
    }

    public WebElement getBusLocation() {
        return busLocation;
    }

    public void setBusLocation(WebElement busLocation) {
        this.busLocation = busLocation;
    }

    public WebElement getSelectLine() {
        return selectLine;
    }

    public void setSelectLine(WebElement selectLine) {
        this.selectLine = selectLine;
    }

    public WebElement getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(WebElement firstOption) {
        this.firstOption = firstOption;
    }

    public WebElement getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(WebElement secondOption) {
        this.secondOption = secondOption;
    }

    public void stationsDisplayed() {
        try {
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOf(this.stations));
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {

            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfAllElements(this.stations));


        }
    }

    public void busDisplayed() {
        try {
            (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.visibilityOf(this.busLocation));
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {

            (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.visibilityOfAllElements(this.busLocation));


        }
    }

    public void lineSelectDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(selectLine));
    }

    public void firstOptionDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(firstOption));
    }

    public void secondOptionDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(secondOption));
    }


}
