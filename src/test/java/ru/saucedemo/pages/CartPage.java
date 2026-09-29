package ru.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By title = By.cssSelector("[data-test='title']");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By itemPrices = By.cssSelector("[data-test='inventory-item-price']");
    private final By checkout = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
        visible(title);
    }

    public String getItemName() {
        return text(itemNames);
    }

    public String getItemPrice() {
        return text(itemPrices);
    }

    public CheckoutPage checkout() {
        click(checkout);
        return new CheckoutPage(driver);
    }
}
