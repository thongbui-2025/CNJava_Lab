package com.example.iphone.repositories;

import com.example.iphone.models.Account;
import com.example.iphone.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> getAllByUserAccount(Account username);
}
