package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.exception.ItemNotAvailableException;
import com.example.restaurantmanagementspringboot.exception.ResourceNotFoundException;
import com.example.restaurantmanagementspringboot.model.Bill;
import com.example.restaurantmanagementspringboot.model.BillDetail;
import com.example.restaurantmanagementspringboot.model.Customer;
import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.repository.BillDetailRepository;
import com.example.restaurantmanagementspringboot.repository.BillRepository;
import com.example.restaurantmanagementspringboot.repository.CustomerRepository;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import com.example.restaurantmanagementspringboot.utils.MenuItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    private final BillRepository billRepository;
    private final MenuRepository menuRepository;
    private final CustomerRepository customerRepository;
    private final BillDetailRepository billDetailRepository;

    @Autowired
    public BillService(BillRepository billRepository, MenuRepository menuRepository, CustomerRepository customerRepository, BillDetailRepository billDetailRepository) {
        this.billRepository = billRepository;
        this.menuRepository = menuRepository;
        this.customerRepository = customerRepository;
        this.billDetailRepository = billDetailRepository;
    }

    public ResponseEntity<List<Bill>> getBills() {
        return new ResponseEntity<>(billRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Bill> getBillById(Long billId) {
        Optional<Bill> bill = billRepository.findById(billId);
        if (bill.isPresent())
            return new ResponseEntity<>(bill.get(), HttpStatus.OK);
        else
            throw new ResourceNotFoundException("Bill with ID " + billId + " does not exist");
    }

    @Transactional
    public ResponseEntity<HttpStatus> addNewBill(Bill bill) {

        Bill newBill = new Bill(LocalDateTime.now());
        String phone = bill.getCustomer().getPhone();

        Optional<Customer> customer = customerRepository.findByPhone(phone);
        if (customer.isPresent())
            newBill.setCustomer(customer.get());
        else {
            customerRepository.save(new Customer("Unnamed", phone));
            newBill.setCustomer(customerRepository.findByPhone(phone).get());
        }

        List<BillDetail> newBillDetails = bill.getBillDetails();
        fillBillDetailsOfNewBill(newBill, newBillDetails);

        newBill.countTotal();
        billRepository.save(newBill);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<HttpStatus> deleteBill(Long billId) {
        billRepository.findById(billId).orElseThrow(() ->
                new ResourceNotFoundException("Bill with ID " + billId + " does not exist")); // check if exists
        billRepository.deleteById(billId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> updateBillDetail(Long billId, Long menuItemId, Long newQuantity) {
        BillDetail billDetail = billDetailRepository.findBillDetailByBillIdAndMenuItemId(billId, menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Bill detail with Bill/Item IDs: " + billId + "/" + menuItemId
                                + " not found"));

        Bill billToUpdate = billRepository.findById(billId).orElseThrow(
                () -> new ResourceNotFoundException("Bill with ID " + billId + " does not exist"));

        if (newQuantity > 0) {
            billDetail.setQuantity(newQuantity);
            billDetail.countSubtotal();
        }
        if (newQuantity == 0) {
            billToUpdate.getBillDetails().remove(billDetail);
            billDetailRepository.delete(billDetail);
        }
        billToUpdate.countTotal();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void fillBillDetailsOfNewBill(Bill newBill, List<BillDetail> newBillDetails) {
        for (BillDetail newBillDetail : newBillDetails) {
            newBillDetail.setBill(newBill);
            MenuItem menuItem = menuRepository.findById(newBillDetail.getMenuItem().getId()).orElseThrow((() -> new ResourceNotFoundException
                    ("Item with ID "
                            + newBillDetail.getMenuItem().getId() + " does not exist")));
            if (menuItem.getStatus() == MenuItemStatus.ENABLED) {
                newBillDetail.setMenuItem(menuItem);
                newBill.addBillDetail(newBillDetail);
                newBillDetail.countSubtotal();
            }
            else
                throw new ItemNotAvailableException("Menu item with ID " + menuItem.getId() + "is currently not available");
        }
    }

}