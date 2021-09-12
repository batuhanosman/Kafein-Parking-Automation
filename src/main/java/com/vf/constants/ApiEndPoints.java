package com.vf.constants;

import org.springframework.http.MediaType;

public class ApiEndPoints {

    public static final String RESPONSE_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

    public static final String API_BASE_URL = "/api";
    public static final String VF_PARKING_LOT_API = API_BASE_URL + "/parking-lot";

    private ApiEndPoints(){}
}
