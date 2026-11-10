package com.cviews.abs.location.service.controller;

import com.cviews.abs.location.service.AirportService;
import com.cviews.abs.payload.request.AirportRequest;
import com.cviews.abs.payload.response.AirportResponse;
import com.cviews.abs.payload.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest airportRequest) {
        AirportResponse airportResponse = airportService.createAirport(airportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(airportResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) {
        AirportResponse airportResponse = airportService.getAirportById(id);
        return ResponseEntity.ok(airportResponse);
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        List<AirportResponse> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable Long cityId) {
        List<AirportResponse> airports = airportService.getAirportsByCityId(cityId);
        return ResponseEntity.ok(airports);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id, @Valid @RequestBody AirportRequest airportRequest) {
        AirportResponse airportResponse = airportService.updateAirport(id, airportRequest);
        return ResponseEntity.ok(airportResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted successfully"));
    }
}
