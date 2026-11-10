package com.cviews.abs.location.service.controller;

import com.cviews.abs.location.service.CityService;
import com.cviews.abs.payload.CityRequest;
import com.cviews.abs.payload.CityResponse;
import com.cviews.abs.payload.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @Test
    void createCityShouldReturnCreatedResponse() {
        CityRequest request = CityRequest.builder()
                .name("Paris")
                .cityCode("PAR")
                .countryCode("FR")
                .countryName("France")
                .regionCode("IDF")
                .timeZoneOffset("+01:00")
                .build();

        CityResponse responseBody = CityResponse.builder()
                .id(1L)
                .name("Paris")
                .cityCode("PAR")
                .countryName("France")
                .countryCode("FR")
                .regionCode("IDF")
                .timeZoneOffset("+01:00")
                .build();

        when(cityService.createCity(request)).thenReturn(responseBody);

        ResponseEntity<CityResponse> response = cityController.createCity(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseBody, response.getBody());
        verify(cityService).createCity(request);
    }

    @Test
    void getCityByIdShouldReturnOkResponse() {
        Long cityId = 1L;
        CityResponse responseBody = CityResponse.builder()
                .id(cityId)
                .name("Paris")
                .cityCode("PAR")
                .countryName("France")
                .countryCode("FR")
                .regionCode("IDF")
                .timeZoneOffset("+01:00")
                .build();

        when(cityService.getCityById(cityId)).thenReturn(responseBody);

        ResponseEntity<CityResponse> response = cityController.getCityById(cityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBody, response.getBody());
        verify(cityService).getCityById(cityId);
    }

    @Test
    void getAllCitiesShouldReturnPageResponse() {
        int page = 1;
        int size = 10;
        String sortBy = "cityCode";
        String sortDirection = "desc";
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));

        CityResponse city = CityResponse.builder()
                .id(2L)
                .name("Rome")
                .cityCode("ROM")
                .countryName("Italy")
                .countryCode("IT")
                .regionCode("LAZ")
                .timeZoneOffset("+01:00")
                .build();
        Page<CityResponse> cityPage = new PageImpl<>(List.of(city), pageable, 1);

        when(cityService.getAllCities(pageable)).thenReturn(cityPage);

        ResponseEntity<Page<CityResponse>> response = cityController.getAllCities(page, size, sortBy, sortDirection);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cityPage, response.getBody());
        verify(cityService).getAllCities(pageable);
    }

    @Test
    void updateCityShouldReturnOkResponse() {
        Long cityId = 3L;
        CityRequest request = CityRequest.builder()
                .name("Berlin")
                .cityCode("BER")
                .countryCode("DE")
                .countryName("Germany")
                .regionCode("BE")
                .timeZoneOffset("+01:00")
                .build();

        CityResponse responseBody = CityResponse.builder()
                .id(cityId)
                .name("Berlin")
                .cityCode("BER")
                .countryName("Germany")
                .countryCode("DE")
                .regionCode("BE")
                .timeZoneOffset("+01:00")
                .build();

        when(cityService.updateCity(cityId, request)).thenReturn(responseBody);

        ResponseEntity<CityResponse> response = cityController.updateCity(cityId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBody, response.getBody());
        verify(cityService).updateCity(cityId, request);
    }

    @Test
    void deleteCityShouldReturnOkResponse() {
        Long cityId = 4L;
        ApiResponse expectedResponse = new ApiResponse("City deleted successfully");

        doNothing().when(cityService).deleteCity(cityId);

        ResponseEntity<ApiResponse> response = cityController.deleteCity(cityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(cityService).deleteCity(cityId);
    }

    @Test
    void searchCitiesShouldReturnPageResponse() {
        String keyword = "par";
        int page = 0;
        int size = 25;
        String sortBy = "name";
        String sortDirection = "asc";
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));

        CityResponse city = CityResponse.builder()
                .id(5L)
                .name("Paris")
                .cityCode("PAR")
                .countryName("France")
                .countryCode("FR")
                .regionCode("IDF")
                .timeZoneOffset("+01:00")
                .build();
        Page<CityResponse> cityPage = new PageImpl<>(List.of(city), pageable, 1);

        when(cityService.searchCities(keyword, pageable)).thenReturn(cityPage);

        ResponseEntity<Page<CityResponse>> response = cityController.searchCities(keyword, page, size, sortBy, sortDirection);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cityPage, response.getBody());
        verify(cityService).searchCities(keyword, pageable);
    }

    @Test
    void getCitiesByCountryShouldUppercaseCountryCodeAndReturnPage() {
        String countryCode = "fr";
        int page = 0;
        int size = 5;
        String sortBy = "name";
        String sortDirection = "asc";
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));

        CityResponse city = CityResponse.builder()
                .id(6L)
                .name("Paris")
                .cityCode("PAR")
                .countryName("France")
                .countryCode("FR")
                .regionCode("IDF")
                .timeZoneOffset("+01:00")
                .build();
        Page<CityResponse> cityPage = new PageImpl<>(List.of(city), pageable, 1);

        when(cityService.getCitiesByCountryCode("FR", pageable)).thenReturn(cityPage);

        ResponseEntity<Page<CityResponse>> response = cityController.getCitiesByCountry(countryCode, page, size, sortBy, sortDirection);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cityPage, response.getBody());
        verify(cityService).getCitiesByCountryCode("FR", pageable);
    }

    @Test
    void checkCityExistsShouldReturnBooleanResult() {
        String cityCode = "PAR";

        when(cityService.cityExists(cityCode)).thenReturn(true);

        ResponseEntity<Boolean> response = cityController.checkCityExists(cityCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(cityService).cityExists(cityCode);
    }
}
