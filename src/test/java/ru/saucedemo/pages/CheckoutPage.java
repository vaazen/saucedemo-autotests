package ru.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By summaryItem = By.cssSelector("[data-test='inventory-item-name']");
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    private final By error = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillCustomerData(String first, String last, String zip) {
        type(firstName, first);
        type(lastName, last);
        type(postalCode, zip);
        click(continueButton);
        return this;
    }

    public String getSummaryItem() {
        return text(summaryItem);
    }

    public CheckoutPage finish() {
        click(finishButton);
        visible(completeHeader);
        return this;
    }

    public String getCompleteMessage() {
        return text(completeHeader);
    }

    public String getValidationError() {
        return text(error);
    }

    public boolean isValidationErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).isDisplayed();
    }
}
