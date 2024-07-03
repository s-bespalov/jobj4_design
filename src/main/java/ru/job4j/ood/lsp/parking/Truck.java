package ru.job4j.ood.lsp.parking;

public class Truck implements Parkable {

    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public void park(Parking parking) {

    }

    public int getSize() {
        return size;
    }
}
