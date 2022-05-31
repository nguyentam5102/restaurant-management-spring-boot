package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.exception.ResourceNotFoundException;
import com.example.restaurantmanagementspringboot.model.Customer;
import com.example.restaurantmanagementspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Customer> getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent())
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        else
        throw  new ResourceNotFoundException("Item with ID "
                + customerId + " does not exist");
    }

    public ResponseEntity<Customer> getCustomerByPhone(String customerPhone) {
        Optional<Customer> customer = customerRepository.findByPhone(customerPhone);
        if (customer.isPresent())
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        else
            throw  new ResourceNotFoundException("Item with ID "
                    + customerPhone + " does not exist");    }

    @Transactional
    public ResponseEntity<HttpStatus> updateCustomer(Long customerId, String name, String phone) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with ID "
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
