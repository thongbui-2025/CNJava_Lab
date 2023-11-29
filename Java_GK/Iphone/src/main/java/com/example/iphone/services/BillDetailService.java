package com.example.iphone.services;

import com.example.iphone.models.BillDetail;
import com.example.iphone.repositories.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    public BillDetail save(BillDetail billDetail){
        return billDetailRepository.save(billDetail);
    }

    public List<BillDetail> getBillDetailByBillId(Long billId){
        return billDetailRepository.getBillDetailByBill_Id(billId);
    }

}
