package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.PointAndTwoNumbers;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.printers.JSONPrinter;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineJSONTest {

    @Test
    public void whenJSONGenerated() {
        var store = new MemoryStore();
        var now = Calendar.getInstance();
        var worker = new Employee("Ivan", now, now, 100.5);
        var worker2 = new Employee("Andrey", now, now, 205);
        store.add(worker);
        store.add(worker2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        var currencyFormat = new PointAndTwoNumbers();
        var printer = new JSONPrinter(
                parser,
                Currency.RUB,
                Currency.RUB,
                new InMemoryCurrencyConverter(),
                currencyFormat);
        Report engine = new ReportEngine(store, printer);
        var expected = """
                [
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": "%s"
                  },
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": "%s"
                  }
                ]""".formatted(worker.getName(),
                parser.parse(worker.getHired()),
                parser.parse(worker.getFired()),
                currencyFormat.format(worker.getSalary()),
                worker2.getName(),
                parser.parse(worker2.getHired()),
                parser.parse(worker2.getFired()),
                currencyFormat.format(worker2.getSalary())
        );
        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }

}