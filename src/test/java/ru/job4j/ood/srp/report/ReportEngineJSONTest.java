package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.printers.JSONPrinter;
import ru.job4j.ood.srp.sorters.DoNotSort;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ReportEngineJSONTest {

    @Test
    public void whenJSONGenerated() {
        var store = new MemoryStore();
        var now = Calendar.getInstance();
        var worker = new Employee("Ivan", now, now, 100);
        var worker2 = new Employee("Andrey", now, now, 205);
        store.add(worker);
        store.add(worker2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        var rows = List.of(
                Employee.Fields.NAME,
                Employee.Fields.HIRED,
                Employee.Fields.FIRED,
                Employee.Fields.SALARY);
        var printer = new JSONPrinter(
                parser, rows, new DoNotSort(), Currency.RUB, Currency.RUB, new InMemoryCurrencyConverter());
        Report engine = new ReportEngine(store, printer);
        var expected = """
                [
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": %.2f
                  },
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": %.2f
                  }
                ]
                """.formatted(worker.getName(),
                parser.parse(worker.getHired()),
                parser.parse(worker.getFired()),
                worker.getSalary(),
                worker2.getName(),
                parser.parse(worker2.getHired()),
                parser.parse(worker2.getFired()),
                worker2.getSalary()
        );
        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }

}