package com.vf.parkingautomation.controller;

import com.vf.parkingautomation.constants.ApiEndPoints;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;
import com.vf.parkingautomation.repository.ParkingLotRepository;
import com.vf.parkingautomation.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ApiEndPoints.VF_PARKING_LOT_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
@Api
@SwaggerDefinition(tags = {@Tag(name = "vf-parking-lot-api", description = "Parking Lot Api")})
public class ParkingLotController {

    private final ParkingLotRepository parkingLotRepository;

    private final ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotRepository parkingLotRepository, ParkingLotService parkingLotService) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(value = "/park/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Parking Vehicle to Slot")
    public ParkVehicleResponse parkVehicle(@Valid @RequestBody ParkVehicleRequest request){
        return parkingLotService.park(request);
    }
}
