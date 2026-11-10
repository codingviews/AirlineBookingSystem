package com.cviews.abs.embeddable;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class GeoCode {

    private final Double latitude;
    private final Double longitude;

    public GeoCode(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GeoCode geoCode = (GeoCode) o;
        return Objects.equals(latitude, geoCode.latitude) && Objects.equals(longitude, geoCode.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "GeoCode{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
