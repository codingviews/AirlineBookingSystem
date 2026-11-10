package com.cviews.abs.embeddable;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    private final String street;
    private final String postalCode;

    public Address(String street, String postalCode) {
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
