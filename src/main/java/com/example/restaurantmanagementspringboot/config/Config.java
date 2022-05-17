package com.example.restaurantmanagementspringboot.config;

import com.example.restaurantmanagementspringboot.repository.BillRepository;
import com.example.restaurantmanagementspringboot.entity.Bill;
import com.example.restaurantmanagementspringboot.entity.ItemType;
import com.example.restaurantmanagementspringboot.entity.MenuItem;
import com.example.restaurantmanagementspringboot.entity.MenuItemStatus;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Bean("bean1")
    @DependsOn("bean0")
    CommandLineRunner commandLineRunner0(BillRepository billRepository, MenuRepository menuRepository){
        return args -> {
            Bill bill0 = new Bill(LocalDate.now(),
                    LocalTime.now(),
                    menuRepository.findAll()
            );
            Bill bill1 = new Bill(LocalDate.now(),
                    LocalTime.now(),
                    menuRepository.findAll()
            );

            billRepository.saveAll(
                    List.of(bill0, bill1)
            );
        };
    }
}
