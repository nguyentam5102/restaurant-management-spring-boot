package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {
    ResponseEntity<Customer> getCustomerById(Long customerId);

    ResponseEntity<List<Customer>> getCustomers();

    ResponseEntity<Customer> getCustomerByPhone(String customerPhone);

    ResponseEntity<HttpStatus> updateCustomer(Long customerId, String name, String phone);
}
