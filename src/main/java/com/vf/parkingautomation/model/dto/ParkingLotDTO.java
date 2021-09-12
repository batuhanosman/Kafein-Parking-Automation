package com.vf.parkingautomation.model.dto;

import lombok.Data;

@Data
public class ParkingLotDTO {
    private Long id;
    private int version;
    private Long slotNumber;
    private Boolean available;
    private VehicleDTO vehicleDTO;
}
