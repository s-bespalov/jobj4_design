package ru.job4j.ood.srp.sorters;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class DoNotSort implements Comparator<Employee> {

    @Override
    public int compare(Employee employee, Employee t1) {
        return 0;
    }
}
