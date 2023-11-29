package com.example.iphone.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private String brand;
    private String detail;
    private String image;
}
