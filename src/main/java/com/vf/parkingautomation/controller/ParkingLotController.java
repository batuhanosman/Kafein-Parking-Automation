package com.vf.parkingautomation.controller;

import com.vf.parkingautomation.constants.ApiEndPoints;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.model.response.ParkVehicleResponse;
import com.vf.parkingautomation.model.response.ParkingLotStatusResponse;
import com.vf.parkingautomation.repository.ParkingLotRepository;
import com.vf.parkingautomation.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

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
        logger.info("Parking Vehicle Started For Request {}", request);
        return parkingLotService.park(request);
    }

    @GetMapping(value = "/park/status", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Get Parking Lot Status")
    public ParkingLotStatusResponse parkingLotStatus(){
        logger.info("Get Status Started");
        return parkingLotService.status();
    }

    @DeleteMapping(value = "/park/leave/{SLOT_NUMBER}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "Leave Park")
    public ParkVehicleResponse leave(@PathVariable("SLOT_NUMBER") Long slotNumber){
        logger.info("Leave Park Started For Request {}", slotNumber);
        return parkingLotService.leave(slotNumber);
    }
}
