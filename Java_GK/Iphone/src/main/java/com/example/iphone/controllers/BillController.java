package com.example.iphone.controllers;

import com.example.iphone.models.*;
import com.example.iphone.services.BillDetailService;
import com.example.iphone.services.BillService;
import com.example.iphone.services.CartManagementService;
import com.example.iphone.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    CartManagementService cartManagementService;
    @Autowired
    ProductService productService;
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    BillService billService;
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/order")
    public String getOrder(Model model) {
        Account account = (Account) session.getAttribute("account");
        double total = 0;
        List<CartManagement> cartManagementList = cartManagementService.getAllCartManagementByUserAccount(account.getUsername());
        for (CartManagement cartManagement : cartManagementList) {
            Product product = productService.getProductById(cartManagement.getSanPhamId());
            total += cartManagement.getTongTien();
        }
        //tạo ra bill mới
        Bill billAddLast = billService.save(new Bill(LocalDate.now(), total, account));
        // tạo các bill detail -> add vào bill
        List<BillDetail> billDetailList = new ArrayList<>();
        for (CartManagement cartManagement : cartManagementList) {
            Product product = productService.getProductById(cartManagement.getSanPhamId());
            BillDetail billDetail = new BillDetail(product.getPrice(), cartManagement.getSoLuong(), cartManagement.getTongTien(), product, billAddLast);
            billDetailService.save(billDetail);
            billDetailList.add(billDetail);

        }
        //xóa các cartmanagement sau khi add hoàn tất
        for (CartManagement cartManagement : cartManagementList) {
            cartManagementService.deleteCartManagement(cartManagement);
        }

        model.addAttribute("billDetailList", billDetailList);
        model.addAttribute("billInfor", billAddLast);
        model.addAttribute("fullname", request.getParameter("fullname"));
        model.addAttribute("address", request.getParameter("address"));
        model.addAttribute("phonenumber", request.getParameter("phonenumber"));
        return "order";
    }

//    @GetMapping("/order")
//    public String getOrder1() {
//        return "ERROR";
//    }

    @GetMapping("/purchase-history")
    public String getPurchaseHistory(Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            List<Bill> billList = billService.getAllBillByUserAcccount(account);
            model.addAttribute("billList", billList);
            return "history";
        }
        else {
            return "login";
        }
    }

    //   api trả về chi tiết bill
    @GetMapping("/billdetail/{id}")
    @ResponseBody
    public ResponseEntity<ResponseObject> getBillDetail(@PathVariable("id") Long billId) {
        List<BillDetail> billList = billDetailService.getBillDetailByBillId(billId);
        List<Product> productList = new ArrayList<>();
        for (BillDetail bill: billList) {
            Product product = productService.getProductById(bill.getProduct().getId());
            productList.add(product);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(productList));

    }
}
