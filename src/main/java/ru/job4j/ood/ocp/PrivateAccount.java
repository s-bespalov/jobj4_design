package ru.job4j.ood.ocp;

public class PrivateAccount {
    protected Client client;

    protected double balance;

    public PrivateAccount(Client client) {
        this.client = client;
    }

    public void interestPayment(double interest) {
        balance *= (1 + interest);
    }

    public Client getClient() {
        return client;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
