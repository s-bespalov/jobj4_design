package ru.job4j.ood.srp.printers;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

abstract class BasePrinter implements Printer {

    private final Currency sourceCurrency;
    private final Currency targetCurrency;
    private final CurrencyConverter converter;
    private final DateTimeParser<Calendar> dateTimeParser;

    BasePrinter(Currency sourceCurrency,
                Currency targetCurrency,
                CurrencyConverter converter,
                DateTimeParser<Calendar> dateTimeParser) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.converter = converter;
        this.dateTimeParser = dateTimeParser;
    }

    protected String printEmployeeField(Employee employee, Employee.Fields field) {
        return switch (field) {
            case NAME -> employee.getName();
            case HIRED -> dateTimeParser.parse(employee.getHired());
            case FIRED -> dateTimeParser.parse(employee.getFired());
            case SALARY -> {
                var salary = employee.getSalary();
                if (!Objects.equals(sourceCurrency, targetCurrency)) {
                    salary = converter.convert(sourceCurrency, salary, targetCurrency);
                }
                yield String.valueOf(salary);
            }
        };
    }

    @Override
    public abstract String print(List<Employee> employees);
}
