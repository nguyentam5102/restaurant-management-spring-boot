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

//    @Bean("bean1")
//    @DependsOn("bean0")
//    CommandLineRunner commandLineRunner0(BillRepository billRepository, MenuRepository menuRepository){
//        return args -> {
//            BillDetail billDetail0 = new BillDetail();
//            billDetail0.setMenuItem(menuRepository.getById(1l));
//            billDetail0.setQuantity(2l);
//            BillDetail billDetail1 = new BillDetail();
//            billDetail1.setMenuItem(menuRepository.getById(2l));
//            billDetail1.setQuantity(3l);
//            Bill bill0 = new Bill(LocalDate.now(),
//                    LocalTime.now(),
//                    List.of(billDetail0,billDetail1)
//            );
////            Bill bill1 = new Bill(LocalDate.now(),
////                    LocalTime.now(),
////                    menuRepository.findAll()
////            );
//
//            billRepository.saveAll(
//                    List.of(bill0)
//            );
//        };
//    }
}
