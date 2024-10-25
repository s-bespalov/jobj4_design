package ru.job4j.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Worker {
    private String name;
    private int managerID;
    private final int id;

    public Worker(int id, int managerID, String name) {
        this.id = id;
        this.managerID = managerID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Worker.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("managerID=" + managerID)
                .toString();
    }

    public static List<Worker> getAllManagers(List<Worker> workers, int id) {
        var result = new ArrayList<Worker>();
        var map = new HashMap<Integer, Worker>();
        workers.forEach(w -> map.put(w.getId(), w));
        int managerId = map.containsKey(id) ? map.get(id).getManagerID() : 0;
        while (managerId != 0) {
            if (map.containsKey(managerId)) {
                var manager = map.get(managerId);
                result.add(manager);
                managerId = manager.getManagerID();
            } else {
                managerId = 0;
            }
        }
        return result;
    }

    public static List<Worker> getAllSubWorkers(List<Worker> workers, int id) {
        var result = new ArrayList<Worker>();
        var map = new HashMap<Integer, Worker>();
        workers.forEach(w -> map.put(w.getManagerID(), w));
        while (id != 0) {
            if (map.containsKey(id)) {
                var worker = map.get(id);
                result.add(worker);
                id = worker.getId();
            } else {
                id = 0;
            }
        }
        return result;
    }
}
