package com.vf.parkingautomation.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @NotEmpty(message = "Plate Number can not be empty.")
    private String vehiclePlateNumber;

    @NotEmpty(message = "Color can not be empty.")
    private String vehicleColor;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> slotNumbers;
}
