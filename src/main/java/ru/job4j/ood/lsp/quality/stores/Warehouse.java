package ru.job4j.ood.lsp.quality.stores;

import ru.job4j.ood.lsp.quality.utils.Calculator;

public class Warehouse extends AbstractStore {
    public Warehouse() {
        super(food -> Calculator.shelfLifeInPercents(food) > 75);
    }
}
