package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Parking {

    private final int carPlaces;
    private final int truckPlaces;
    private int occupiedCars = 0;
    private int occupiedTrucks = 0;


    public Parking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    public void occupyCarPlaces(int count) {
        if (occupiedCars + count > carPlaces) {
            throw new IllegalArgumentException(String.format("The  parking doesn't have %d free car places", count));
        }
        while (count > 0) {
            occupiedCars++;
            count--;
        }
    }

    public void occupyTruckPlaces(int count) {
        if (occupiedTrucks + count > truckPlaces) {
            throw new IllegalArgumentException(String.format("The  parking doesn't have %d free truck places", count));
        }
        while (count > 0) {
            occupiedTrucks++;
            count--;
        }
    }

    public void parkVehicle(Vehicle vehicle) {
        vehicle.park(this);
    }

    public void parkVehicles(List<Vehicle> vehicles) {
        vehicles.forEach(this::parkVehicle);
    }

    public int getCarPlaces() {
        return carPlaces;
    }

    public int getTruckPlaces() {
        return truckPlaces;
    }

    public int getOccupiedCars() {
        return occupiedCars;
    }

    public int getOccupiedTrucks() {
        return occupiedTrucks;
    }
}
