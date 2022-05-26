package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.Customer;
import com.example.restaurantmanagementspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "byId/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping(path = "byPhone/{customerPhone}")
    public Optional<Customer> getCustomerByPhone(@PathVariable("customerPhone") String customerPhone) {
        return customerService.getCustomerByPhone(customerPhone);
    }

    @PutMapping(path = "update/{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone
    ) {
        customerService.updateCustomer(customerId, name, phone);
    }
}
