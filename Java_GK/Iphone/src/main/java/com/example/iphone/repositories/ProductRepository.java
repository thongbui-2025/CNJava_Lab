package com.example.iphone.repositories;

import com.example.iphone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductsByBrandInAndPriceBetween(List<String> brands, double firstPrice, double secondPrice);

    List<Product> findProductsByPriceBetween(double minPrice, double maxPrice);

    List<Product> findProductsByBrandIn(List<String> brands);

    List<Product> findProductsByNameContainingIgnoreCase(String name);
}
