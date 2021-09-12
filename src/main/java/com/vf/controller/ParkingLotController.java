package com.vf.controller;

import com.vf.constants.ApiEndPoints;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(value = ApiEndPoints.VF_PARKING_LOT_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
@Api
@SwaggerDefinition(tags = {@Tag(name = "vf-parking-lot-api", description = "Parking Lot Api")})
public class ParkingLotController {



}
