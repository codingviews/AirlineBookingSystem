package com.cviews.abs.location.service.controller;

import com.cviews.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @GetMapping("/location")
    public ApiResponse getLocation() {
        return new ApiResponse("Location service is running.");
    }
}