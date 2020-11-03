package com.app.webappdemo.contorllers;

import com.app.webappdemo.exceptions.AuthException;
import com.app.webappdemo.exceptions.NotFoundException;
import com.app.webappdemo.model.Address;
import com.app.webappdemo.model.Address;
import com.app.webappdemo.model.Address;
import com.app.webappdemo.model.Customer;
import com.app.webappdemo.services.AddressService;
import com.app.webappdemo.services.AddressService;
import com.app.webappdemo.services.CustomerService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public List<Address> allAddresss() {
        try {
            return addressService.listAllAddresss();
        } catch (Exception e) {
            throw new NotFoundException("Addresss could not found");

        }

    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> addAddress(@RequestBody Address address) {
        try {

            String email = address.getEmail();
            Address existing = addressService.AddressByEmail(email);
            if (existing != null)
                updateAddressById(address);
            Customer existCustomer = customerService.CustomerByEmail(email);
            int customerId = existCustomer.getCustomerId();
            address.setCustomer_id(customerId);
            addressService.saveAddressDetails(address);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAddressById(@RequestBody Address address) {
        try {
            String addressEmail = address.getEmail();
            Address existAddress = addressService.AddressByEmail(addressEmail);
            System.out.println(address.getEmail());
            int addressId = existAddress.getAddress_id();
            Address address2 = addressService.getAddress(addressId);
            return new ResponseEntity<Address>(address2, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateAddressById(@RequestBody Address address) {
        try {
            String addressEmail = address.getEmail();
            Address existAddress = addressService.AddressByEmail(addressEmail);
            System.out.println(address.getEmail());
            int addressId = existAddress.getAddress_id();
            System.out.println(addressId);
            if (addressId > 0) {
                Customer existCustomer = customerService.CustomerByEmail(addressEmail);
                int customerId = existCustomer.getCustomerId();
                address.setCustomer_id(customerId);
                address.setAddress_id(addressId);
                addressService.saveAddressDetails(address);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                throw new AuthException("Invalid address Id");
        } catch (NoSuchElementException e) {
            throw new AuthException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable Integer id) {
        try {
            Address existAddress = addressService.getAddress(id);
            if (existAddress != null) {
                addressService.deleteAddress(id);
                System.out.println("sucessfully deleted");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new AuthException("Address is not found");
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
