package ru.job4j.ood.lsp.parking;

public class SimpleParking implements Parking {

    private final int carPlaces;
    private final int truckPlaces;
    private int occupiedCarPlaces = 0;
    private int occupiedTruckPlaces = 0;

    public SimpleParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public void occupyCarPlaces(int count) {
        if (occupiedCarPlaces + count > carPlaces) {
            throw new IllegalArgumentException(String.format("The  parking doesn't have %d free car places", count));
        }
        occupiedCarPlaces += count;
    }

    @Override
    public void occupyTruckPlaces(int count) {
        if (occupiedTruckPlaces + count > truckPlaces) {
            throw new IllegalArgumentException(String.format("The  parking doesn't have %d free truck places", count));
        }
        occupiedTruckPlaces += count;
    }

    @Override
    public int getOccupiedTruckPlaces() {
        return occupiedTruckPlaces;
    }

    @Override
    public int getOccupiedCarPlaces() {
        return occupiedCarPlaces;
    }

    @Override
    public int getTotalCarPlaces() {
        return carPlaces;
    }

    @Override
    public int getTotalTruckPlaces() {
        return truckPlaces;
    }

    @Override
    public void park(Parkable parkable) {
        parkable.park(this);
    }
}
