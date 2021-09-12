package com.vf.parkingautomation.exception;

public class ThereIsNoSlot extends RuntimeException{

    public ThereIsNoSlot() {
        super();
    }

    public ThereIsNoSlot(String message) {
        super(message);
    }
}
