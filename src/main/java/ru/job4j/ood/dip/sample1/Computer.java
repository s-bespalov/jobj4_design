package ru.job4j.ood.dip.sample1;

class Computer {
    private final Keyboard keyboard;

    public Computer() {
        this.keyboard = new Keyboard();
    }

    public void useKeyboard() {
        keyboard.type();
    }
}
