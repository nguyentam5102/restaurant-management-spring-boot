package com.example.restaurantmanagementspringboot.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class BillMenuItemID implements Serializable {

    private Long billId;
    private Long menuItemId;

    public BillMenuItemID() {

    }
}
