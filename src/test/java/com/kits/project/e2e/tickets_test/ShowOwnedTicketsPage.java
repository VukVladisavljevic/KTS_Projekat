package com.kits.project.e2e.tickets_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShowOwnedTicketsPage {

    private WebDriver driver;

    @FindBy(id = "ticketsTable")
    private WebElement ticketsTable;


    @FindBy(id = "closeButton")
    private WebElement closeButton;

    public WebElement getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(WebElement closeButton) {
        this.closeButton = closeButton;
    }

    public WebElement getTicketsTable() {
        return ticketsTable;
    }

    public void setTicketsTable(WebElement ticketsTable) {
        this.ticketsTable = ticketsTable;
    }

    public int numberOfTickets(WebDriver browser) {
        List<WebElement> tableRows = new WebDriverWait(browser, 10)
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(ticketsTable, By.tagName("tr")));

        return tableRows.size();
    }

    public void ensureIsTableDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(this.ticketsTable));
    }

    public void ensureIsCloseButtonDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(this.closeButton));
    }
}
