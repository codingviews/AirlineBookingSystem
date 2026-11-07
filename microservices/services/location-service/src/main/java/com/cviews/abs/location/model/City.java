package com.cviews.abs.location.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cityCode;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String countryName;

    @Size(max = 10)
    private String regionCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZoneId;

    public City(Long id, String name, String cityCode, String countryCode, String countryName, String regionCode, String timeZoneId) {
        this.id = id;
        this.name = name;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.timeZoneId = timeZoneId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String cityCode;
        private String countryCode;
        private String countryName;
        private String regionCode;
        private String timeZoneId;

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

        public Builder timeZoneId(String timeZoneId) {
            this.timeZoneId = timeZoneId;
            return this;
        }

        public City build() {
            return new City(id, name, cityCode, countryCode, countryName, regionCode, timeZoneId);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(cityCode, city.cityCode) && Objects.equals(countryCode, city.countryCode) && Objects.equals(countryName, city.countryName) && Objects.equals(regionCode, city.regionCode) && Objects.equals(timeZoneId, city.timeZoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityCode, countryCode, countryName, regionCode, timeZoneId);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", timeZoneId='" + timeZoneId + '\'' +
                '}';
    }
}
