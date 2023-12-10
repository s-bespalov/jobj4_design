package ru.job4j.map;

import java.text.DateFormat;
import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        var alena = new User(
                "Alena",
                1,
                new Calendar.Builder().setDate(2000, 6, 18).build());
        var alena2 = new User(
                "Alena",
                1,
                new Calendar.Builder().setDate(2000, 6, 18).build());
        Map<User, Object> map = new HashMap<>();
        map.put(alena, new Object());
        map.put(alena2, new Object());
        System.out.println(map);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
