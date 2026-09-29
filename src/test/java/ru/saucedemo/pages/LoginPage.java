package ru.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        visible(username);
        return this;
    }

    public InventoryPage loginAs(String user, String passwordValue) {
        type(username, user);
        type(password, passwordValue);
        click(loginButton);
        return new InventoryPage(driver);
    }

    public void loginExpectingError(String user, String passwordValue) {
        type(username, user);
        type(password, passwordValue);
        click(loginButton);
        visible(errorMessage);
    }

    public String getErrorMessage() {
        return text(errorMessage);
    }
}
