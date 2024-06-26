package ru.job4j.ood.lsp.quality.stores;

import ru.job4j.ood.lsp.quality.utils.Calculator;

public class Trash extends AbstractStore {
    public Trash() {
        super(food -> Calculator.shelfLifeInPercents(food) <= 0);
    }
}
