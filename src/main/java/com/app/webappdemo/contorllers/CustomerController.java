package com.app.webappdemo.contorllers;

import com.app.webappdemo.Constants.Constants;
import com.app.webappdemo.exceptions.AuthException;
import com.app.webappdemo.exceptions.NotFoundException;
import com.app.webappdemo.model.Customer;
import com.app.webappdemo.services.AddressService;
import com.app.webappdemo.services.CustomerService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
import io.jsonwebtoken.Jwts;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AddressService addressService;

    @GetMapping("")
    public List<Customer> AllCustomers() {
        return customerService.listAllCustomers();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> addCustomer(@RequestBody Customer customer) {
        try {
            String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt(10));
            customer.setPassword(hashedPassword);
            String email = customer.getEmail();
            //email format
            Pattern pattern = Pattern.compile("^(.+)@(.+)$");
            //setting the email to lowercase
            if (email != null) email = email.toLowerCase();
            if (!pattern.matcher(email).matches())
                throw new AuthException("Invalid email format");
            Customer existing = customerService.CustomerByEmail(email);
            if (existing != null)
                throw new AuthException("There is already an account registered with that email");
            customerService.registerCustomer(customer);
            return new ResponseEntity<>(generateJWTToken(customer), HttpStatus.OK);

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginCustomer(@RequestBody Customer customer) {
        String email = customer.getEmail();
        String password = customer.getPassword();
        System.out.println(customer.getEmail());
        System.out.println(customer.getPassword());
        if (email.isEmpty() || password.isEmpty())
            throw new NotFoundException("Invalid email or password ");
        try {

            Customer registredEmail = customerService.CustomerByEmail(email);
            int registredID = registredEmail.getCustomerId();
            if (!BCrypt.checkpw(password, registredEmail.getPassword())) {
                throw new AuthException("Invalid email or password");
            } else {
                System.out.println("login sucessful");
                Map<String, Integer> map = new HashMap<>();
                map.put("token", registredID);
                return new ResponseEntity<>(generateJWTToken(customer), HttpStatus.OK);
            }

        } catch (Exception e) {
            throw new AuthException("Invalid email or password");
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> getCustomerById(@RequestBody Customer customer) {
        try {
            String customerEmail = customer.getEmail();
            Customer existCustomer = customerService.CustomerByEmail(customerEmail);
            System.out.println(customer.getEmail());
            int customerId = existCustomer.getCustomerId();
            Customer customer2 = customerService.getCustomer(customerId);
            return new ResponseEntity<Customer>(customer2, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer) {
        try {
            String customerEmail = customer.getEmail();
            Customer existCustomer = customerService.CustomerByEmail(customerEmail);
            System.out.println(customer.getEmail());
            int customerId = existCustomer.getCustomerId();
            String password = existCustomer.getPassword();
            System.out.println(customerId);
            if (customerId > 0) {
                customer.setCustomerId(customerId);
                customer.setPassword(password);
                customerService.saveCustomerDetails(customer);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                throw new AuthException("Invalid customer Id");
        } catch (NoSuchElementException e) {
            throw new AuthException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Integer id) {
        try {
            Customer existCustomer = customerService.getCustomer(id);
            if (existCustomer != null) {
                customerService.deleteCustomer(id);
                addressService.deleteAddress(id);
                System.out.println("sucessfully deleted");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new AuthException("Customer is not found");
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private Map<String, String> generateJWTToken(Customer customer) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("customerId", customer.getCustomerId())
                .claim("email", customer.getEmail())
                .claim("firstName", customer.getFirstName())
                .claim("lastName", customer.getLastName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
