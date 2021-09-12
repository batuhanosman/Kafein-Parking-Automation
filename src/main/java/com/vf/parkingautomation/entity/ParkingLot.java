package com.vf.parkingautomation.entity;

import com.vf.parkingautomation.model.dto.ParkingLotDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @NotNull(message = "Slot number cannot be null")
    private Long slotNumber;

    @NotNull(message = "Slot's available status cannot be null.")
    private Boolean available = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Vehicle vehicle;

    public ParkingLotDTO toDTO(){
        ParkingLotDTO dto = new ParkingLotDTO();
        dto.setId(getId());
        dto.setVersion(getVersion());
        dto.setSlotNumber(getSlotNumber());
        dto.setAvailable(getAvailable());
        dto.setVehicleDTO(getVehicle().toDTO());

        return dto;
    }
}
