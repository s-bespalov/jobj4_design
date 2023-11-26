package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindRoleNameIsAdmin() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        Role result = store.findById("0");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsAdmin() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        store.add(new Role("0", "User"));
        Role result = store.findById("0");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceRoleNameIsUser() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        store.replace("0", new Role("0", "User"));
        Role result = store.findById("0");
        assertThat(result.getRoleName()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceRoleNameThenNoChangeRoleName() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        store.replace("10", new Role("0", "User"));
        Role result = store.findById("0");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        store.delete("0");
        Role result = store.findById("0");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdmin() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        store.delete("10");
        Role result = store.findById("0");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        boolean result = store.replace("0", new Role("0", "User"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        var store = new RoleStore();
        store.add(new Role("0", "Admin"));
        boolean result = store.replace("10", new Role("10", "User"));
        assertThat(result).isFalse();
    }
}