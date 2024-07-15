package ru.job4j.ood.lsp.quality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.food.Food;
import ru.job4j.ood.lsp.quality.stores.Shop;
import ru.job4j.ood.lsp.quality.stores.Trash;
import ru.job4j.ood.lsp.quality.stores.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenThreeFoodsAddedToThreeStoresNoDiscount() {
        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();
        var controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        var toWarehouse = new Food("Carrot",
                LocalDate.now().minusDays(10), LocalDate.now().plusDays(90), 100d);
        var toShop = new Food("Pizza",
                LocalDate.now().minusDays(30), LocalDate.now().plusDays(70), 100d);
        var toTrash = new Food("Apple",
                LocalDate.now().minusDays(110), LocalDate.now().minusDays(10), 100d);
        controlQuality.distributeFood(List.of(toWarehouse, toShop, toTrash));
        assertThat(shop.getFoods()).containsExactly(toShop);
        assertThat(warehouse.getFoods()).containsExactly(toWarehouse);
        assertThat(trash.getFoods()).containsExactly(toTrash);
        assertThat(toShop.getPriceAfterDiscount()).isEqualTo(100d);
        assertThat(toTrash.getPriceAfterDiscount()).isEqualTo(100d);
        assertThat(toWarehouse.getPriceAfterDiscount()).isEqualTo(100d);
    }

    @Test
    public void whenFoodLifeLess25ThenDiscountedBy20Percents() {
        var shop = new Shop();
        var controlQuality = new ControlQuality(List.of(shop));
        var food = new Food("Milk",
                LocalDate.now().minusDays(78), LocalDate.now().plusDays(22), 100d);
        controlQuality.distributeFood(List.of(food));
        assertThat(food.getPriceAfterDiscount()).isEqualTo(80d);
    }

    @Test
    public void whenAddFoodToWarehouse() {
        var warehouse = new Warehouse();
        var controlQuality = new ControlQuality(List.of(warehouse));
        var toWarehouse = new Food("Carrot",
                LocalDate.now().minusDays(10), LocalDate.now().plusDays(90), 100d);
        controlQuality.distributeFood(List.of(toWarehouse));
        assertThat(warehouse.getFoods()).containsExactly(toWarehouse);
    }

    @Test
    public void whenAddFoodToShop() {
        var shop = new Shop();
        var controlQuality = new ControlQuality(List.of(shop));
        var toShop = new Food("Pizza",
                LocalDate.now().minusDays(30), LocalDate.now().plusDays(70), 100d);
        controlQuality.distributeFood(List.of(toShop));
        assertThat(shop.getFoods()).containsExactly(toShop);
    }

    @Test
    public void whenAddFoodToTrash() {
        var trash = new Trash();
        var controlQuality = new ControlQuality(List.of(trash));
        var toTrash = new Food("Apple",
                LocalDate.now().minusDays(110), LocalDate.now().minusDays(10), 100d);
        controlQuality.distributeFood(List.of(toTrash));
        assertThat(trash.getFoods()).containsExactly(toTrash);
    }

    @Test
    public void whenFoodLifeLess25AndResort3TimesThenDiscountedBy20Percents() {
        var shop = new Shop();
        var controlQuality = new ControlQuality(List.of(shop));
        var food = new Food("Milk",
                LocalDate.now().minusDays(78), LocalDate.now().plusDays(22), 100d);
        controlQuality.distributeFood(List.of(food));
        controlQuality.resort();
        controlQuality.resort();
        controlQuality.resort();
        assertThat(food.getPriceAfterDiscount()).isEqualTo(80d);
        assertThat(shop.getFoods()).containsExactly(food);
    }

    @Test
    public void whenFoodsInWrongStoresAfterResortFoodsInRightStores() {
        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();
        var controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        var toWarehouse = new Food("Carrot",
                LocalDate.now().minusDays(10), LocalDate.now().plusDays(90), 100d);
        var toShop = new Food("Pizza",
                LocalDate.now().minusDays(30), LocalDate.now().plusDays(70), 100d);
        var toTrash = new Food("Apple",
                LocalDate.now().minusDays(110), LocalDate.now().minusDays(10), 100d);
        warehouse.addFood(toTrash);
        shop.addFood(toWarehouse);
        trash.addFood(toShop);
        controlQuality.resort();
        assertThat(shop.getFoods()).containsExactly(toShop);
        assertThat(warehouse.getFoods()).containsExactly(toWarehouse);
        assertThat(trash.getFoods()).containsExactly(toTrash);
    }
}