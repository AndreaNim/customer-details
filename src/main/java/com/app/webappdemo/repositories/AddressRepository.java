package com.app.webappdemo.repositories;

import com.app.webappdemo.model.Address;
import com.app.webappdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findCustomerByEmail(String email);

}
