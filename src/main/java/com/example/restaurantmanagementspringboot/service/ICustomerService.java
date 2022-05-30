package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Customer getCustomerById(Long customerId);

    List<Customer> getCustomers();

    Optional<Customer> getCustomerByPhone(String customerPhone);

    void updateCustomer(Long customerId, String name, String phone);
}
