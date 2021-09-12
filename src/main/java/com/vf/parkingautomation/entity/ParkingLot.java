package com.vf.parkingautomation.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;
}
