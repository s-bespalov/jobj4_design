package ru.job4j.ood.dip.sample3;

class OrderService {
    private final EmailService emailService;

    public OrderService() {
        this.emailService = new EmailService();
    }

    public void placeOrder() {
        System.out.println("Order placed");
        emailService.sendEmail("Order confirmation");
    }
}
