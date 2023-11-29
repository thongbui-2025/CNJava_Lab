package com.example.iphone.services;

import com.example.iphone.models.Account;
import com.example.iphone.models.Bill;
import com.example.iphone.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    public Bill save(Bill billDetail){
        return billRepository.save(billDetail);
    }

    public List<Bill> getAllBillByUserAcccount(Account useraccount){
        return billRepository.getAllByUserAccount(useraccount);
    }
}
