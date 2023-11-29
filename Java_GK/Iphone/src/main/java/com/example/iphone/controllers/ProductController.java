package com.example.iphone.controllers;

import com.example.iphone.models.Product;
import com.example.iphone.services.CartManagementService;
import com.example.iphone.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartManagementService cartManagementService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("listProduct", productList);
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detailPage(@PathVariable("id") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute(product);
        return "detail";
    }

    @PostMapping("/filter")
    public String getProductsFilter(@RequestParam(value = "brand", defaultValue = "") List<String> brands,
                                    @RequestParam(value = "minPrice", defaultValue = "0") double minPrice,
                                    @RequestParam(value = "maxPrice", defaultValue = "99999999") double maxPrice,
                                    Model model) {
        if (!brands.isEmpty() && maxPrice == 99999999) {
            List<Product> productList = productService.getProductsByBrands(brands);
            model.addAttribute("brandsChecked", brands);
            model.addAttribute("listProduct", productList);

        } else if (brands.isEmpty()) {
            if (minPrice < 0 || maxPrice < 0) {
                minPrice = 0;
                maxPrice = 77777777;
            }
            List<Product> productList = productService.getProductsByPriceRange(minPrice, maxPrice);
            model.addAttribute("minPrice", minPrice);
            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("listProduct", productList);
        } else {
            if (minPrice < 0 || maxPrice < 0) {
                minPrice = 0;
                maxPrice = 77777777;
            }
            List<Product> productList = productService.getProductsByBrandsAndPriceRange(brands, minPrice, maxPrice);
            model.addAttribute("brandsChecked", brands);
            model.addAttribute("minPrice", minPrice);
            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("listProduct", productList);
        }
        return "index";
    }

    @GetMapping("/filter")
    public String getProductsByName(@RequestParam("productName") String productName, Model model) {
        List<Product> productList = productService.getProductsByName(productName);
        model.addAttribute("productNameSearched", productName);
        model.addAttribute("listProduct", productList);
        return "index";
    }
}