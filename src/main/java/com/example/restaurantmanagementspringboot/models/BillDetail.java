package com.example.restaurantmanagementspringboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bill_detail")
public class BillDetail {
    @EmbeddedId
    @JsonIgnore
    private BillMenuItemID billMenuItemID = new BillMenuItemID();

    @JsonIgnore
    @ManyToOne //(cascade = CascadeType.REFRESH)
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column
    private Long quantity;

    @Column
    private Double subtotal;

    public BillDetail() {
    }

    public BillMenuItemID getBillMenuItemID() {
        return billMenuItemID;
    }

    public void setBillMenuItemID(BillMenuItemID billMenuItemID) {
        this.billMenuItemID = billMenuItemID;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void countSubtotal(){
        this.setSubtotal(this.menuItem.getPrice() * this.getQuantity());
    }
}
