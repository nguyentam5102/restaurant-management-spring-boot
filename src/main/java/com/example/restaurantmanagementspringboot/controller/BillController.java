package com.example.restaurantmanagementspringboot.controller;

import com.example.restaurantmanagementspringboot.model.Bill;
import com.example.restaurantmanagementspringboot.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getMenuItems() {
        return new ResponseEntity<>(billService.getBills(), HttpStatus.OK);
    }

    @GetMapping(path = "{billId}")
    public ResponseEntity<Optional<Bill>> getMenuItemById(@PathVariable("billId") Long billId) {
        return new ResponseEntity<>(billService.getBillById(billId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registerNewBill(@RequestBody Bill bill) {
        Long billId = billService.addNewBill(bill);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(billId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/detail_update/{billId}")
    public ResponseEntity<HttpStatus> updateBillDetail(@PathVariable("billId") Long billId,
                                                       @RequestParam Long menuItemId,
                                                       @RequestParam Long newQuantity) {
        billService.updateBillDetail(billId, menuItemId, newQuantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(path = "{billId}")
    public void deleteMenuItem(@PathVariable("billId") Long billId) {
        billService.deleteBill(billId);
    }


}
