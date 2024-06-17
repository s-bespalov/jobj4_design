package ru.job4j.ood.srp.printers;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.CurrencyFormat;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

public class XMLPrinter extends BasePrinter implements Printer {

    public XMLPrinter(DateTimeParser<Calendar> dateTimeParser,
                      Currency sourceCurrency,
                      Currency targetCurrency,
                      CurrencyConverter converter,
                      CurrencyFormat currencyFormat) {
        super(sourceCurrency, targetCurrency, converter, currencyFormat, dateTimeParser);
    }

    @Override
    public String print(List<Employee> employees) {
        var xml = "";
        try {
            var employeesXML = new EmployeesXML();
            employeesXML.employees = employees.stream()
                    .map(employee -> new EmployeeXML(
                            printEmployeeField(employee, Employee.Fields.NAME),
                            printEmployeeField(employee, Employee.Fields.HIRED),
                            printEmployeeField(employee, Employee.Fields.FIRED),
                            printEmployeeField(employee, Employee.Fields.SALARY)
                    )).toArray(EmployeeXML[]::new);
            var context = JAXBContext.newInstance(EmployeesXML.class);
            var mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (var writer = new StringWriter()) {
                mar.marshal(employeesXML, writer);
                xml = writer.getBuffer().toString();
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class EmployeesXML {

        @XmlElement(name = "employee")
        private EmployeeXML[] employees;
    }

    @XmlRootElement(name = "employee")
    @XmlType(propOrder = {"name", "hired", "fired", "salary"})
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class EmployeeXML {

        @XmlElement
        private String name;
        @XmlElement
        private String hired;
        @XmlElement
        private String fired;
        @XmlElement
        private String salary;

        public EmployeeXML(String name, String hired, String fired, String salary) {
            this.name = name;
            this.hired = hired;
            this.fired = fired;
            this.salary = salary;
        }

        public EmployeeXML() {
        }
    }
}
