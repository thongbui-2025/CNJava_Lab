package com.example.iphone.services;

import com.example.iphone.models.Account;
import com.example.iphone.models.CartManagement;
import com.example.iphone.repositories.AccountRepository;
import com.example.iphone.repositories.CartManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartManagementService {
    @Autowired
    CartManagementRepository cartManagementRepository;
    @Autowired
    AccountRepository accountRepository;
    public CartManagement save(CartManagement cartManagement){
        return cartManagementRepository.save(cartManagement);
    }
    public List<CartManagement> getAllCartManagementByUserAccount(String username){
        Optional<Account> account = accountRepository.findById(username);

        return cartManagementRepository.findByUserAccount(account);
    }
    public CartManagement findBySanPhamIdAndUserAccount(Long sanPhamId, Account username){
        return cartManagementRepository.findBySanPhamIdAndUserAccount(sanPhamId,username);
    }

    public void deleteCartManagement(CartManagement cartManagement){
        cartManagementRepository.delete(cartManagement);
    }
}
