package ru.job4j.ood.srp.printers;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public interface Printer {
    String print(List<Employee> employees);
}
