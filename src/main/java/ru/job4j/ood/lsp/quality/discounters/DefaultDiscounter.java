package ru.job4j.ood.lsp.quality.discounters;

import ru.job4j.ood.lsp.quality.food.Food;
import ru.job4j.ood.lsp.quality.utils.Calculator;

import java.util.function.Consumer;

public class DefaultDiscounter implements Consumer<Food> {
    @Override
    public void accept(Food food) {
        var remaining = Calculator.shelfLifeInPercents(food);
        if (remaining > 0 && remaining < 25) {
            food.setDiscount(0.8d);
        }
    }
}
