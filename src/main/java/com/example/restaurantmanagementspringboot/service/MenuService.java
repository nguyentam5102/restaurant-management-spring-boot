package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Optional<MenuItem> getMenuItemById(Long menuItemId){
        return menuRepository.findById(menuItemId);
    }

    public List<MenuItem> getMenuItems(String status) {
        if (status == null)
            return menuRepository.findAll();
        return menuRepository.findByStatus(MenuItemStatus.valueOf(status.toUpperCase()));
    }


    public Long addNewMenuItem(MenuItem menuItem) {
        MenuItem createdMenuItem = menuRepository.save(menuItem);
        return createdMenuItem.getId();
    }

    public void deleteMenuItem(Long menuItemId) {
        boolean exists = menuRepository.existsById(menuItemId);
        if (!exists)
            throw new EntityNotFoundException(
                    "Item with ID " + menuItemId + " does not exist!");
        menuRepository.deleteById(menuItemId);
    }

    @Transactional
    public void updateMenuItem(Long menuItemId, String description, Double price) {
        MenuItem menuItem = menuRepository.findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Item with ID"
                        + menuItemId + " does not exist"));
        if (description != null &&
                description.length() > 0 &&
                !Objects.equals(menuItem.getDescription(), description)) {
            menuItem.setDescription(description);
        }
        if (price != null &&
                price > 0 &&
                !Objects.equals(menuItem.getPrice(), price))
            menuItem.setPrice(price);

    }

    @Transactional
    public void switchStatus(Long menuItemId) {
        MenuItem menuItem = menuRepository.findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Item with ID"
                        + menuItemId + " does not exist"));
       menuItem.switchStatus();
    }
}
