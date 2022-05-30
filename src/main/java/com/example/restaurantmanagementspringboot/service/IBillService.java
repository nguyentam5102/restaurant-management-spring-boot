package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> getBills();

    Bill getBillById(Long billId);

    Long addNewBill(Bill bill);

    void deleteBill(Long billId);

    void updateBillDetail(Long billId, Long menuItemId, Long newQuantity);

}
