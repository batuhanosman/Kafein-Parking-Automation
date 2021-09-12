package com.vf.parkingautomation.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketDTO {

    private Long id;
    private int version;
    private String vehiclePlateNumber;
    private String vehicleColor;
    private List<Long> slotNumbers;

}
