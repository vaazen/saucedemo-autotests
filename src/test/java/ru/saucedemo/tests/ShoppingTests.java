package ru.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.saucedemo.pages.CartPage;
import ru.saucedemo.pages.CheckoutPage;
import ru.saucedemo.pages.InventoryPage;

public class ShoppingTests extends BaseTest {
    @Test(description = "Добавление Sauce Labs Backpack в корзину")
    public void productCanBeAddedToCart() {
        InventoryPage inventory = loginAsStandardUser();
        inventory.addProduct("Sauce Labs Backpack");

        Assert.assertEquals(inventory.getCartBadge(), "1",
                "Ожидался счётчик корзины 1, фактически: " + inventory.getCartBadge());

        CartPage cart = inventory.openCart();
        Assert.assertEquals(cart.getItemName(), "Sauce Labs Backpack",
                "Ожидался товар Sauce Labs Backpack, фактически: " + cart.getItemName());
        Assert.assertEquals(cart.getItemPrice(), "$29.99",
                "Ожидалась цена $29.99, фактически: " + cart.getItemPrice());
    }

    @Test(description = "Полное оформление заказа")
    public void userCanCompleteCheckout() {
        InventoryPage inventory = loginAsStandardUser();
        CartPage cart = inventory.addProduct("Sauce Labs Backpack").openCart();
        CheckoutPage checkout = cart.checkout();

        checkout.fillCustomerData("Ivan", "Petrov", "123456");
        Assert.assertEquals(checkout.getSummaryItem(), "Sauce Labs Backpack",
                "В заказе ожидался Sauce Labs Backpack, фактически: " + checkout.getSummaryItem());

        checkout.finish();
        Assert.assertEquals(checkout.getCompleteMessage(), "Thank you for your order!",
                "Ожидалось сообщение о завершении заказа, фактически: " + checkout.getCompleteMessage());
    }
}
