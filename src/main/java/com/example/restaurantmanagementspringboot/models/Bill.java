package com.example.restaurantmanagementspringboot.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "bill")
@Table(name = "bill")
public class Bill {

    @Id
    @SequenceGenerator(
            name = "bill_sequence",
            sequenceName = "bill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bill_sequence"
    )
    private Long id;

    private LocalDate date;
    private LocalTime time;
    private Double subTotal;

    @ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE})
    @JoinTable(name = "bill_item",
        joinColumns = @JoinColumn(name = "menuItem_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"))
    private List<MenuItem> orderedMenuItems;

    public Bill(LocalDate date, LocalTime time, List<MenuItem> orderedMenuItems) {
        this.date = date;
        this.time = time;
        this.subTotal = subTotal;
        this.orderedMenuItems = orderedMenuItems;
    }

    public Bill() {}

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public List<MenuItem> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public void setOrderedMenuItems(List<MenuItem> orderedMenuItems) {
        this.orderedMenuItems = orderedMenuItems;
    }
}
