package com.example.restaurantmanagementspringboot.repository;

import com.example.restaurantmanagementspringboot.models.MenuItem;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {

    @Query("SELECT i FROM MenuItem i WHERE i.name = ?1")
    Optional<MenuItem> findMenuItemByName(String name);

    List<MenuItem> findByStatus(MenuItemStatus menuItemStatus);
    Optional<MenuItem> findById(Long id);
}
