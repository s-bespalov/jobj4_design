package ru.job4j.srp;

public class Contact {

    String name;
    Address address;
    String phone;

    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = Address.parseAddress(address);
        checkPhone(phone);
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    private static void checkPhone(String number) {
        if (!number.startsWith("+")) {
            throw new IllegalArgumentException(String.format("Incorrect phone number: %s", number));
        }
    }
}
