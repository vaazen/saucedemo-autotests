package ru.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.saucedemo.pages.InventoryPage;
import ru.saucedemo.utils.Config;

public class LoginTests extends BaseTest {
    @Test(description = "Позитивная авторизация стандартным пользователем")
    public void standardUserCanLogin() {
        InventoryPage inventory = loginAsStandardUser();
        Assert.assertTrue(inventory.isOnInventoryPage(),
                "Ожидался URL /inventory.html, фактически: " + driver.getCurrentUrl());
        Assert.assertEquals(inventory.getTitle(), "Products",
                "Ожидался заголовок Products, фактически: " + inventory.getTitle());
    }

    @Test(description = "Заблокированный пользователь получает сообщение об ошибке")
    public void lockedUserCannotLogin() {
        loginPage.loginExpectingError(Config.LOCKED_USER, Config.PASSWORD);
        String actual = loginPage.getErrorMessage();
        Assert.assertTrue(actual.contains("Epic sadface"),
                "Ожидалось сообщение с текстом Epic sadface, фактически: " + actual);
    }
}
