package ru.saucedemo.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.saucedemo.pages.InventoryPage;

public class SortingTests extends BaseTest {
    @Test(description = "Сортировка товаров по цене от меньшей к большей")
    public void productsCanBeSortedByPriceLowToHigh() {
        InventoryPage inventory = loginAsStandardUser();
        inventory.sortBy("lohi");
        List<Double> actual = inventory.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected,
                "Ожидался порядок цен по возрастанию. Фактически: " + actual);
    }
}
