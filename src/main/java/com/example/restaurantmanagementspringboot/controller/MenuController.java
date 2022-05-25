package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.models.MenuItem;
import com.example.restaurantmanagementspringboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getMenuItems(@RequestParam(required = false) String status) {
        return new ResponseEntity<>(menuService.getMenuItems(status), HttpStatus.OK);
    }

    @GetMapping(path = "{menuItemId}")
    public ResponseEntity<Optional<MenuItem>> getMenuItemById(@PathVariable("menuItemId") Long menuItemId) {
        return new ResponseEntity<>(menuService.getMenuItemById(menuItemId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewMenuItem(@RequestBody MenuItem menuItem) {
        Long menuItemId = menuService.addNewMenuItem(menuItem);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(menuItemId).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "{menuItemId}")
    public void deleteMenuItem(@PathVariable("menuItemId") Long menuItemId) {
        menuService.deleteMenuItem(menuItemId);
    }

    @PutMapping(path = "{menuItemId}")
    public ResponseEntity<HttpStatus> updateMenuItem(
            @PathVariable("menuItemId") Long menuItemId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {
        menuService.updateMenuItem(menuItemId, description, price);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "switch/{menuItemId}")
    public ResponseEntity<HttpStatus> switchMenuItemStatus(@PathVariable("menuItemId") Long menuItemId) {
        menuService.switchStatus(menuItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
