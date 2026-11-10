package com.cviews.abs.location.service.controller;

import com.cviews.abs.embeddable.Address;
import com.cviews.abs.embeddable.GeoCode;
import com.cviews.abs.location.service.AirportService;
import com.cviews.abs.payload.CityResponse;
import com.cviews.abs.payload.request.AirportRequest;
import com.cviews.abs.payload.response.AirportResponse;
import com.cviews.abs.payload.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @Test
    void createAirportShouldReturnCreatedResponse() {
        AirportRequest request = new AirportRequest(
                "CDG",
                "Charles de Gaulle",
                ZoneId.of("Europe/Paris"),
                new Address("Paris", "75000"),
                1L,
                new GeoCode(49.0097, 2.5479)
        );

        AirportResponse expected = AirportResponse.builder()
                .id(10L)
                .iataCode("CDG")
                .name("Charles de Gaulle")
                .detailedName("Charles de Gaulle Airport")
                .timeZoneId(ZoneId.of("Europe/Paris"))
                .address(new Address("Paris", "75000"))
                .city(CityResponse.builder()
                        .id(1L)
                        .name("Paris")
                        .cityCode("PAR")
                        .countryName("France")
                        .countryCode("FR")
                        .regionCode("IDF")
                        .timeZoneOffset("+01:00")
                        .build())
                .geoCode(new GeoCode(49.0097, 2.5479))
                .build();

        when(airportService.createAirport(request)).thenReturn(expected);

        ResponseEntity<AirportResponse> response = airportController.createAirport(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(airportService).createAirport(request);
    }

    @Test
    void getAirportByIdShouldReturnOkResponse() {
        Long airportId = 10L;
        AirportResponse expected = AirportResponse.builder()
                .id(airportId)
                .iataCode("CDG")
                .name("Charles de Gaulle")
                .detailedName("Charles de Gaulle Airport")
                .timeZoneId(ZoneId.of("Europe/Paris"))
                .address(new Address("Paris", "75000"))
                .city(CityResponse.builder()
                        .id(1L)
                        .name("Paris")
                        .cityCode("PAR")
                        .countryName("France")
                        .countryCode("FR")
                        .regionCode("IDF")
                        .timeZoneOffset("+01:00")
                        .build())
                .geoCode(new GeoCode(49.0097, 2.5479))
                .build();

        when(airportService.getAirportById(airportId)).thenReturn(expected);

        ResponseEntity<AirportResponse> response = airportController.getAirportById(airportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(airportService).getAirportById(airportId);
    }

    @Test
    void getAllAirportsShouldReturnOkResponse() {
        AirportResponse airport1 = AirportResponse.builder()
                .id(10L)
                .iataCode("CDG")
                .name("Charles de Gaulle")
                .detailedName("Charles de Gaulle Airport")
                .timeZoneId(ZoneId.of("Europe/Paris"))
                .address(new Address("Paris", "75000"))
                .city(CityResponse.builder().id(1L).name("Paris").cityCode("PAR").countryName("France").countryCode("FR").regionCode("IDF").timeZoneOffset("+01:00").build())
                .geoCode(new GeoCode(49.0097, 2.5479))
                .build();

        AirportResponse airport2 = AirportResponse.builder()
                .id(11L)
                .iataCode("ORY")
                .name("Orly")
                .detailedName("Orly Airport")
                .timeZoneId(ZoneId.of("Europe/Paris"))
                .address(new Address("Paris", "94390"))
                .city(CityResponse.builder().id(1L).name("Paris").cityCode("PAR").countryName("France").countryCode("FR").regionCode("IDF").timeZoneOffset("+01:00").build())
                .geoCode(new GeoCode(48.7262, 2.3652))
                .build();

        List<AirportResponse> expected = List.of(airport1, airport2);
        when(airportService.getAllAirports()).thenReturn(expected);

        ResponseEntity<List<AirportResponse>> response = airportController.getAllAirports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(airportService).getAllAirports();
    }

    @Test
    void getAirportsByCityIdShouldReturnOkResponse() {
        Long cityId = 1L;
        AirportResponse airport = AirportResponse.builder()
                .id(10L)
                .iataCode("CDG")
                .name("Charles de Gaulle")
                .detailedName("Charles de Gaulle Airport")
                .timeZoneId(ZoneId.of("Europe/Paris"))
                .address(new Address("Paris", "75000"))
                .city(CityResponse.builder().id(cityId).name("Paris").cityCode("PAR").countryName("France").countryCode("FR").regionCode("IDF").timeZoneOffset("+01:00").build())
                .geoCode(new GeoCode(49.0097, 2.5479))
                .build();

        List<AirportResponse> expected = List.of(airport);
        when(airportService.getAirportsByCityId(cityId)).thenReturn(expected);

        ResponseEntity<List<AirportResponse>> response = airportController.getAirportsByCityId(cityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(airportService).getAirportsByCityId(cityId);
    }

    @Test
    void updateAirportShouldReturnOkResponse() {
        Long airportId = 10L;
        AirportRequest request = new AirportRequest(
                "CDG",
                "Charles de Gaulle",
                ZoneId.of("Europe/Paris"),
                new Address("Paris", "75000"),
                1L,
                new GeoCode(49.0097, 2.5479)
        );

        AirportResponse expected = AirportResponse.builder()
                .id(airportId)
                .iataCode("CDG")
                .name("Charles de Gaulle")
                .detailedName("Charles de Gaulle Airport")
                .timeZoneId(ZoneId.of("Europe/Paris"))
                .address(new Address("Paris", "75000"))
                .city(CityResponse.builder().id(1L).name("Paris").cityCode("PAR").countryName("France").countryCode("FR").regionCode("IDF").timeZoneOffset("+01:00").build())
                .geoCode(new GeoCode(49.0097, 2.5479))
                .build();

        when(airportService.updateAirport(airportId, request)).thenReturn(expected);

        ResponseEntity<AirportResponse> response = airportController.updateAirport(airportId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(airportService).updateAirport(airportId, request);
    }

    @Test
    void deleteAirportShouldReturnOkResponse() {
        Long airportId = 10L;
        ApiResponse expected = new ApiResponse("Airport deleted successfully");

        doNothing().when(airportService).deleteAirport(airportId);

        ResponseEntity<ApiResponse> response = airportController.deleteAirport(airportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(airportService).deleteAirport(airportId);
    }
}
