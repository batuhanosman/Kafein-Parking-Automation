package com.vf.parkingautomation.model.response;

import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.enums.Status;

import java.util.List;

public class ParkingLotStatusResponse {

    private List<TicketDTO> ticketDTOList;
    private Status status;

}
