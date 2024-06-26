package ru.job4j.ood.lsp.quality.stores;

import ru.job4j.ood.lsp.quality.utils.Calculator;

public class Shop extends AbstractStore {
    public Shop() {
        super(food -> {
            var p = Calculator.shelfLifeInPercents(food);
            return p <= 75 && p > 0;
        });
    }
}
