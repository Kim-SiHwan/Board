package jpaboard.jpaboard.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
@Builder
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
