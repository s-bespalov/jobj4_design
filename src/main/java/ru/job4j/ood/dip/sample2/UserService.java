package ru.job4j.ood.dip.sample2;

class UserService {
    private Database database;

    public UserService() {
        this.database = new Database();
    }

    public void performDatabaseOperations() {
        database.connect();
        // Perform operations
        database.disconnect();
    }
}
