package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.models.Customer;
import com.example.restaurantmanagementspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String phone) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with ID"
                + customerId + " does not exist"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(customer.getName(), name)) {
            customer.setName(name);
        }
        if (phone != null &&
                phone.length() > 0 &&
                !Objects.equals(customer.getPhone(), phone))
            customer.setPhone(phone);
        customer.setPhone(phone);
    }
}
