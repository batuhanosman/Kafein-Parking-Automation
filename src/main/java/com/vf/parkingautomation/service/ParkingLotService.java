package com.vf.parkingautomation.service;

import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;
import com.vf.parkingautomation.model.response.ParkingLotStatusResponse;

public interface ParkingLotService {

    ParkVehicleResponse park(ParkVehicleRequest request);

    ParkVehicleResponse leave(Long slotNumber);

    ParkingLotStatusResponse status();

}
