package ru.job4j.serialization.json.my;

import org.json.JSONObject;

public class JSONOrg {

    public static void main(String[] args) {
        var house = new House("Vozdvizhenka Street, 10, Moscow, 125009",
                1890,
                new Apartment[]{
                        new Apartment(1, new Lodger[]{
                                new Lodger("Sergey")
                        }),
                        new Apartment(2, new Lodger[]{
                                new Lodger("Andrey")
                        })},
                true);
        System.out.println(new JSONObject(house));
    }
}
