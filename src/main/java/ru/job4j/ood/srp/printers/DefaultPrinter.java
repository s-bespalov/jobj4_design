package ru.job4j.ood.srp.printers;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.*;

public class DefaultPrinter extends BasePrinter implements Printer {
    private final List<Employee.Fields> rows;
    private final Comparator<Employee> sorter;

    public DefaultPrinter(DateTimeParser<Calendar> dateTimeParser,
                          List<Employee.Fields> rows,
                          Comparator<Employee> sorter,
                          Currency sourceCurrency,
                          Currency targetCurrency,
                          CurrencyConverter converter) {
        super(sourceCurrency, targetCurrency, converter, dateTimeParser);
        this.rows = rows;
        this.sorter = sorter;
    }

    private String printReportHeader() {
        StringJoiner joiner = new StringJoiner("; ", "", ";");
        rows.stream()
                .map(Employee.Fields::getName)
                .forEach(joiner::add);
        return joiner.toString();
    }

    @Override
    public String print(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append(printReportHeader())
                .append(System.lineSeparator());
        employees.stream()
                .sorted(sorter)
                .forEach(employee -> {
                    var joiner = new StringJoiner(" ");
                    rows.forEach(row -> joiner.add(printEmployeeField(employee, row)));
                    text.append(joiner).append(System.lineSeparator());
                });
        return text.toString();
    }
}
