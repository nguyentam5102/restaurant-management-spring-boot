package com.example.restaurantmanagementspringboot.config;

import com.example.restaurantmanagementspringboot.utils.ItemType;
import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Bean("bean0")
    CommandLineRunner commandLineRunner(MenuRepository menuRepository){
        return args -> {
            MenuItem rice = new MenuItem(ItemType.FOOD,
                    "Rice",
                    "rice",
                    "rice.img",
                    45000.0,
                    MenuItemStatus.ENABLED);
            MenuItem wine = new MenuItem(ItemType.ALCOHOL,
                    "Wine",
                    "wine",
                    "wine.img",
                    60000.0,
                    MenuItemStatus.DISABLED);

            menuRepository.saveAll(
                    List.of(rice, wine)
            );
        };
    }

}
