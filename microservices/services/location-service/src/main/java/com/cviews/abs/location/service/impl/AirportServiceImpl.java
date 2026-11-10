package com.cviews.abs.location.service.impl;

import com.cviews.abs.location.mapper.AirportMapper;
import com.cviews.abs.location.model.Airport;
import com.cviews.abs.location.model.City;
import com.cviews.abs.location.repository.AirportRepository;
import com.cviews.abs.location.repository.CityRepository;
import com.cviews.abs.location.service.AirportService;
import com.cviews.abs.payload.request.AirportRequest;
import com.cviews.abs.payload.response.AirportResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportServiceImpl(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {
        if (airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()) {
            throw new IllegalArgumentException("Airport with IATA code " + airportRequest.getIataCode() + " already exists.");
        }

        City city = cityRepository.findById(airportRequest.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + airportRequest.getCityId() + " not found."));

        Airport airport = AirportMapper.toEntityBuilder(airportRequest).city(city).build();
        Airport savedAirport = airportRepository.save(airport);
        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport with id " + id + " not found."));
        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(AirportMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport with id " + id + " not found."));

        if (airportRequest.getIataCode() != null && !airport.getIataCode().equals(airportRequest.getIataCode()) && airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()) {
            throw new IllegalArgumentException("Airport with IATA code " + airportRequest.getIataCode() + " already exists.");
        }

        City city = cityRepository.findById(airportRequest.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + airportRequest.getCityId() + " not found."));
        Airport updatedAirport = AirportMapper.updateEntity(airport, airportRequest, city);
        Airport savedAirport = airportRepository.save(updatedAirport);
        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    public void deleteAirport(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport with id " + id + " not found."));
        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId).stream().map(AirportMapper::toResponse).collect(Collectors.toList());
    }
}
