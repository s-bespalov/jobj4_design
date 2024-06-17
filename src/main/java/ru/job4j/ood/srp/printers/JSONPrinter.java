package ru.job4j.ood.srp.printers;

import org.json.JSONObject;
import org.json.JSONArray;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.CurrencyFormat;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class JSONPrinter extends BasePrinter implements Printer {
    private final List<Employee.Fields> rows;

    public JSONPrinter(DateTimeParser<Calendar> dateTimeParser,
                       Currency sourceCurrency,
                       Currency targetCurrency,
                       CurrencyConverter converter,
                       CurrencyFormat currencyFormat) {
        super(sourceCurrency, targetCurrency, converter, currencyFormat, dateTimeParser);
        rows = List.of(Employee.Fields.NAME, Employee.Fields.HIRED, Employee.Fields.FIRED, Employee.Fields.SALARY);
    }

    @Override
    public String print(List<Employee> employees) {
        var root = new JSONArray();
        employees.forEach(employee -> {
            var employeeJSON = new LinkedJsonObject();
            rows.forEach(row -> employeeJSON.put(row.getName().toLowerCase(), printEmployeeField(employee, row)));
            root.put(employeeJSON);
        });
        return root.toString(2);
    }

    private static class LinkedJsonObject extends JSONObject {

        public LinkedJsonObject() {
            try {
                Field map = JSONObject.class.getDeclaredField("map");
                map.setAccessible(true);
                Object mapValue = map.get(this);
                if (!(mapValue instanceof LinkedHashMap)) {
                    map.set(this, new LinkedHashMap<>());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
