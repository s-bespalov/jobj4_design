package ru.job4j.srp;

public class Address {
    String country;
    String city;
    String street;
    int home;

    public Address(String country, String city, String street, int home) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
    }

    public static Address parseAddress(String address) {
        Address rsl;
        var arr = address.split(",");
        if (arr.length < 4) {
            throw new IllegalArgumentException(String.format("Incorrect address: %s", address));
        }
        rsl = new Address(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]));
        return rsl;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHome() {
        return home;
    }
}
