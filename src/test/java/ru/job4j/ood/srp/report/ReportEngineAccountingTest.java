package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.printers.DefaultPrinter;
import ru.job4j.ood.srp.sorters.DoNotSort;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ReportEngineAccountingTest {

    @Test
    public void whenAccountingReportEURGenereated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        var rows = List.of(
                Employee.Fields.NAME,
                Employee.Fields.HIRED,
                Employee.Fields.FIRED,
                Employee.Fields.SALARY);
        var printer = new DefaultPrinter(
                parser, rows, new DoNotSort(), Currency.USD, Currency.RUB, new InMemoryCurrencyConverter());
        Report engine = new ReportEngine(store, printer);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(new InMemoryCurrencyConverter().convert(Currency.USD, worker.getSalary(), Currency.RUB))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}