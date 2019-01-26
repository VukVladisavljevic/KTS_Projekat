package com.kits.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LinesPage {

    private WebDriver driver;

    @FindBy(css= "button.btn.btn-secondary")
    private WebElement addStationButton;

    @FindBy(css= "button.btn.btn-primary")
    private WebElement addLineButton;

    @FindBy(css = "button.btn.btn-dark")
    private WebElement addButton;

    @FindBy(xpath = "//input")
    private WebElement lineName;

    @FindBy(xpath = "//mat-select/div/div/span")
    private WebElement stationSelect;

    @FindBy(xpath = "//mat-option[2]/span")
    private WebElement secondOption;

    @FindBy(xpath = "//mat-option/span")
    private WebElement firstOption;

    @FindBy(xpath = "//mat-dialog-container[@id='mat-dialog-0']/app-add-line/div/mat-dialog-content/table/tbody/tr/td[3]/button")
    private WebElement deleteStation;

    @FindBy(xpath = "//tr[last()]/td[0]")
    private WebElement lastTdtName;

    public LinesPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAddStationButton() {
        return addStationButton;
    }

    public void setAddStationButton(WebElement addStationButton) {
        this.addStationButton = addStationButton;
    }

    public WebElement getAddLineButton() {
        return addLineButton;
    }

    public void setAddLineButton(WebElement addLineButton) {
        this.addLineButton = addLineButton;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public void setAddButton(WebElement addButton) {
        this.addButton = addButton;
    }

    public int getLinesTableSize() {
        return this.driver.findElements(By.cssSelector("tr")).size();
    }

    public WebElement getLineName() {
        return lineName;
    }

    public void setLineName(WebElement lineName) {
        this.lineName = lineName;
    }

    public WebElement getStationSelect() {
        return stationSelect;
    }

    public WebElement getDeleteStaton() {
        return deleteStation;
    }

    public void setDeleteStaton(WebElement selectedStaton) {
        this.deleteStation = selectedStaton;
    }

    public void setStationSelect(WebElement stationSelect) {
        this.stationSelect = stationSelect;
    }

    public WebElement getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(WebElement secondOption) {
        this.secondOption = secondOption;
    }

    public WebElement getFirstOption() {
        return firstOption;
    }

    public WebElement getLastTdtName() {
        return lastTdtName;
    }

    public void setLastTdtName(WebElement lastTdtName) {
        this.lastTdtName = lastTdtName;
    }

    public void setFirstOption(WebElement firstOption) {
        this.firstOption = firstOption;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addButton));
    }

    public void ensureIsAddDialogDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addLineButton));
    }

    public void ensureIsLineNameDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(lineName));
    }

    public void ensureDeleteStationIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(deleteStation));
    }

    public void ensureIsAddDialogHidden() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.invisibilityOf(this.driver.findElement(By.xpath("//mat-dialog-content/table/tbody/tr"))));
    }


    public boolean isAddStationDisabled() {
        return !this.addStationButton.isEnabled();
    }

    public boolean isAddLineDisabled() {
        return !this.addLineButton.isEnabled();
    }

    public boolean isLineNameEmpty() {
        return this.lineName.getText().isEmpty();
    }

    public void addLineName(String name) {
        lineName.clear();
        lineName.sendKeys(name);
    }

    public void ensureIsSelectStationDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(stationSelect));
    }

    public void ensureIsAdded(int previousNoOfStudents) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector("tr"), previousNoOfStudents + 1));
    }

    public void ensureIsDeleted(int previousNoOfStudents) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//tr[" + (previousNoOfStudents + 1) + "]")));
    }


    public int getStationsTableSize() {
        return this.driver.findElements(By.xpath("//mat-dialog-content/table/tbody/tr")).size();
    }

    public WebElement getLastTableElement(int index) {
        return this.driver.findElement(By.xpath("//tr[" + index + "]/td"));
    }

    public WebElement getDeleteButton(int index) {
        return this.driver.findElement(By.xpath("//tr[" + index + "]/td[2]/button"));
    }
}
