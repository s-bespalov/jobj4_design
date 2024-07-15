package ru.job4j.ood.lsp.quality;

import ru.job4j.ood.lsp.quality.discounters.DefaultDiscounter;
import ru.job4j.ood.lsp.quality.food.Food;
import ru.job4j.ood.lsp.quality.stores.AbstractStore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ControlQuality {

    private final List<AbstractStore> stores;
    private final Consumer<Food> discounter;


    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
        discounter = new DefaultDiscounter();
    }

    public ControlQuality(List<AbstractStore> stores, Consumer<Food> discounter) {
        this.stores = stores;
        this.discounter = discounter;
    }

    public void distributeSingleFoodItem(Food food) {
        var optionalStore = stores.stream()
                .filter(s -> s.isSuitableFood(food))
                .findFirst();
        if (optionalStore.isEmpty()) {
            throw new IllegalStateException(String.format("No suitable store for food: %s", food));
        } else {
            addFoodToStore(optionalStore.get(), food);
        }
    }

    public void distributeFood(List<Food> foods) {
        foods.forEach(this::distributeSingleFoodItem);
    }

    public void resort() {
        var foods = new ArrayList<Food>();
        stores.forEach(store -> foods.addAll(store.ejectAllFood()));
        distributeFood(foods);
    }

    public List<AbstractStore> getStores() {
        return stores;
    }

    public Consumer<Food> getDiscounter() {
        return discounter;
    }

    private void addFoodToStore(AbstractStore store, Food food) {
        discounter.accept(food);
        store.addFood(food);
    }
}
