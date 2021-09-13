package com.vf.parkingautomation.service.impl;

import com.vf.parkingautomation.constants.ApiErrorConstants;
import com.vf.parkingautomation.controller.ParkingLotController;
import com.vf.parkingautomation.entity.ParkingLot;
import com.vf.parkingautomation.entity.Ticket;
import com.vf.parkingautomation.entity.Vehicle;
import com.vf.parkingautomation.exception.ThereIsNoSlot;
import com.vf.parkingautomation.exception.ThereIsNoVehicle;
import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.enums.Status;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;
import com.vf.parkingautomation.model.response.ParkingLotStatusResponse;
import com.vf.parkingautomation.repository.ParkingLotRepository;
import com.vf.parkingautomation.repository.TicketRepository;
import com.vf.parkingautomation.repository.VehicleRepository;
import com.vf.parkingautomation.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;

    private final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository, VehicleRepository vehicleRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public ParkVehicleResponse park(ParkVehicleRequest request) {

        List<ParkingLot> parkingLotList = parkingLotRepository.findAll();
        TicketDTO ticket = new TicketDTO();
        List<Long> slotNumbers = new ArrayList<>();

        if(parkingLotList.isEmpty()){
            throw new ThereIsNoSlot(ApiErrorConstants.THERE_IS_NO_SLOT);
        }

        for(int i = 0; i < parkingLotList.size(); i++){
                if(parkingLotList.get(i).getAvailable()){
                    slotNumbers.add(parkingLotList.get(i).getSlotNumber());
                    if(slotNumbers.size() == request.getVehicleType().getSize()){
                        ticket = parkTheVehicle(parkingLotList, request, slotNumbers, i == parkingLotList.size() - 1);
                        return new ParkVehicleResponse(ticket, Status.SUCCESS);
                    }
                }else{
                    slotNumbers.clear();
                }
        }

        if (Objects.isNull(ticket.getVehiclePlateNumber())) {
            logger.info("Garage Is Full");
        } else {
            logger.info("Allocated {} slot", request.getVehicleType().getSize());
        }
        return new ParkVehicleResponse(ticket, Status.SUCCESS);
    }

    private TicketDTO parkTheVehicle(List<ParkingLot> parkingLotList,ParkVehicleRequest request, List<Long> slotNumbers, boolean isLastSlot) {
        Ticket ticket = new Ticket();
        ticket.setVehiclePlateNumber(request.getPlateNumber());
        ticket.setVehicleColor(request.getVehicleColor());
        ticket.setSlotNumbers(slotNumbers);

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(request.getPlateNumber());
        vehicle.setColor(request.getVehicleColor());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setTicket(ticket);


        if(!isLastSlot){
            parkingLotList.stream()
                    .filter(parkingLot -> parkingLot.getSlotNumber()
                            .equals(slotNumbers.get(slotNumbers.size()-1) + 1))
                    .findAny().get().setAvailable(false);
        }
        parkingLotList.stream().filter(parkingLot -> slotNumbers.contains(parkingLot.getSlotNumber()))
                .forEach(parkingLot -> {
                    parkingLot.setAvailable(false);
                    parkingLot.setVehicle(vehicle);
                });


        return ticket.toDTO();
    }


    @Override
    public ParkingLotStatusResponse status() {
        List<Ticket> ticketList = ticketRepository.findAll();
        return new ParkingLotStatusResponse(ticketList.isEmpty() ?
                Collections.emptyList() :
                ticketList.stream().map(ticket -> ticket.toDTO()).collect(Collectors.toList()), Status.SUCCESS);
    }

    @Override
    public ParkVehicleResponse leave(Long slotNumber) {
        List<ParkingLot> parkingLotList = parkingLotRepository.findAll(Sort.by(Sort.Direction.ASC ,"slotNumber"));
        ParkingLot vehicleParkingLot = parkingLotRepository.findOneBySlotNumber(slotNumber);

        if(!Optional.ofNullable(vehicleParkingLot).isPresent())
            throw new ThereIsNoVehicle(ApiErrorConstants.THERE_IS_NO_SLOT);
        if(!Optional.ofNullable(vehicleParkingLot.getVehicle()).isPresent())
            throw new ThereIsNoVehicle(ApiErrorConstants.THERE_IS_NO_VEHICLE);

        Vehicle vehicle = vehicleParkingLot.getVehicle();
        int slotSize = vehicleParkingLot.getVehicle().getVehicleType().getSize(), lastSlotNumber = 0;
        TicketDTO ticket = null;


        Long vehicleSlotNumber = parkingLotList.stream().filter(parkingLot ->
            parkingLot.getVehicle() == vehicleParkingLot.getVehicle()
        ).findFirst().get().getSlotNumber();

        for (int i = vehicleSlotNumber.intValue()-1; i < (vehicleSlotNumber.intValue()-1) + slotSize; i++){
            if(Objects.isNull(ticket)) ticket = parkingLotList.get(i).getVehicle().getTicket().toDTO();
            parkingLotList.get(i).setVehicle(null);
            parkingLotList.get(i).setAvailable(true);
            lastSlotNumber = i;
        }


        if(!parkingLotList.get(lastSlotNumber).equals(parkingLotList.get(parkingLotList.size()-1))){
            parkingLotList.get(lastSlotNumber + 1).setAvailable(true);
        }

        vehicleRepository.delete(vehicle);

        return new ParkVehicleResponse(ticket, Status.SUCCESS);
    }
}
