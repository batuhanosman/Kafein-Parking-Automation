package com.vf.parkingautomation.service.impl;

import com.vf.parkingautomation.constants.ApiErrorConstants;
import com.vf.parkingautomation.controller.ParkingLotController;
import com.vf.parkingautomation.entity.ParkingLot;
import com.vf.parkingautomation.entity.Ticket;
import com.vf.parkingautomation.entity.Vehicle;
import com.vf.parkingautomation.exception.ThereIsNoSlot;
import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.enums.Status;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;
import com.vf.parkingautomation.model.response.ParkingLotStatusResponse;
import com.vf.parkingautomation.repository.ParkingLotRepository;
import com.vf.parkingautomation.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    private final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
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
                if(parkingLotList.get(i).getAvailable().equals(Boolean.TRUE)){
                    slotNumbers.add(parkingLotList.get(i).getSlotNumber());
                    if(slotNumbers.size() == request.getVehicleType().getSize()){
                        if(i != parkingLotList.size() - 1){
                            if(parkingLotList.get(i).getAvailable()){
                                // create
                                ticket = parkTheVehicle(parkingLotList, request, slotNumbers, false);
                                return new ParkVehicleResponse(ticket, Status.SUCCESS);
                            }
                        }else{
                            //create
                            ticket = parkTheVehicle(parkingLotList, request, slotNumbers, true);
                            return new ParkVehicleResponse(ticket, Status.SUCCESS);
                        }
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
        return null;
    }

    @Override
    public ParkVehicleResponse leave(Long slotNumber) {
        return null;
    }
}
