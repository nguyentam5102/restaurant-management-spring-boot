package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.service.BillService;
import com.example.restaurantmanagementspringboot.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getMenuItems() {
        return billService.getBills();
    }

    @PostMapping
    public void registerNewBill(@RequestBody Bill bill) {
        billService.addNewBill(bill);
    }

    @PutMapping(path = "/detail_update/{billId}")
    public void updateBillDetail(@PathVariable("billId") Long billId,
                                 @RequestParam Long menuItemId,
                                 @RequestParam Long newQuantity){
        billService.updateBillDetail(billId, menuItemId, newQuantity);

    }

    @DeleteMapping(path = "{billId}")
    public void deleteMenuItem(@PathVariable("billId") Long billId) {
        billService.deleteBill(billId);
    }
}
