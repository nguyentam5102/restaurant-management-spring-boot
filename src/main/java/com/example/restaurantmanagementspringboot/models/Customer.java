package com.example.restaurantmanagementspringboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "customer_phone_unique", columnNames = "phone")
}
)
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private String name;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bill> customerBills = new ArrayList<>();

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Customer() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Bill> getCustomerBills() {
        return customerBills;
    }

    public void setCustomerBills(List<Bill> customerBills) {
        this.customerBills = customerBills;
    }
}
