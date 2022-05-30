package com.example.restaurantmanagementspringboot.repository;

import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByStatus(MenuItemStatus menuItemStatus);

    Optional<MenuItem> findById(Long id);
}
