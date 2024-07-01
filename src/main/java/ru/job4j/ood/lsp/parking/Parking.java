package ru.job4j.ood.lsp.parking;

public interface Parking {
    void occupyCarPlaces(int count);
    void occupyTruckPlaces(int count);
    void getOccupiedTruckPlaces(int count);
    void getOccupiedCarPlaces(int count);
    int getTotalCarPlaces();
    int getTotalTruckPlaces();
    void park(Parkable parkable);
}
