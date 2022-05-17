package com.example.restaurantmanagementspringboot.models;

import javax.persistence.Entity;

@Entity
public class BillMenuItem extends MenuItem{
    private Long quantity;
}
