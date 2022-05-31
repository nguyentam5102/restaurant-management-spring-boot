package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/menu")
public class MenuController {

    private final IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("getItems")
    public ResponseEntity<List<MenuItem>> getMenuItems(@RequestParam(required = false) String status) {
        return menuService.getMenuItems(status);
    }

    @GetMapping(path = "getItemById/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable("menuItemId") Long menuItemId) {
        return menuService.getMenuItemById(menuItemId);
    }

    @GetMapping(path = "getItemByType/{itemType}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByType(@PathVariable("itemType") String itemType) {
        return menuService.getMenuItemsByType(itemType);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.addNewMenuItem(menuItem);
    }

    @PutMapping(path = "update/{menuItemId}")
    public ResponseEntity<HttpStatus> updateMenuItem(
            @PathVariable("menuItemId") Long menuItemId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {
        return menuService.updateMenuItem(menuItemId, description, price);
    }

    @PutMapping(path = "switchItemStatusById/{menuItemId}")
    public ResponseEntity<HttpStatus> switchMenuItemStatus(@PathVariable("menuItemId") Long menuItemId) {
        return menuService.switchStatus(menuItemId);
    }


}
