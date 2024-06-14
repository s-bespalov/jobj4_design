package ru.job4j.ood.srp.printers;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class XMLPrinter extends BasePrinter implements Printer {
    public XMLPrinter(DateTimeParser<Calendar> dateTimeParser,
                       List<Employee.Fields> rows,
                       Comparator<Employee> sorter,
                       Currency sourceCurrency,
                       Currency targetCurrency,
                       CurrencyConverter converter) {
        super(sourceCurrency, targetCurrency, converter, dateTimeParser);
    }

    @Override
    public String print(List<Employee> employees) {
        return "";
    }
}
