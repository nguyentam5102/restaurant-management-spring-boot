package com.example.restaurantmanagementspringboot.repository;

import com.example.restaurantmanagementspringboot.entity.Bill;
import com.example.restaurantmanagementspringboot.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
