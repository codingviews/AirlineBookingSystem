package com.cviews.abs.location.service;

import com.cviews.abs.payload.request.AirportRequest;
import com.cviews.abs.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {

    AirportResponse createAirport(AirportRequest airportRequest);

    AirportResponse getAirportById(Long id);

    List<AirportResponse> getAllAirports();

    AirportResponse updateAirport(Long id, AirportRequest airportRequest);

    void deleteAirport(Long id);

    List<AirportResponse> getAirportsByCityId(Long cityId);
}
