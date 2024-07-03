package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@Disabled
class SimpleParkingTest {

    @Test
    public void when1Car1TruckOnParking() {
        var parking = new SimpleParking(1, 1);
        List.of(new Truck(5), new Car()).forEach(parking::park);
        assertThat(parking.getOccupiedCarPlaces()).isEqualTo(1);
        assertThat(parking.getOccupiedTruckPlaces()).isEqualTo(1);
    }

    @Test
    public void whenTruckSize5Parking5CarPlacesThen5CarPlacesOccupied() {
        var parking = new SimpleParking(5, 0);
        parking.park(new Truck(5));
        assertThat(parking.getOccupiedCarPlaces()).isEqualTo(5);
        assertThat(parking.getOccupiedTruckPlaces()).isEqualTo(0);
    }

    @Test
    public void when2Truck10CarsParking12Cars1TruckThen12CarPlaces1TruckPlaceOccupied() {
        var parking = new SimpleParking(12, 1);
        var cars = new ArrayList<Parkable>();
        IntStream.range(0, 10).forEach(i -> cars.add(new Car()));
        cars.add(new Truck(5));
        cars.add(new Truck(2));
        cars.forEach(parking::park);
        assertThat(parking.getOccupiedCarPlaces()).isEqualTo(12);
        assertThat(parking.getOccupiedTruckPlaces()).isEqualTo(1);
    }

    @Test
    public void whenNoSpaceForTruckThrowsException() {
        var parking = new SimpleParking(4, 0);
        var truck = new Truck(5);
        assertThatThrownBy(() -> parking.park(truck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("The parking doesn't have place for a vehicle %s", truck));
    }

    @Test
    public void whenNoSpaceForCarThrowsException() {
        var parking = new SimpleParking(0, 5);
        var car = new Car();
        assertThatThrownBy(() -> parking.park(car))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("The parking doesn't have place for a vehicle %s", car));
    }

    @Test
    public void whenOccupyTooManyCarPlacesThrowAnException() {
        var parking = new SimpleParking(0, 5);
        assertThatThrownBy(() -> parking.occupyCarPlaces(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("The  parking doesn't have %d free car places", 1));
    }

    @Test
    public void whenOccupyTooManyTruckPlacesThrowAnException() {
        var parking = new SimpleParking(0, 5);
        assertThatThrownBy(() -> parking.occupyTruckPlaces(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("The  parking doesn't have %d free truck places", 6));
    }
}