package ru.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.saucedemo.utils.Config;

public class InventoryPage extends BasePage {
    private final By title = By.cssSelector("[data-test='title']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By sortSelect = By.cssSelector("[data-test='product-sort-container']");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By itemPrices = By.cssSelector("[data-test='inventory-item-price']");

    public InventoryPage(WebDriver driver) {
        super(driver);
        visible(title);
    }

    public String getTitle() {
        return text(title);
    }

    public InventoryPage addProduct(String productName) {
        By addButton = By.xpath("//div[@data-test='inventory-item'][.//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']]//button[contains(@data-test,'add-to-cart')]");
        click(addButton);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartBadge, "1"));
        return this;
    }

    public String getCartBadge() {
        return text(cartBadge);
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public InventoryPage sortBy(String value) {
        click(sortSelect);
        WebElement select = visible(sortSelect);
        new org.openqa.selenium.support.ui.Select(select).selectByValue(value);
        wait.until(d -> getItemNames().size() >= 1);
        return this;
    }

    public List<String> getItemNames() {
        List<String> result = new ArrayList<>();
        for (WebElement element : driver.findElements(itemNames)) {
            result.add(element.getText());
        }
        return result;
    }

    public List<Double> getItemPrices() {
        List<Double> result = new ArrayList<>();
        for (WebElement element : driver.findElements(itemPrices)) {
            result.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        return result;
    }

    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }
}
