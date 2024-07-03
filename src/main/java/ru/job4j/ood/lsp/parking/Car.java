package ru.job4j.ood.lsp.parking;

public class Car implements Parkable {

    @Override
    public void park(Parking parking) {
        if (parking.getTotalCarPlaces() - parking.getOccupiedCarPlaces() <= 0) {
            throw new IllegalArgumentException(String.format("The parking doesn't have place for a vehicle %s", this));
        }
        parking.occupyCarPlaces(1);
    }
}
