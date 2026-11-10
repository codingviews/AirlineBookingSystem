package com.cviews.abs.location.mapper;

import com.cviews.abs.location.model.Airport;
import com.cviews.abs.location.model.City;
import com.cviews.abs.payload.request.AirportRequest;
import com.cviews.abs.payload.response.AirportResponse;

public class AirportMapper {

    private AirportMapper() {
        // Private constructor to prevent instantiation
    }

    public static Airport toEntity(AirportRequest airportRequest) {
        return toEntityBuilder(airportRequest).build();
    }

    public static Airport.Builder toEntityBuilder(AirportRequest airportRequest) {
        if (airportRequest == null) {
            return null;
        }

        return Airport.builder()
                .name(airportRequest.getName())
                .iataCode(airportRequest.getIataCode())
                .address(airportRequest.getAddress())
                .geoCode(airportRequest.getGeoCode())
                .cityId(airportRequest.getCityId());
    }

    public static AirportResponse toResponse(Airport airport) {
        if (airport == null) {
            return null;
        }

        return AirportResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .iataCode(airport.getIataCode())
                .address(airport.getAddress())
                .geoCode(airport.getGeoCode())
                .city(CityMapper.toResponse(airport.getCity()))
                .build();
    }

    public static Airport updateEntity(Airport existingAirport, AirportRequest airportRequest, City city) {
        if (existingAirport == null || airportRequest == null) {
            return null;
        }

        Airport.Builder airportBuilder = Airport.builder().id(existingAirport.getId());

        if (airportRequest.getName() != null) {
            airportBuilder.name(airportRequest.getName().trim());
        }
        if (airportRequest.getIataCode() != null) {
            airportBuilder.iataCode(airportRequest.getIataCode().toUpperCase().trim());
        }
        if (airportRequest.getAddress() != null) {
            airportBuilder.address(airportRequest.getAddress());
        }
        if (airportRequest.getGeoCode() != null) {
            airportBuilder.geoCode(airportRequest.getGeoCode());
        }
        if (city != null) {
            airportBuilder.city(city);
        }

        return airportBuilder.build();
    }
}
