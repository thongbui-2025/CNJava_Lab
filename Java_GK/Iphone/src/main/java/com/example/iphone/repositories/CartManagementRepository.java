package com.example.iphone.repositories;

import com.example.iphone.models.Account;
import com.example.iphone.models.CartManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartManagementRepository extends JpaRepository<CartManagement,Long> {

    List<CartManagement> findByUserAccount(Optional <Account> account);

    CartManagement findBySanPhamIdAndUserAccount(Long sanPhamId, Account username);


}
