package com.vf.parkingautomation.service;

import com.vf.parkingautomation.entity.ParkingLot;
import com.vf.parkingautomation.entity.Ticket;
import com.vf.parkingautomation.entity.Vehicle;
import com.vf.parkingautomation.model.enums.VehicleType;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.repository.ParkingLotRepository;
import com.vf.parkingautomation.repository.TicketRepository;
import com.vf.parkingautomation.repository.VehicleRepository;
import com.vf.parkingautomation.service.impl.ParkingLotServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class ParkingLotServiceTest {
    @InjectMocks
    private ParkingLotServiceImpl parkingLotServiceImpl;

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void parkVehicleTest(){
        ParkVehicleRequest request = new ParkVehicleRequest();

        request.setPlateNumber("38-HH-0038");
        request.setVehicleColor("Red");
        request.setVehicleType(VehicleType.Car);

        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(getParkingLot());
        ParkingLot parkingLot = getParkingLot();
        parkingLot.setSlotNumber(Long.valueOf(2));
        parkingLotList.add(parkingLot);

        Mockito.when(parkingLotRepository.findAll()).thenReturn(parkingLotList);

        parkingLotServiceImpl.park(request);
    }

    @Test
    public void getStatusTest(){
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(getParkingLot());
        parkingLotList.add(getParkingLot());

        Mockito.when(ticketRepository.findAll()).thenReturn(parkingLotList.stream().map(parkingLot ->
            parkingLot.getVehicle().getTicket()
        ).collect(Collectors.toList()));

        parkingLotServiceImpl.status();
    }

    @Test(expected = NoSuchElementException.class)
    public void leaveParkTest(){
        ParkVehicleRequest request = new ParkVehicleRequest();

        request.setPlateNumber("38-HH-0038");
        request.setVehicleColor("Red");
        request.setVehicleType(VehicleType.Car);

        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(getParkingLot());
        ParkingLot parkingLot = getParkingLot();
        parkingLot.setSlotNumber(Long.valueOf(2));
        parkingLotList.add(parkingLot);


        Mockito.when(parkingLotRepository.findOneBySlotNumber(parkingLot.getSlotNumber())).thenReturn(parkingLot);

        parkingLotServiceImpl.leave(parkingLot.getSlotNumber());
    }

    public ParkingLot getParkingLot(){
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

        return parkingLot;
    }

}
