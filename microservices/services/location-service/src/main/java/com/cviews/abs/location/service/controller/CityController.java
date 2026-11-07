package com.cviews.abs.location.service.controller;

import com.cviews.abs.location.payload.CityRequest;
import com.cviews.abs.location.payload.CityResponse;
import com.cviews.abs.location.service.CityService;
import com.cviews.payload.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest cityRequest) {
        CityResponse cityResponse = cityService.createCity(cityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) {
        CityResponse cityResponse = cityService.getCityById(id);
        return ResponseEntity.ok(cityResponse);
    }

    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> cities = cityService.getAllCities(pageable);
        return ResponseEntity.ok(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable Long id, @Valid @RequestBody CityRequest cityRequest) {
        CityResponse cityResponse = cityService.updateCity(id, cityRequest);
        return ResponseEntity.ok(cityResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok(new ApiResponse("City deleted successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> cities = cityService.searchCities(keyword, pageable);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountry(@PathVariable String countryCode, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> cities = cityService.getCitiesByCountryCode(countryCode.toUpperCase(), pageable);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> checkCityExists(@PathVariable String cityCode) {
        boolean exists = cityService.cityExists(cityCode);
        return ResponseEntity.ok(exists);
    }
}
