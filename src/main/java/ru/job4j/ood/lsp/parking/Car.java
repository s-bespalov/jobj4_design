package ru.job4j.ood.lsp.parking;

import java.util.StringJoiner;

public class Car implements Vehicle {

    @Override
    public void park(Parking parking) {
        if (parking.getOccupiedCars() < parking.getCarPlaces()) {
            parking.occupyCarPlaces(1);
        } else {
            throw new IllegalArgumentException(
                    String.format("The parking doesn't have place for a vehicle %s", this));
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .toString();
    }
}
