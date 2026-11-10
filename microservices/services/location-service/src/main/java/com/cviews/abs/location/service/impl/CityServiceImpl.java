package com.cviews.abs.location.service.impl;

import com.cviews.abs.location.mapper.CityMapper;
import com.cviews.abs.location.model.City;
import com.cviews.abs.payload.CityRequest;
import com.cviews.abs.payload.CityResponse;
import com.cviews.abs.location.repository.CityRepository;
import com.cviews.abs.location.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityResponse createCity(CityRequest cityRequest) {
        if (cityRepository.existsByCityCode(cityRequest.getCityCode())) {
            throw new IllegalArgumentException("City with code " + cityRequest.getCityCode() + " already exists.");
        }

        City city = CityMapper.toEntity(cityRequest);
        City result = cityRepository.save(city);
        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("City with id " + id + " not found."));

        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("City with id " + id + " not found."));

        if (cityRequest.getCityCode() != null && cityRepository.existsByCityCode(cityRequest.getCityCode())) {
            throw new IllegalArgumentException("City with code " + cityRequest.getCityCode() + " already exists.");
        }

        City result = cityRepository.save(CityMapper.updateEntity(city, cityRequest));
        return CityMapper.toResponse(result);
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("City with id " + id + " not found."));

        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable).map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }

    @Override
    public boolean validateCityCode(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
