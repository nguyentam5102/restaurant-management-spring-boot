package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMenuService {
    ResponseEntity<MenuItem> getMenuItemById(Long menuItemId);

    ResponseEntity<List<MenuItem>> getMenuItems(String status);

    ResponseEntity<List<MenuItem>> getMenuItemsByType(String itemType);

    ResponseEntity<HttpStatus> addNewMenuItem(MenuItem menuItem);

    ResponseEntity<HttpStatus> updateMenuItem(Long menuItemId, String description, Double price);

    ResponseEntity<HttpStatus> switchStatus(Long menuItemId);


}
