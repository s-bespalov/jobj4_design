package ru.job4j.ood.srp.formatter;

public class PointAndTwoNumbers implements CurrencyFormat {

    @Override
    public String format(double number) {
        return String.format("%.2f", number);
    }
}
