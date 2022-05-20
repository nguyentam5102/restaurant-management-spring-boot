package com.example.restaurantmanagementspringboot.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "bill")
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "bill_id")
    private Long id;

    private LocalDate date;
    private LocalTime time;
    private Double total;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillDetail> billDetails = new ArrayList<>();

    public Bill(LocalDate date, LocalTime time,Double total, List<BillDetail> billDetails) {
        this.date = date;
        this.time = time;
        this.total = total;
        this.billDetails = billDetails;
    }

    public Bill() {
    }

    public Bill(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public Double countTotal() {
        Double total = 0d;
        for (BillDetail billDetail : this.getBillDetails()){
            total += billDetail.getSubtotal();
        }
        return total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public void addBillDetail(BillDetail newBillDetail) {
        this.billDetails.add(newBillDetail);
    }
}
