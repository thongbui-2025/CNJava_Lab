package com.example.iphone.services;

import com.example.iphone.models.Product;
import com.example.iphone.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public Product getProductById(Long id){
        return productRepository.getById(id);
    }

    public List<Product> getProductsByBrandsAndPriceRange(List<String> brands, double minPrice, double maxPrice){
        return productRepository.findProductsByBrandInAndPriceBetween(brands, minPrice, maxPrice);
    }
    public List<Product> getProductsByBrands(List<String> brands){
        return productRepository.findProductsByBrandIn(brands);
    }
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findProductsByPriceBetween(minPrice, maxPrice);
    }
    public List<Product> getProductsByName(String name){
        return productRepository.findProductsByNameContainingIgnoreCase(name);
    }
}
