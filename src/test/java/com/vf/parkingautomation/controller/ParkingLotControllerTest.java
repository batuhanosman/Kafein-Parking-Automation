package com.vf.parkingautomation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vf.parkingautomation.entity.Vehicle;
import com.vf.parkingautomation.model.enums.VehicleType;
import com.vf.parkingautomation.model.request.ParkVehicleRequest;
import com.vf.parkingautomation.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ParkingLotControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ParkingLotController parkingLotController;

    @Mock
    private ParkingLotService parkingLotService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(parkingLotController).build();
    }

    @Test
    public void it_should_invoke_api_vehicle_park_endpoint() throws Exception {

        ParkVehicleRequest request = new ParkVehicleRequest();

        request.setPlateNumber("38-HH-0038");
        request.setVehicleColor("Red");
        request.setVehicleType(VehicleType.Car);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/parking-lot/park/vehicle").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void it_should_invoke_api_car_leave_endpoint() throws Exception {



        mockMvc.perform(delete("/api/parking-lot/park/leave/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void it_should_invoke_api_status_endpoint() throws Exception {

        mockMvc.perform(get("/api/parking-lot/park/status").contentType(MediaType.ALL_VALUE))
                .andExpect(status().isOk()).andReturn();
    }
}
