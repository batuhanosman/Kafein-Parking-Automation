package com.vf.parkingautomation.Entity;

import com.vf.parkingautomation.entity.ParkingLot;
import com.vf.parkingautomation.entity.Ticket;
import com.vf.parkingautomation.entity.Vehicle;
import com.vf.parkingautomation.model.enums.VehicleType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ParkingLotTest {

    @Test
    public void parkingLotTest(){
        Ticket ticket = new Ticket();
        ticket.setSlotNumbers(Arrays.asList(Long.valueOf(1)));
        ticket.setVehicleColor("Red");

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber("38-HH-0038");
        vehicle.setColor("Red");
        vehicle.setVehicleType(VehicleType.Car);
        vehicle.setTicket(ticket);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setSlotNumber(Long.valueOf(1));
        parkingLot.setAvailable(true);
        parkingLot.setVehicle(vehicle);

        Assert.assertNotNull(parkingLot.toDTO());
    }

}
