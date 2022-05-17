package com.example.restaurantmanagementspringboot.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table( name = "menu",
        uniqueConstraints = {
                @UniqueConstraint(name = "menu_item_name_unique", columnNames = "name")
        }
        )
public class MenuItem {

    @Id
    @SequenceGenerator(
            name = "menuItem_sequence",
            sequenceName = "menuItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "menuItem_sequence"
    )
    private Long id;
    private ItemType type;
    private String name;
    private String description;
    private String imgUrl;
    private Double price;
    private MenuItemStatus status = MenuItemStatus.ENABLED;

    public MenuItem(ItemType type, String name, String description, String imgUrl, Double price, MenuItemStatus menuItemStatus) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.status = menuItemStatus;
    }

    public MenuItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MenuItemStatus getStatus() {
        return status;
    }

    public void setStatus(MenuItemStatus status) {
        this.status = status;
    }

}
