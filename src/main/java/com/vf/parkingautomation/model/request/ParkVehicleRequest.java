package com.vf.parkingautomation.model.request;

import com.vf.parkingautomation.model.enums.VehicleType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ParkVehicleRequest {

    @NotBlank(message = "Plate Number Cannot Be Empty")
    private String  plateNumber;

    @NotBlank(message = "Vehicle Color Cannot Be Empty")
    private String vehicleColor;

    @NotNull(message = "Vehicle Type Cannot Be Null")
    private VehicleType vehicleType;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
