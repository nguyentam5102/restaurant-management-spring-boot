package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.Bill;
import com.example.restaurantmanagementspringboot.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        return new ResponseEntity<>(billService.getBills(), HttpStatus.OK);
    }

    @GetMapping(path = "/getBillById/{billId}")
    public ResponseEntity<Bill> getMenuItemById(@PathVariable("billId") Long billId) {
        return new ResponseEntity<>(billService.getBillById(billId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewBill(@RequestBody Bill bill) {
        Long billId = billService.addNewBill(bill);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(billId).toUri();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/detail_update/{billId}")
    public ResponseEntity<HttpStatus> updateBill(@PathVariable("billId") Long billId,
                                                 @RequestParam Long menuItemId,
                                                 @RequestParam Long itemQuantity) {
        billService.updateBillDetail(billId, menuItemId, itemQuantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(path = "{billId}")
    public void deleteMenuItem(@PathVariable("billId") Long billId) {
        billService.deleteBill(billId);
    }


}
