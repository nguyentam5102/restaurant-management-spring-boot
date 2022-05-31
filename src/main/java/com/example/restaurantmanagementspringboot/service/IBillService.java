package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBillService {
    ResponseEntity<List<Bill>> getBills();

    ResponseEntity<Bill> getBillById(Long billId);

    ResponseEntity<HttpStatus> addNewBill(Bill bill);

    ResponseEntity<HttpStatus> deleteBill(Long billId);

    ResponseEntity<HttpStatus> updateBillDetail(Long billId, Long menuItemId, Long newQuantity);

}
