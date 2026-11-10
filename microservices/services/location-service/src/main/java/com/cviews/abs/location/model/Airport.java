package com.cviews.abs.location.model;


import com.cviews.abs.embeddable.Address;
import com.cviews.abs.embeddable.GeoCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Column(unique = true, nullable = false, length = 3)
    private final String iataCode;

    @Column(nullable = false)
    private final String name;

    @Embedded
    private final Address address;

    @Embedded
    private final GeoCode geoCode;

    @ManyToOne
    @JsonIgnore
    private final City city;

    public Airport(Long id, String iataCode, String name, Address address, GeoCode geoCode, City city) {
        this.id = id;
        this.iataCode = iataCode;
        this.name = name;
        this.address = address;
        this.geoCode = geoCode;
        this.city = city;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String iataCode;
        private String name;
        private Address address;
        private GeoCode geoCode;
        private City city;

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

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder geoCode(GeoCode geoCode) {
            this.geoCode = geoCode;
            return this;
        }

        public Builder city(City city) {
            this.city = city;
            return this;
        }

        public Builder cityId(Long cityId) {
            this.city = cityId == null ? null : City.builder().id(cityId).build();
            return this;
        }

        public Airport build() {
            return new Airport(id, iataCode, name, address, geoCode, city);
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

    public Address getAddress() {
        return address;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    public City getCity() {
        return city;
    }

    @JsonIgnore
    @Transient
    public String getDetailedName() {
        StringBuilder detailedName = new StringBuilder(name.toUpperCase());
        if (city != null) {
            detailedName.append("/").append(city.getCityCode().toUpperCase());
            if (city.getCountryName() != null) {
                detailedName.append("/").append(city.getCountryName().toUpperCase());
            }
        }
        return detailedName.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(id, airport.id) && Objects.equals(iataCode, airport.iataCode) && Objects.equals(name, airport.name) && Objects.equals(address, airport.address) && Objects.equals(geoCode, airport.geoCode) && Objects.equals(city, airport.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iataCode, name, address, geoCode, city);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", iataCode='" + iataCode + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", geoCode=" + geoCode +
                ", city=" + city +
                '}';
    }
}
