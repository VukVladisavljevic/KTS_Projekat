package com.kits.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LinesMapPage {
    private WebDriver driver;

    @FindBy(xpath= "(//img[contains(@src,'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png')])")
    private WebElement stations;

    @FindBy(id= "mat-select-0")
    private WebElement selectLine;

    @FindBy(xpath = "//mat-option[@id='mat-option-0']/span")
    private WebElement firstOption;

    @FindBy(xpath = "//mat-option[@id='mat-option-1']/span")
    private WebElement secondOption;

    public LinesMapPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getStations() {
        return stations;
    }

    public void setStations(WebElement stations) {
        this.stations = stations;
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

    public List<WebElement> findAllStations() {
        return driver.findElements(By.xpath("(//img[contains(@src,'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png')])"));
    }

    public List<WebElement> findAllOptions() {
        return driver.findElements(By.xpath("//mat-option/span"));
    }
}
