package com.example.iphone.controllers;

import com.example.iphone.models.Account;
import com.example.iphone.models.Product;
import com.example.iphone.services.AccountService;
import com.example.iphone.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/login")
    public String loginPage(Model model){
        if(httpSession.getAttribute("account")!=null){
            httpSession.removeAttribute("account");
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("listProduct",productList);
            return "index";
        }
        return "login";
    }
    @PostMapping("/")
    public String loginPageInfo(Model model, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account checkAcc = new Account(username,password);
        Account accRes = accountService.getAccount(checkAcc);
        if(accRes!=null){
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("listProduct",productList);
            session.setAttribute("account",accRes);
            return "index";
        }
        return "login";
    }
}
