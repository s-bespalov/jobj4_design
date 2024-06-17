package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.PointAndTwoNumbers;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.printers.DefaultPrinter;
import ru.job4j.ood.srp.sorters.DescSalarySort;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ReportEngineHRTest {

    @Test
    public void whenReportHRGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 1000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        var currencyFormat = new PointAndTwoNumbers();
        store.add(worker);
        store.add(worker2);
        var rows = List.of(Employee.Fields.NAME, Employee.Fields.SALARY);
        var printer = new DefaultPrinter(
                parser,
                rows,
                new DescSalarySort(),
                Currency.RUB,
                Currency.RUB,
                new InMemoryCurrencyConverter(),
                currencyFormat);
        Report engine = new ReportEngine(store, printer);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(String.format("%.2f", worker2.getSalary()))
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(currencyFormat.format(worker.getSalary()))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}