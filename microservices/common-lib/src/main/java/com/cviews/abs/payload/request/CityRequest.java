package com.cviews.abs.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private final String name;

    @NotBlank(message = "City code is required")
    @Size(max = 10, message = "City code must not exceed 10 characters")
    private final String cityCode;

    @NotBlank(message = "Country code is required")
    @Size(max = 5, message = "Country code must not exceed 5 characters")
    private final String countryCode;

    @NotBlank(message = "Country name is required")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private final String countryName;

    @Size(max = 10, message = "Region code must not exceed 10 characters")
    private final String regionCode;

    @Size(max = 10, message = "TimeZone offset must not exceed 10 characters")
    private final String timeZoneOffset;

    public CityRequest(String name, String cityCode, String countryCode, String countryName, String regionCode, String timeZoneOffset) {
        this.name = name;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.timeZoneOffset = timeZoneOffset;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String cityCode;
        private String countryCode;
        private String countryName;
        private String regionCode;
        private String timeZoneOffset;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder cityCode(String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder countryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder regionCode(String regionCode) {
            this.regionCode = regionCode;
            return this;
        }

        public Builder timeZoneOffset(String timeZoneOffset) {
            this.timeZoneOffset = timeZoneOffset;
            return this;
        }

        public CityRequest build() {
            return new CityRequest(name, cityCode, countryCode, countryName, regionCode, timeZoneOffset);
        }
    }

    public String getName() {
        return name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getTimeZoneOffset() {
        return timeZoneOffset;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CityRequest that = (CityRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(cityCode, that.cityCode) && Objects.equals(countryCode, that.countryCode) && Objects.equals(countryName, that.countryName) && Objects.equals(regionCode, that.regionCode) && Objects.equals(timeZoneOffset, that.timeZoneOffset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityCode, countryCode, countryName, regionCode, timeZoneOffset);
    }

    @Override
    public String toString() {
        return "CityRequest{" +
                "name='" + name + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", timeZoneOffset='" + timeZoneOffset + '\'' +
                '}';
    }
}
