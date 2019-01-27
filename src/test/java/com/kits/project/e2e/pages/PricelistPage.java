package com.kits.project.e2e.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricelistPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[4]/div[5]/div")
    private WebElement startDate;

    @FindBy(xpath = "//div[3]/ngb-datepicker/div[2]/div/ngb-datepicker-month-view/div[7]/div[7]/div")
    private WebElement endDate;

    @FindBy(css = "button.btn.btn-dark")
    private WebElement addButton;

    @FindBy(xpath = "//button[2]")
    private WebElement addPricelistButton;

    @FindBy(xpath = "//div[4]/input")
    private WebElement price;

    @FindBy(css = "button.mat-raised-button")
    private WebElement closeModal;

    public PricelistPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getStartDate() {
        return startDate;
    }

    public WebElement getCloseModal() {
        return closeModal;
    }

    public void setCloseModal(WebElement closeModal) {
        this.closeModal = closeModal;
    }

    public void setStartDate(WebElement startDate) {
        this.startDate = startDate;
    }

    public WebElement getEndDate() {
        return endDate;
    }

    public void setEndDate(WebElement endDate) {
        this.endDate = endDate;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public void setAddButton(WebElement addButton) {
        this.addButton = addButton;
    }

    public WebElement getPrice() {
        return price;
    }

    public void setPrice(WebElement price) {
        this.price = price;
    }

    public WebElement getAddPricelistButton() {
        return addPricelistButton;
    }

    public void setAddPricelistButton(WebElement addPricelistButton) {
        this.addPricelistButton = addPricelistButton;
    }

    public void ensureIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addButton));
    }

    public void ensureIsAddDialogDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath("//div[4]/input"))));
    }

    public void ensureIsAddDialogHidden() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.invisibilityOf(this.driver.findElement(By.xpath("//div[4]/input"))));
    }

    public int getPricelistTableSize() {
        return this.driver.findElements(By.cssSelector("tr")).size();
    }

    public Alert waitForAlert(WebDriver driver) throws InterruptedException {
        int i=0;
        while(i++<5)
        {
            try
            {
                Alert alert = driver.switchTo().alert();
                return alert;
            }
            catch(NoAlertPresentException e)
            {
                Thread.sleep(1000);
                continue;
            }
        }
        return null;
    }

    public void ensureIsAdded(int previousNoOfPricelists) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector("tr"), previousNoOfPricelists + 1));
    }

    public void ensureIsNotAdded(int previousNoOfPricelists) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector("tr"), previousNoOfPricelists));
    }
}
