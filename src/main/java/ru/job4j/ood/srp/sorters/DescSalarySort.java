package ru.job4j.ood.srp.sorters;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class DescSalarySort implements Comparator<Employee> {

    @Override
    public int compare(Employee e0, Employee e1) {
        return Double.compare(e1.getSalary(), e0.getSalary());
    }
}
