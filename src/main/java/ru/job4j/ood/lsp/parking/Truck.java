package ru.job4j.ood.lsp.parking;

public class Truck implements Parkable {

    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public void park(Parking parking) {
        if (parking.getTotalTruckPlaces() - parking.getOccupiedTruckPlaces() > 0) {
            parking.occupyTruckPlaces(1);
        } else if (parking.getTotalCarPlaces() - parking.getOccupiedCarPlaces() >= size) {
            parking.occupyCarPlaces(size);
        } else {
            throw new IllegalArgumentException(String.format("The parking doesn't have place for a vehicle %s", this));
        }
    }

    public int getSize() {
        return size;
    }
}
