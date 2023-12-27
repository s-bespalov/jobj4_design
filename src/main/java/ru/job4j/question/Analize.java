package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        var trackMap = new HashMap<Integer, User>();
        var info = new Info(0, 0, 0);
        for (var user : previous) {
            trackMap.put(user.getId(), user);
        }
        for (var user : current) {
            if (trackMap.containsKey(user.getId())) {
                if (!Objects.equals(trackMap.get(user.getId()), user)) {
                    info.increaseChanged();
                }
            } else {
                info.increaseAdded();
            }
        }
        info.setDeleted(previous.size() - (current.size() - info.getAdded()));
        return info;
    }
}
