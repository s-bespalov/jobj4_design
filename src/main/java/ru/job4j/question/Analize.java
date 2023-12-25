package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        var trackMap = new HashMap<Integer, List<String>>();
        for (var user : previous) {
            var list = new LinkedList<String>();
            list.add(user.getName());
            trackMap.put(user.getId(), list);
        }
        for (var user : current) {
            if (trackMap.containsKey(user.getId())) {
                trackMap.get(user.getId()).add(user.getName());
            } else {
                var list = new LinkedList<String>();
                list.add(null);
                list.add(user.getName());
                trackMap.put(user.getId(), list);
            }
        }
        var info = new Info(0, 0, 0);
        for (var item : trackMap.values()) {
            if (item.size() == 1) {
                info.increaseDeleted();
            } else if (item.get(0) == null) {
                info.increaseAdded();
            } else if (!Objects.equals(item.get(0), item.get(1))) {
                info.increaseChanged();
            }
        }
        return info;
    }
}
