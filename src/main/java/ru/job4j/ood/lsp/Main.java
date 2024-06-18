package ru.job4j.ood.lsp;

public class Main {

    public static void main(String[] args) {
        var site1 = new Site("http://example.com");
        System.out.println(site1);
        var site2 = new SecureSite("https://ya.ru");
        System.out.println(site2);
    }
}
