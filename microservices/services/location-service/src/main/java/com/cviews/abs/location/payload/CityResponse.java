package com.cviews.abs.location.payload;

import java.util.Objects;

public class CityResponse {

    private final Long id;
    private final String name;
    private final String cityCode;
    private final String countryName;
    private final String countryCode;
    private final String regionCode;
    private final String timeZoneOffset;

    public CityResponse(Long id, String name, String cityCode, String countryName, String countryCode, String regionCode, String timeZoneOffset) {
        this.id = id;
        this.name = name;
        this.cityCode = cityCode;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.regionCode = regionCode;
        this.timeZoneOffset = timeZoneOffset;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private String cityCode;
        private String countryName;
        private String countryCode;
        private String regionCode;
        private String timeZoneOffset;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder cityCode(String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public Builder countryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
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

        public CityResponse build() {
            return new CityResponse(id, name, cityCode, countryName, countryCode, regionCode, timeZoneOffset);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
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
        CityResponse that = (CityResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cityCode, that.cityCode) && Objects.equals(countryName, that.countryName) && Objects.equals(countryCode, that.countryCode) && Objects.equals(regionCode, that.regionCode) && Objects.equals(timeZoneOffset, that.timeZoneOffset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityCode, countryName, countryCode, regionCode, timeZoneOffset);
    }

    @Override
    public String toString() {
        return "CityResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", timeZoneOffset='" + timeZoneOffset + '\'' +
                '}';
    }
}
