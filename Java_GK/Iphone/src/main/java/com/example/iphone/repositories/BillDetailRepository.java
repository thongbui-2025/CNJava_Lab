package com.example.iphone.repositories;

import com.example.iphone.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail,Long> {

    List<BillDetail> getBillDetailByBill_Id(Long billId);
}
