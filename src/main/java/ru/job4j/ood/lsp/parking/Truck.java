package ru.job4j.ood.lsp.parking;

import java.util.StringJoiner;

public class Truck implements Vehicle {

    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public void park(Parking parking) {
        if (parking.getOccupiedTrucks() < parking.getTruckPlaces()) {
            parking.occupyTruckPlaces(1);
        } else if (parking.getOccupiedCars() + this.getSize() <= parking.getCarPlaces()) {
            parking.occupyCarPlaces(this.getSize());
        }  else {
            throw new IllegalArgumentException(
                    String.format("The parking doesn't have place for a vehicle %s", this));
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Truck.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .toString();
    }

    public int getSize() {
        return size;
    }
}
