package com.cviews.abs.payload.request;

import com.cviews.abs.embeddable.Address;
import com.cviews.abs.embeddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZoneId;
import java.util.Objects;

public class AirportRequest {

    @NotBlank(message = "IATA code is required")
    @Size(min = 3, max = 3, message = "IATA code must be at exactly 3 characters")
    private final String iataCode;

    @NotBlank(message = "Airport name is required")
    private final String name;

    private final ZoneId timeZoneId;

    @Valid
    private final Address address;

    @NotNull(message = "City ID is required")
    private final Long cityId;

    @Valid
    private final GeoCode geoCode;

    public AirportRequest(String iataCode, String name, ZoneId timeZoneId, Address address, Long cityId, GeoCode geoCode) {
        this.iataCode = iataCode;
        this.name = name;
        this.timeZoneId = timeZoneId;
        this.address = address;
        this.cityId = cityId;
        this.geoCode = geoCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getName() {
        return name;
    }

    public ZoneId getTimeZoneId() {
        return timeZoneId;
    }

    public Address getAddress() {
        return address;
    }

    public Long getCityId() {
        return cityId;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AirportRequest that = (AirportRequest) o;
        return Objects.equals(iataCode, that.iataCode) && Objects.equals(name, that.name) && Objects.equals(timeZoneId, that.timeZoneId) && Objects.equals(address, that.address) && Objects.equals(cityId, that.cityId) && Objects.equals(geoCode, that.geoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, name, timeZoneId, address, cityId, geoCode);
    }

    @Override
    public String toString() {
        return "AirportRequest{" +
                "iataCode='" + iataCode + '\'' +
                ", name='" + name + '\'' +
                ", timeZoneId=" + timeZoneId +
                ", address=" + address +
                ", cityId=" + cityId +
                ", geoCode=" + geoCode +
                '}';
    }
}
