package com.example.restaurantmanagementspringboot.repository;

import com.example.restaurantmanagementspringboot.models.BillDetail;
import com.example.restaurantmanagementspringboot.models.BillMenuItemID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillMenuItemID> {

    @Query("SELECT billDetail FROM BillDetail billDetail WHERE billDetail.bill.id = ?1" +
            " and billDetail.menuItem.id = ?2 ")
    Optional<BillDetail> findBillDetailByBillIdAndMenuItemId(Long billId, Long menuItemId);
}
