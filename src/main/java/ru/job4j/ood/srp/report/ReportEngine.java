package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.printers.Printer;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;
    private final Printer printer;

    public ReportEngine(Store store, Printer printer) {
        this.store = store;
        this.printer = printer;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return printer.print(store.findBy(filter));
    }
}