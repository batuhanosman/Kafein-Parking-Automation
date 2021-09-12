package com.vf.parkingautomation.model.dto;

import com.vf.parkingautomation.model.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleDTO {

    private Long id;
    private int version;
    private String plateNumber;
    private String color;
    private VehicleType vehicleType;
    private TicketDTO ticketDTO;

}
