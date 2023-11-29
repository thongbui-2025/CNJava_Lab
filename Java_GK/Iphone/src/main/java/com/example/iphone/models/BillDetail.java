package com.example.iphone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_billdetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double giaBan;
    private int soLuong;
    private double tongTien;
    @ManyToOne
    @JoinColumn(name = "fk_bill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name="fk_product")
    private Product product;

    public BillDetail(double giaBan, int soLuong, double tongTien, Product product, Bill bill){
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.product = product;
        this.bill = bill;
    }
}
