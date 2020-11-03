package com.app.webappdemo.model;

import javax.persistence.*;
@Entity
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String mobile_number;
    @Column(unique = true)
    private String email ;
    private String password;
    private String date_of_birth;
    private String phone_number;



    public Customer(int customerId, String firstName, String lastName, String mobile_number, String email, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile_number = mobile_number;
        this.email = email;
        this.password = password;

    }


    public Customer(int customerId, String firstName, String lastName, String mobile_number, String email, String password, String date_of_birth, String phone_number) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile_number = mobile_number;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
    }

    public Customer() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
