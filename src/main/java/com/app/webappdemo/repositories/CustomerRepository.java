package com.app.webappdemo.repositories;

import com.app.webappdemo.model.Address;
import com.app.webappdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);
    Customer findCustomerByEmailAndPassword(String email,String password);


}
