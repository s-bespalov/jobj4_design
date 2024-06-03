package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ContactTest {

    @Test
    public void whenCreateNewContact() {
        var addr = "USA,New-York,Line 1,18";
        var contact = new Contact("Sergey", addr, "+19551255454");
        assertThat(contact.getAddress().country).isEqualTo("USA");
        assertThat(contact.getName()).isEqualTo("Sergey");
        assertThat(contact.getAddress().street).isEqualTo("Line 1");
        assertThat(contact.getAddress().getCity()).isEqualTo("New-York");
        assertThat(contact.getAddress().getHome()).isEqualTo(18);
        assertThat(contact.getPhone()).isEqualTo("+19551255454");
    }

}