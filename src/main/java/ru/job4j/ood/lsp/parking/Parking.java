package ru.job4j.ood.lsp.parking;

public interface Parking {
    void occupyCarPlaces(int count);
    void occupyTruckPlaces(int count);
    int getOccupiedTruckPlaces();
    int getOccupiedCarPlaces();
    int getTotalCarPlaces();
    int getTotalTruckPlaces();
    void park(Parkable parkable);
}
