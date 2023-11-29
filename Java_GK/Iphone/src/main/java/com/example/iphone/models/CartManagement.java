package com.example.iphone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_cartmanagement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long sanPhamId;
    private int soLuong;
    private Double tongTien;
    @ManyToOne
    @JoinColumn(name="fk_username")
    private Account userAccount;

    public CartManagement(Long sanPhamId, int soLuong, Double tongTien, Account userAccount){
        this.sanPhamId = sanPhamId;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.userAccount = userAccount;
    }
}
