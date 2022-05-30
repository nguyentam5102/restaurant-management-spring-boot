package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        return new ResponseEntity<>(menuService.getMenuItems(status), HttpStatus.OK);
    }

    @GetMapping(path = "getItemById/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable("menuItemId") Long menuItemId) {
        return new ResponseEntity<>(menuService.getMenuItemById(menuItemId), HttpStatus.OK);
    }

    @GetMapping(path = "getItemByType/{itemType}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByType(@PathVariable("itemType") String itemType) {
        return new ResponseEntity<>(menuService.getMenuItemsByType(itemType), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addNewMenuItem(@RequestBody MenuItem menuItem) {
        Long menuItemId = menuService.addNewMenuItem(menuItem);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(menuItemId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "update/{menuItemId}")
    public ResponseEntity<HttpStatus> updateMenuItem(
            @PathVariable("menuItemId") Long menuItemId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {
        try {
            menuService.updateMenuItem(menuItemId, description, price);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new NumberFormatException();
        }
    }

    @PutMapping(path = "switchItemStatusById/{menuItemId}")
    public ResponseEntity<HttpStatus> switchMenuItemStatus(@PathVariable("menuItemId") Long menuItemId) {
        menuService.switchStatus(menuItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
