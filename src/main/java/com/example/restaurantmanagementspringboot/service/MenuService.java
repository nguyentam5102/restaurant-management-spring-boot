package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.exception.ResourceNotFoundException;
import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import com.example.restaurantmanagementspringboot.utils.ItemType;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public ResponseEntity<MenuItem> getMenuItemById(Long menuItemId) {
        Optional<MenuItem> menuItem = menuRepository.findById(menuItemId);
        if (menuItem.isPresent())
            return new ResponseEntity<>(menuItem.get(), HttpStatus.OK);
        else throw new ResourceNotFoundException("Item with ID"
                + menuItemId + " does not exist");
    }

    public ResponseEntity<List<MenuItem>> getMenuItems(String status) {
        if (status == null)
            return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
        else
            return new ResponseEntity<>(menuRepository.findByStatus(MenuItemStatus.valueOf(status.toUpperCase())), HttpStatus.OK);
    }

    public ResponseEntity<List<MenuItem>> getMenuItemsByType(String itemType) {
        return new ResponseEntity<>(menuRepository.findByType(ItemType.valueOf(itemType.toUpperCase())), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addNewMenuItem(MenuItem menuItem) {
        menuRepository.save(menuItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<HttpStatus> updateMenuItem(Long menuItemId, String description, Double price) {
        MenuItem menuItem = menuRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item with ID "
                        + menuItemId + " does not exist"));
        if (description != null && description.length() > 0 && !Objects.equals(menuItem.getDescription(), description)) {
            menuItem.setDescription(description);
        }
        if (price != null && price > 0 && !Objects.equals(menuItem.getPrice(), price))
            menuItem.setPrice(price);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<HttpStatus> switchStatus(Long menuItemId) {
        MenuItem menuItem = menuRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item with ID "
                        + menuItemId + " does not exist"));
        menuItem.switchStatus();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
