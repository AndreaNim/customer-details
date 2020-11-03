package com.app.webappdemo.services;

import com.app.webappdemo.model.Address;
import com.app.webappdemo.model.Customer;
import com.app.webappdemo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> listAllAddresss() {
        return addressRepository.findAll();
    }

    public void saveAddressDetails(Address customer) {

        addressRepository.save(customer);
    }

    public Address getAddress(Integer id) {
        return addressRepository.findById(id).get();
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
    public Address AddressByEmail(String email) {
        return addressRepository.findCustomerByEmail(email);
    }

}
