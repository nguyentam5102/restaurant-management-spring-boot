package com.example.restaurantmanagementspringboot.model;


import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime dateTime;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillDetail> billDetails = new ArrayList<>();

    public Bill() {
    }

    public Bill(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void countTotal() {
        Double total = 0d;
        for (BillDetail billDetail : this.getBillDetails()) {
            total += billDetail.getSubtotal();
        }
        this.setTotal(total);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public void removeBillDetail(BillDetail billDetailToRemove) {
        for (int i = 0; i < billDetails.size(); i++)
            if (billDetailToRemove.getMenuItem().getId() ==
                    billDetails.get(i).getMenuItem().getId()) {
                billDetails.remove(i);
                return;
            }
    }
}
