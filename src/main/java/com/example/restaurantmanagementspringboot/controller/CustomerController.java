package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.Customer;
import com.example.restaurantmanagementspringboot.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("getCustomers")
    public ResponseEntity<List<Customer>> getCustomers() {
        return customerService.getCustomers();
    }


    @GetMapping(path = "getCustomerById/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }


    @GetMapping(path = "getCustomerByPhone/{customerPhone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable("customerPhone") String customerPhone) {
        return customerService.getCustomerByPhone(customerPhone);
    }

    @PutMapping(path = "update/{customerId}")
    public ResponseEntity<HttpStatus> updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone
    ) {
        return customerService.updateCustomer(customerId, name, phone);
    }


}
