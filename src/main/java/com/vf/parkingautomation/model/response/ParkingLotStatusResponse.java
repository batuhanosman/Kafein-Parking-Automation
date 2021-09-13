package com.vf.parkingautomation.model.response;

import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.enums.Status;

import java.util.List;

public class ParkingLotStatusResponse {

    private List<TicketDTO> ticketDTOList;
    private Status status;

    public ParkingLotStatusResponse(List<TicketDTO> ticketDTOList, Status status) {
        this.ticketDTOList = ticketDTOList;
        this.status = status;
    }

    public List<TicketDTO> getTicketDTOList() {
        return ticketDTOList;
    }

    public void setTicketDTOList(List<TicketDTO> ticketDTOList) {
        this.ticketDTOList = ticketDTOList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
