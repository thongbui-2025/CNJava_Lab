package com.example.iphone.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_bill")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate ngayLap;

    private double tongCong;

    @ManyToOne
    @JoinColumn(name="fk_username")
    private Account userAccount;

    public Bill(LocalDate ngayLap, double tongCong, Account userAccount){
        this.ngayLap = ngayLap;
        this.tongCong = tongCong;
        this.userAccount = userAccount;
    }
}
