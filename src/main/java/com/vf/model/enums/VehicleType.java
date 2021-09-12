package com.vf.model.enums;

public enum VehicleType {
    Car(1),
    Jeep(2),
    Truck(4);

    public final int size;

    VehicleType(int size) {
        this.size = size;
    }
}
