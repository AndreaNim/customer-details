package com.app.webappdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    private int address_id;
    private int customer_id;
    private String email;
    private String street_address;
    private String street_address2;
    private String city;
    private String state;
    private String zip_code;

    public Address(int address_id, String street_address, String street_address2, String city, String state, String zip_code, int customer_id, String email) {
        this.address_id = address_id;
        this.street_address = street_address;
        this.street_address2 = street_address2;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.customer_id = customer_id;
        this.email = email;
    }

    public Address() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getStreet_address2() {
        return street_address2;
    }

    public void setStreet_address2(String street_address2) {
        this.street_address2 = street_address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
