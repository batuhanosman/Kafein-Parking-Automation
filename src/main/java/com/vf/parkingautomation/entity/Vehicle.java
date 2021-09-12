package com.vf.parkingautomation.entity;

import com.vf.parkingautomation.model.enums.VehicleType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @NotEmpty(message = "Plate Number can not be empty.")
    private String plateNumber;

    @NotEmpty(message = "Color can not be empty.")
    private String color;

    @NotNull(message = "vehicleType cannot be null")
    private VehicleType vehicleType;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Ticket cannot be null")
    private Ticket ticket;

}
