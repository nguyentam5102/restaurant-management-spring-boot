package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.Bill;
import com.example.restaurantmanagementspringboot.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/bill")
public class BillController {
    private final IBillService billService;

    @Autowired
    public BillController(IBillService billService) {
        this.billService = billService;
    }

    @GetMapping("/getBills")
    public ResponseEntity<List<Bill>> getMenuItems() {
        return billService.getBills();
    }

    @GetMapping(path = "/getBillById/{billId}")
    public ResponseEntity<Bill> getMenuItemById(@PathVariable("billId") Long billId) {
        return billService.getBillById(billId);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewBill(@RequestBody Bill bill) {
        return billService.addNewBill(bill);
    }

    @PutMapping(path = "/detail_update/{billId}")
    public ResponseEntity<HttpStatus> updateBill(@PathVariable("billId") Long billId,
                                                 @RequestParam Long menuItemId,
                                                 @RequestParam Long itemQuantity) {
        return billService.updateBillDetail(billId, menuItemId, itemQuantity);


    }

    @DeleteMapping(path = "{billId}")
    public ResponseEntity<HttpStatus> deleteMenuItem(@PathVariable("billId") Long billId) {
        return billService.deleteBill(billId);
    }


}
