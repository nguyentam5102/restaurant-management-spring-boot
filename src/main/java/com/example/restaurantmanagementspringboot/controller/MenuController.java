package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.entity.MenuItem;
import com.example.restaurantmanagementspringboot.entity.MenuItemStatus;
import com.example.restaurantmanagementspringboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) { this.menuService = menuService; }

    @GetMapping
    public List<MenuItem> getMenuItems(@RequestParam(required = false) String status) {
        return menuService.getMenuItems(status);
    }

    @GetMapping(path = "{menuItemId}")
    public Optional<MenuItem> getMenuItemById(@PathVariable("menuItemId") Long menuItemId){
        return menuService.getMenuItemById(menuItemId);
    }

    @PostMapping
    public void registerNewMenuItem(@RequestBody MenuItem menuItem) {
        menuService.addNewMenuItem(menuItem);
    }

    @DeleteMapping(path = "{menuItemId}")
    public void deleteMenuItem(@PathVariable("menuItemId") Long menuItemId) {
        menuService.deleteMenuItem(menuItemId);
    }

    @PutMapping(path = "{menuItemId}")
    public void updateMenuItem(
            @PathVariable("menuItemId") Long studentId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) MenuItemStatus status) {
        menuService.updateStudent(studentId, description, price, status);
    }
}
