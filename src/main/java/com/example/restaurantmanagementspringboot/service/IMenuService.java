package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.MenuItem;

import java.util.List;

public interface IMenuService {
    MenuItem getMenuItemById(Long menuItemId);

    List<MenuItem> getMenuItems(String status);

    List<MenuItem> getMenuItemsByType(String itemType);

    Long addNewMenuItem(MenuItem menuItem);

    void updateMenuItem(Long menuItemId, String description, Double price);

    void switchStatus(Long menuItemId);


}
