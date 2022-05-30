package com.example.restaurantmanagementspringboot.service;

import com.example.restaurantmanagementspringboot.model.Bill;
import com.example.restaurantmanagementspringboot.model.BillDetail;
import com.example.restaurantmanagementspringboot.model.Customer;
import com.example.restaurantmanagementspringboot.model.MenuItem;
import com.example.restaurantmanagementspringboot.repository.BillDetailRepository;
import com.example.restaurantmanagementspringboot.repository.BillRepository;
import com.example.restaurantmanagementspringboot.repository.CustomerRepository;
import com.example.restaurantmanagementspringboot.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long billId) {
        return billRepository.findById(billId);
    }

    @Transactional
    public Long addNewBill(Bill bill) {
        // Generate bill's date and time
        Bill newBill = new Bill(LocalDate.now(), LocalTime.now());
        String phone = bill.getCustomer().getPhone();

        // Find customer by phone, create new Customer if not found
        Optional<Customer> customer = customerRepository.findByPhone(phone);
        if (customer.isPresent())
            newBill.setCustomer(customer.get());
        else {
            customerRepository.save(new Customer("Unnamed", phone));
            newBill.setCustomer(customerRepository.findByPhone(phone).get());
        }

        // Fill bill details with information given from request
        List<BillDetail> newBillDetails = bill.getBillDetails();
        fillBillDetailsOfNewBill(newBill, newBillDetails);

        newBill.countTotal();

        // Save bill
        Bill createdBill = billRepository.save(newBill);
        return createdBill.getId();

    }

    public void deleteBill(Long billId) {
        menuRepository.findById(billId); // check if exists
        billRepository.deleteById(billId);
    }

    @Transactional
    public void updateBillDetail(Long billId, Long menuItemId, Long newQuantity) {
        BillDetail billDetail = billDetailRepository.findBillDetailByBillIdAndMenuItemId(billId, menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Bill detail not found"));

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

    private void fillBillDetailsOfNewBill(Bill newBill, List<BillDetail> newBillDetails) {
        for (BillDetail newBillDetail : newBillDetails) {
            newBillDetail.setBill(newBill);
            MenuItem menuItem = menuRepository.getById(newBillDetail.getMenuItem().getId());
            newBillDetail.setMenuItem(menuItem);
            newBill.addBillDetail(newBillDetail);
            newBillDetail.countSubtotal();
        }
    }


}