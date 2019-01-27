package com.kits.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShowTimeSchedulesPage {

    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/mat-dialog-actions/button")
    private WebElement dialogCloseButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/div/mat-dialog-content/table/tbody/tr[1]/td[2]/button")
    private WebElement dialogDeleteButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/div/div/label[1]")
    private WebElement workingDayOption;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/div/div/label[2]")
    private WebElement saturdayOption;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/div/div/label[3]")
    private WebElement sundayOption;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/div/mat-dialog-content/table/tbody/tr")
    private WebElement departuresTable;

    public ShowTimeSchedulesPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDialogCloseButton() {
        return dialogCloseButton;
    }

    public WebElement getDeparturesTable() {
        return departuresTable;
    }

    public void setDeparturesTable(WebElement departuresTable) {
        this.departuresTable = departuresTable;
    }

    public void setDialogCloseButton(WebElement dialogCloseButton) {
        this.dialogCloseButton = dialogCloseButton;
    }

    public WebElement getDialogDeleteButton() {
        return dialogDeleteButton;
    }

    public void setDialogDeleteButton(WebElement dialogDeleteButton) {
        this.dialogDeleteButton = dialogDeleteButton;
    }

    public WebElement getWorkingDayOption() {
        return workingDayOption;
    }

    public void setWorkingDayOption(WebElement workingDayOption) {
        this.workingDayOption = workingDayOption;
    }

    public WebElement getSaturdayOption() {
        return saturdayOption;
    }

    public void setSaturdayOption(WebElement saturdayOption) {
        this.saturdayOption = saturdayOption;
    }

    public WebElement getSundayOption() {
        return sundayOption;
    }

    public void setSundayOption(WebElement sundayOption) {
        this.sundayOption = sundayOption;
    }

    public void ensureWorkingDayButtonVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(workingDayOption));
    }

    public void ensureDeleteVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(dialogDeleteButton));
    }

    public void ensureCancelButtonVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(dialogCloseButton));
    }

    public int numberOfDepartures() {
        WebDriverWait wait = new WebDriverWait(driver, 1);

        List<WebElement> tableRows = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(departuresTable, By.tagName("tr")));
        return tableRows.size();
    }

    public void ensureTableVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(departuresTable));
    }

    public int getStationsTableSize() {
        return this.driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-list-existing-departures-dialog/div/mat-dialog-content/table/tbody/tr")).size();
    }

}
