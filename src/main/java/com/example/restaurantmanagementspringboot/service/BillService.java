package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.models.Bill;
import com.example.restaurantmanagementspringboot.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public void addNewBill(Bill bill) {
            billRepository.save(bill);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }
}
