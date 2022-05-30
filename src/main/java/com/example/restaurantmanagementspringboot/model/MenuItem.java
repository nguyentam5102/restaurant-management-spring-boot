package com.example.restaurantmanagementspringboot.model;

import com.example.restaurantmanagementspringboot.utils.ItemType;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menuItem",
        uniqueConstraints = {
                @UniqueConstraint(name = "menu_item_name_unique", columnNames = "name")
        }
)
public class MenuItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "menu_item_id")
    private Long id;

    @NotNull(message = "type is mandatory, not null")
    private ItemType type;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "description is mandatory")
    private String description;

    private String imgUrl;

    @NotNull
    private Double price;
    private MenuItemStatus status = MenuItemStatus.ENABLED;

    @JsonIgnore
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    private List<BillDetail> billDetails = new ArrayList<>();

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

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
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

    public void switchStatus() {
        if (this.getStatus() == MenuItemStatus.ENABLED)
            this.setStatus(MenuItemStatus.DISABLED);
        else
            this.setStatus(MenuItemStatus.ENABLED);
    }
}
