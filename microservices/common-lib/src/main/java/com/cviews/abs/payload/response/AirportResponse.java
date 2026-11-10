package com.cviews.abs.payload.response;

import com.cviews.abs.embeddable.Address;
import com.cviews.abs.embeddable.GeoCode;
import com.cviews.abs.payload.CityResponse;

import java.time.ZoneId;
import java.util.Objects;

public class AirportResponse {

    private final Long id;
    private final String iataCode;
    private final String name;
    private final String detailedName;
    private final ZoneId timeZoneId;
    private final Address address;
    private final CityResponse city;
    private final GeoCode geoCode;

    public AirportResponse(Long id, String iataCode, String name, String detailedName, ZoneId timeZoneId, Address address, CityResponse city, GeoCode geoCode) {
        this.id = id;
        this.iataCode = iataCode;
        this.name = name;
        this.detailedName = detailedName;
        this.timeZoneId = timeZoneId;
        this.address = address;
        this.city = city;
        this.geoCode = geoCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String iataCode;
        private String name;
        private String detailedName;
        private ZoneId timeZoneId;
        private Address address;
        private CityResponse city;
        private GeoCode geoCode;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder iataCode(String iataCode) {
            this.iataCode = iataCode;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder detailedName(String detailedName) {
            this.detailedName = detailedName;
            return this;
        }

        public Builder timeZoneId(ZoneId timeZoneId) {
            this.timeZoneId = timeZoneId;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder city(CityResponse city) {
            this.city = city;
            return this;
        }

        public Builder geoCode(GeoCode geoCode) {
            this.geoCode = geoCode;
            return this;
        }

        public AirportResponse build() {
            return new AirportResponse(id, iataCode, name, detailedName, timeZoneId, address, city, geoCode);
        }
    }

    public Long getId() {
        return id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getName() {
        return name;
    }

    public String getDetailedName() {
        return detailedName;
    }

    public ZoneId getTimeZoneId() {
        return timeZoneId;
    }

    public Address getAddress() {
        return address;
    }

    public CityResponse getCity() {
        return city;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AirportResponse that = (AirportResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(iataCode, that.iataCode) && Objects.equals(name, that.name) && Objects.equals(detailedName, that.detailedName) && Objects.equals(timeZoneId, that.timeZoneId) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(geoCode, that.geoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iataCode, name, detailedName, timeZoneId, address, city, geoCode);
    }

    @Override
    public String toString() {
        return "AirportResponse{" +
                "id=" + id +
                ", iataCode='" + iataCode + '\'' +
                ", name='" + name + '\'' +
                ", detailedName='" + detailedName + '\'' +
                ", timeZoneId=" + timeZoneId +
                ", address=" + address +
                ", city=" + city +
                ", geoCode=" + geoCode +
                '}';
    }
}
