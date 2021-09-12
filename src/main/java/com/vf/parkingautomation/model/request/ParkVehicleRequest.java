package com.vf.parkingautomation.model.request;

import com.vf.parkingautomation.model.enums.VehicleType;

import javax.validation.constraints.NotEmpty;

public class ParkVehicleRequest {

    @NotEmpty(message = "Plate Number Cannot Be Empty")
    private String  plateNumber;

    @NotEmpty(message = "Vehicle Color Cannot Be Empty")
    private String vehicleColor;

    @NotEmpty(message = "Vehicle Type Cannot Be Empty")
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
