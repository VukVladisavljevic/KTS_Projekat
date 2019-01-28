package com.kits.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTimeSchedulePage {

    private WebDriver driver;

    @FindBy(xpath="/html/body/div[1]/div[2]/div/mat-dialog-container/app-add-departure-dialog/div/mat-dialog-content/div[2]/ngb-timepicker/fieldset/div/div[1]/input")
    private WebElement dialogHourBox;

    @FindBy(xpath="/html/body/div[1]/div[2]/div/mat-dialog-container/app-add-departure-dialog/div/mat-dialog-content/div[2]/ngb-timepicker/fieldset/div/div[3]/input")
    private WebElement dialogMinuteBox;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-add-departure-dialog/mat-dialog-actions/button[1]")
    private WebElement dialogCloseButton;

    @FindBy(id = "doneButton")
    private WebElement dialogDoneButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-add-departure-dialog/div/mat-dialog-content/div[1]/label[1]")
    private WebElement workingDayOption;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-add-departure-dialog/div/mat-dialog-content/div[1]/label[2]")
    private WebElement saturdayOption;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/mat-dialog-container/app-add-departure-dialog/div/mat-dialog-content/div[1]/label[3]")
    private WebElement sundayOption;

    public AddTimeSchedulePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDialogHourBox() {
        return dialogHourBox;
    }

    public void setDialogHourBox(WebElement dialogHourBox) {
        this.dialogHourBox = dialogHourBox;
    }

    public WebElement getDialogMinuteBox() {
        return dialogMinuteBox;
    }

    public void setDialogMinuteBox(WebElement dialogMinuteBox) {
        this.dialogMinuteBox = dialogMinuteBox;
    }

    public WebElement getDialogCloseButton() {
        return dialogCloseButton;
    }

    public void setDialogCloseButton(WebElement dialogCloseButton) {
        this.dialogCloseButton = dialogCloseButton;
    }

    public WebElement getDialogDoneButton() {
        return dialogDoneButton;
    }

    public void setDialogDoneButton(WebElement dialogDoneButton) {
        this.dialogDoneButton = dialogDoneButton;
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

    public void ensureClockSelectVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(dialogHourBox));
    }

    public void ensureCancelButtonVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(dialogCloseButton));
    }

    public void ensureDoneButtonClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(dialogDoneButton));
    }
}
