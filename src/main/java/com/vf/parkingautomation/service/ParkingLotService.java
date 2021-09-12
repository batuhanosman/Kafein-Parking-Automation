package com.vf.parkingautomation.service;

import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;

import java.util.List;

public interface ParkingLotService {

    ParkVehicleResponse park(ParkVehicleRequest request);

    TicketDTO leave(Long slotNumber);

    List<TicketDTO> status();

}
