package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.models.Bill;
import com.example.restaurantmanagementspringboot.models.BillDetail;
import com.example.restaurantmanagementspringboot.models.Customer;
import com.example.restaurantmanagementspringboot.models.MenuItem;
import com.example.restaurantmanagementspringboot.repository.BillDetailRepository;
import com.example.restaurantmanagementspringboot.repository.BillRepository;
import com.example.restaurantmanagementspringboot.repository.CustomerRepository;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BillService {
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


    public List<Bill> getBills() {
        return billRepository.findAll();
    }


    public void addNewBill(Bill bill) {
        // Generate bill's date and time
        Bill newBill = new Bill(LocalDate.now(), LocalTime.now());
        String phone = bill.getCustomer().getPhone();

        // Find customer by phone, create new Customer if not found
        try {
            Customer customer = customerRepository.findByPhone(phone).get();
            newBill.setCustomer(customer);
        } catch (Exception e) {
            customerRepository.save(new Customer("Noname", phone));
            newBill.setCustomer(customerRepository.findByPhone(phone).get());
        }

        // Fill bill details with information given from request
        List<BillDetail> newBillDetails = bill.getBillDetails();
        for (BillDetail newBillDetail : newBillDetails) {
            newBillDetail.setBill(newBill);
            MenuItem menuItem = menuRepository.getById(newBillDetail.getMenuItem().getId());
            newBillDetail.setMenuItem(menuItem);
            newBill.addBillDetail(newBillDetail);
            newBillDetail.countSubtotal();
        }
        newBill.countTotal();

        // Save bill
        billRepository.save(newBill);

    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    @Transactional
    public void updateBillDetail(Long billId, Long menuItemId, Long newQuantity) {

        BillDetail billDetail = billDetailRepository.findBillDetailByBillIDAndMenuItemId(billId, menuItemId)
                .orElseThrow(() -> new IllegalStateException("Bill detail not found"));
        Bill billToUpdate = billRepository.getById(billId);

        if (newQuantity > 0) {
            billDetail.setQuantity(newQuantity);
            billDetail.countSubtotal();
        }
        if (newQuantity == 0) {
            billToUpdate.getBillDetails().remove(billDetail);
            billDetailRepository.delete(billDetail);
        }
        billToUpdate.countTotal();

    }
}