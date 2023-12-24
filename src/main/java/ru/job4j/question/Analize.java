package ru.job4j.question;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        var trackMap = new HashMap<Integer, NameTrack>();
        for (var user : previous) {
            trackMap.put(user.getId(), new NameTrack(user.getName(), null));
        }
        for (var user : current) {
            if (trackMap.containsKey(user.getId())) {
                trackMap.get(user.getId()).newName = user.getName();
            } else {
                trackMap.put(user.getId(), new NameTrack(null, user.getName()));
            }
        }
        var info = new Info(0, 0, 0);
        for (var item : trackMap.values()) {
            if (item.newName == null) {
                info.increaseDeleted();
            } else if (item.oldName == null) {
                info.increaseAdded();
            } else if (!Objects.equals(item.newName, item.oldName)) {
                info.increaseChanged();
            }
        }
        return info;
    }

    private static class NameTrack {
        private String oldName;
        private String newName;

        private NameTrack(String oldName, String newName) {
            this.oldName = oldName;
            this.newName = newName;
        }
    }
}
