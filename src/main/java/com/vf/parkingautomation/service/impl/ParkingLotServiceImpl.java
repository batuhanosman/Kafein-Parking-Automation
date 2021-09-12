package com.vf.parkingautomation.service.impl;

import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;
import com.vf.parkingautomation.service.ParkingLotService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParkingLotServiceImpl implements ParkingLotService {


    @Override
    public ParkVehicleResponse park(ParkVehicleRequest request) {

        return null;
    }

    @Override
    public TicketDTO leave(Long slotNumber) {
        return null;
    }

    @Override
    public List<TicketDTO> status() {
        return null;
    }
}
