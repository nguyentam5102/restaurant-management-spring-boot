package com.example.restaurantmanagementspringboot.repository;

import com.example.restaurantmanagementspringboot.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
