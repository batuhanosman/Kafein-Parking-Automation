package com.vf.parkingautomation.entity;

import com.vf.parkingautomation.model.dto.VehicleDTO;
import com.vf.parkingautomation.model.enums.VehicleType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @NotBlank(message = "Plate Number can not be empty.")
    private String plateNumber;

    @NotBlank(message = "Color can not be empty.")
    private String color;

    @NotNull(message = "vehicleType cannot be null")
    private VehicleType vehicleType;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Ticket cannot be null")
    private Ticket ticket;

    public VehicleDTO toDTO(){
        VehicleDTO dto = new VehicleDTO();
        dto.setId(getId());
        dto.setVersion(getVersion());
        dto.setPlateNumber(getPlateNumber());
        dto.setColor(getColor());
        dto.setVehicleType(getVehicleType());
        dto.setTicketDTO(getTicket().toDTO());

        return dto;
    }

}
