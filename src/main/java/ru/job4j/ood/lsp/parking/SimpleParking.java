package ru.job4j.ood.lsp.parking;

public class SimpleParking implements Parking {

    private final int carPlaces;
    private final int truckPlaces;

    public SimpleParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public void occupyCarPlaces(int count) {

    }

    @Override
    public void occupyTruckPlaces(int count) {

    }

    @Override
    public int getOccupiedTruckPlaces() {
        return 0;
    }

    @Override
    public int getOccupiedCarPlaces() {
        return 0;
    }

    @Override
    public int getTotalCarPlaces() {
        return 0;
    }

    @Override
    public int getTotalTruckPlaces() {
        return 0;
    }

    @Override
    public void park(Parkable parkable) {

    }
}
