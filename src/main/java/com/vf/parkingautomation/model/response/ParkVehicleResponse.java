package com.vf.parkingautomation.model.response;

import com.vf.parkingautomation.model.dto.TicketDTO;
import com.vf.parkingautomation.model.enums.Status;

public class ParkVehicleResponse {

    private TicketDTO ticket;
    private Status status;

    public ParkVehicleResponse(TicketDTO ticket, Status status) {
        this.ticket = ticket;
        this.status = status;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkVehicleResponse{" +
                "ticket=" + ticket +
                ", status=" + status +
                '}';
    }
}
