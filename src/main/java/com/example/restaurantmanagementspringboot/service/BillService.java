package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.models.Bill;
import com.example.restaurantmanagementspringboot.models.BillDetail;
import com.example.restaurantmanagementspringboot.models.MenuItem;
import com.example.restaurantmanagementspringboot.repository.BillRepository;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final MenuRepository menuRepository;
    @Autowired
    public BillService(BillRepository billRepository, MenuRepository menuRepository) {
        this.billRepository = billRepository;
        this.menuRepository = menuRepository;
    }


    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public void addNewBill(Bill bill) {
        Bill newBill = new Bill(LocalDate.now(), LocalTime.now());
        List<BillDetail> newBillDetails = bill.getBillDetails();
        for (BillDetail newBillDetail : newBillDetails) {
            newBillDetail.setBill(newBill);
            MenuItem menuItem = menuRepository.getById(newBillDetail.getMenuItem().getId());
            newBillDetail.setMenuItem(menuItem);
            newBill.addBillDetail(newBillDetail);
            Double subtotal = menuItem.getPrice() * newBillDetail.getQuantity();
            newBillDetail.setSubtotal(subtotal);
        }
        newBill.setTotal(bill.countTotal());

        billRepository.save(newBill);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }
}
