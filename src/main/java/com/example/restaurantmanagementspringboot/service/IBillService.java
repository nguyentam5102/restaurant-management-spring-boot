package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Bill;

import java.util.List;
import java.util.Optional;

public interface IBillService {
    List<Bill> getBills();
    Optional<Bill> getBillById(Long billId);
    Long addNewBill(Bill bill);
    void deleteBill(Long billId);
    void updateBillDetail(Long billId, Long menuItemId, Long newQuantity);

}
