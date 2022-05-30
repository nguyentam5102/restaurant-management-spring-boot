package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface IMenuService {
    Optional<MenuItem> getMenuItemById(Long menuItemId);
    List<MenuItem> getMenuItems(String status);
    Long addNewMenuItem(MenuItem menuItem);
    void updateMenuItem(Long menuItemId, String description, Double price);
    void switchStatus(Long menuItemId);
}
