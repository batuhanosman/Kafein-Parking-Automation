package com.vf.parkingautomation.entity;

import com.vf.parkingautomation.model.dto.TicketDTO;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @NotBlank(message = "Plate Number can not be empty.")
    private String vehiclePlateNumber;

    @NotBlank(message = "Color can not be empty.")
    private String vehicleColor;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> slotNumbers;

    public TicketDTO toDTO(){
        TicketDTO dto = new TicketDTO();
        dto.setId(getId());
        dto.setVersion(getVersion());
        dto.setVehiclePlateNumber(getVehiclePlateNumber());
        dto.setVehicleColor(getVehicleColor());
        dto.setSlotNumbers(getSlotNumbers());

        return dto;
    }
}
