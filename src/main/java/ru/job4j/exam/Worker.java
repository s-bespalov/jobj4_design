package ru.job4j.exam;

import java.util.*;

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
        var managers = new HashMap<Integer, List<Worker>>();
        workers.forEach(w -> {
            var managerId = w.getManagerID();
            if (managerId != 0 && w.getId() != id) {
                managers.putIfAbsent(managerId, new ArrayList<>());
                managers.get(managerId).add(w);
            }
        });
        var toVisit = new ArrayList<Integer>();
        toVisit.add(id);
        while (!toVisit.isEmpty()) {
            var managerId = toVisit.removeFirst();
            if (managers.containsKey(managerId)) {
                var workersOfManager = managers.get(managerId);
                result.addAll(workersOfManager);
                workersOfManager.forEach(w -> toVisit.add(w.getId()));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Worker.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("managerID=" + managerID)
                .add("id=" + id)
                .toString();
    }
}
