package com.app.webappdemo.services;

import com.app.webappdemo.model.Customer;
import com.app.webappdemo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    public void registerCustomer(Customer customer) {

        customerRepository.save(customer);
    }
    public void saveCustomerDetails(Customer customer) {

        customerRepository.save(customer);
    }

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id).get();
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public Customer CustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    public Customer CustomerByEmailAndPassword(String email, String password) {
        return customerRepository.findCustomerByEmailAndPassword(email, password);
    }
}
