package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Customer;
import com.example.restaurantmanagementspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByPhone(String customerPhone) {
        return customerRepository.findByPhone(customerPhone);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String phone) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with ID"
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
