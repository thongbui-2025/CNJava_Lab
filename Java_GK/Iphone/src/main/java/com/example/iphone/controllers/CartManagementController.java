package com.example.iphone.controllers;

import com.example.iphone.models.Account;
import com.example.iphone.models.CartManagement;
import com.example.iphone.models.Product;
import com.example.iphone.services.CartManagementService;
import com.example.iphone.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartManagementController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    CartManagementService cartManagementService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    ProductService productService;

    @PostMapping("/cart")
    public String addToCart(Model model) {
        // Check login
        double total = 00.;
        if (httpSession.getAttribute("account") != null) {
            Long sanPhamId = Long.valueOf(request.getParameter("sanPhamId"));
            double tongTien = Double.valueOf(request.getParameter("tongTien"));
            Account account = (Account) httpSession.getAttribute("account");

            // Check if the item is already in the cart
            CartManagement cart = cartManagementService.findBySanPhamIdAndUserAccount(sanPhamId, account);
            if (cart != null) {
                cart.setSoLuong(cart.getSoLuong() + 1);
                cart.setTongTien(cart.getTongTien() + tongTien);
                cartManagementService.save(cart);
            } else {
                int soLuong = 1;
                cartManagementService.save(new CartManagement(sanPhamId, soLuong, tongTien, account));
            }

            List<CartManagement> cartManagementList = cartManagementService.getAllCartManagementByUserAccount(account.getUsername());
            List<Product> productList = new ArrayList<>();
            for (CartManagement cartManagement : cartManagementList) {
                Product product = productService.getProductById(cartManagement.getSanPhamId());
                productList.add(product);
                total += cartManagement.getTongTien();
            }
            model.addAttribute("listProductOnCart", productList);
            model.addAttribute("cartManagementList", cartManagementList);
            model.addAttribute("totalBill", total);
            return "cart";
        }
        return "/login";
//        return "cart";
    }
    @GetMapping("/cart")
    public String getAllCart(Model model){
        Account account = (Account) httpSession.getAttribute("account");
        double total = 00.;
        if(account!=null){
            List<CartManagement> cartManagementList = cartManagementService.getAllCartManagementByUserAccount(account.getUsername());
            List<Product> productList = new ArrayList<>();
            for (CartManagement cartManagement : cartManagementList) {
                Product product = productService.getProductById(cartManagement.getSanPhamId());
                productList.add(product);
                total += cartManagement.getTongTien();
            }
            model.addAttribute("listProductOnCart",productList);
            model.addAttribute("cartManagementList", cartManagementList);
        }
        model.addAttribute("totalBill",total);
        return "cart";
    }
    @PostMapping("/cart/{id}")
    public String deleteProductOnCart(@PathVariable("id") Long productID, Model model){
        Account account = (Account) httpSession.getAttribute("account");
        double total = 00.;
        CartManagement cartManagement = cartManagementService.findBySanPhamIdAndUserAccount(productID,account);
        if(cartManagement!=null){
            cartManagementService.deleteCartManagement(cartManagement);
        }
        List<CartManagement> cartManagementList = cartManagementService.getAllCartManagementByUserAccount(account.getUsername());
        List<Product> productList = new ArrayList<>();
        for (CartManagement cartManagementValue : cartManagementList) {
            Product product = productService.getProductById(cartManagementValue.getSanPhamId());
            productList.add(product);
            total += cartManagementValue.getTongTien();
        }
        model.addAttribute("listProductOnCart", productList);
        model.addAttribute("cartManagementList", cartManagementList);
        model.addAttribute("totalBill", total);
        return "redirect:/cart";
    }
    @GetMapping("cart/minus/{id}")
    public String reducingProductOnCart(@PathVariable("id") Long productId, Model model){
        double total = 00.;
        if (httpSession.getAttribute("account") != null) {
            Account account = (Account) httpSession.getAttribute("account");

            // Check if the item is already in the cart
            CartManagement cart = cartManagementService.findBySanPhamIdAndUserAccount(productId, account);
            Product productInfor = productService.getProductById((productId));
            if(cart.getSoLuong()>1) {
                cart.setSoLuong(cart.getSoLuong() - 1);
                cart.setTongTien(cart.getTongTien() - productInfor.getPrice());
                cartManagementService.save(cart);
            }
            List<CartManagement> cartManagementList = cartManagementService.getAllCartManagementByUserAccount(account.getUsername());
            List<Product> productList = new ArrayList<>();
            for (CartManagement cartManagement : cartManagementList) {
                Product product = productService.getProductById(cartManagement.getSanPhamId());
                productList.add(product);
                total += cartManagement.getTongTien();
            }
            model.addAttribute("listProductOnCart", productList);
            model.addAttribute("cartManagementList", cartManagementList);
            model.addAttribute("totalBill", total);
        }
        return "redirect:/cart";
    }
    @GetMapping("cart/add/{id}")
    public String increasingProductOnCart(@PathVariable("id") Long productId, Model model){
        double total = 00.;
        if (httpSession.getAttribute("account") != null) {
            Account account = (Account) httpSession.getAttribute("account");

            // Check if the item is already in the cart
            CartManagement cart = cartManagementService.findBySanPhamIdAndUserAccount(productId, account);
            Product productInfor = productService.getProductById((productId));
            if (cart != null) {
                cart.setSoLuong(cart.getSoLuong() + 1);
                cart.setTongTien(cart.getTongTien() + productInfor.getPrice());
                cartManagementService.save(cart);
            } else {
                int soLuong = 1;
                cartManagementService.save(new CartManagement(productId, soLuong, productInfor.getPrice(), account));
            }

            List<CartManagement> cartManagementList = cartManagementService.getAllCartManagementByUserAccount(account.getUsername());
            List<Product> productList = new ArrayList<>();
            for (CartManagement cartManagement : cartManagementList) {
                Product product = productService.getProductById(cartManagement.getSanPhamId());
                productList.add(product);
                total += cartManagement.getTongTien();
            }
            model.addAttribute("listProductOnCart", productList);
            model.addAttribute("cartManagementList", cartManagementList);
            model.addAttribute("totalBill", total);
        }
        return "redirect:/cart";
    }
}
