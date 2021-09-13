package com.vf.parkingautomation.exception;

public class ThereIsNoVehicle extends RuntimeException{

    public ThereIsNoVehicle() {
        super();
    }

    public ThereIsNoVehicle(String message) {
        super(message);
    }
}
