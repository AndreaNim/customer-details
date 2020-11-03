package com.app.webappdemo.services;

import com.app.webappdemo.model.Customer;

import java.util.List;

public class CustomerImpl extends CustomerService {
    @Override
    public List<Customer> listAllCustomers() {

        return super.listAllCustomers();
    }

    @Override
    public void registerCustomer(Customer customer) {

        super.registerCustomer(customer);
    }

    @Override
    public Customer getCustomer(Integer id) {
        return super.getCustomer(id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        super.deleteCustomer(id);
    }

    @Override
    public Customer CustomerByEmail(String email) {
        return super.CustomerByEmail(email);
    }

    @Override
    public Customer CustomerByEmailAndPassword(String email, String password) {
        if (email != null) email = email.toLowerCase();
        return super.CustomerByEmailAndPassword(email, password);
    }
}
