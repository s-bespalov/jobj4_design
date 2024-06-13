package ru.job4j.ood.ocp;

public class BusinessAccount extends PrivateAccount {

    public BusinessAccount(Client client) {
        super(client);
    }

    @Override
    public void interestPayment(double interest) {
        balance *= (1 + interest) - 0.01;
    }
}
