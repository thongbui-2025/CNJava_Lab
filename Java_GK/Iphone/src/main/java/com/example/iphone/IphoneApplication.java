package com.example.iphone;

import com.example.iphone.models.Account;
import com.example.iphone.models.Product;
import com.example.iphone.services.AccountService;
import com.example.iphone.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IphoneApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IphoneApplication.class, args);
    }


    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        Product.ProductBuilder productA =  Product.builder()
                .name("iPhone 14 Pro Max 128GB | Chính hãng VN/A")
                .price(26390000)
                .brand("Apple")
                .image("https://cdn2.cellphones.com.vn/358x358,webp,q100/media/catalog/product/3/_/3_51_1_7.jpg")
                .detail("Màn hình Dynamic Island - Sự biến mất của màn hình tai thỏ thay thế bằng thiết kế viên thuốc, OLED 6,7 inch, hỗ trợ always-on display <br> Cấu hình iPhone 14 Pro Max mạnh mẽ, hiệu năng cực khủng từ chipset A16 Bionic");
        Product.ProductBuilder productB =  Product.builder()
                .name("iPhone 13 128GB | Chính hãng VN/A")
                .price(16290000)
                .brand("Apple")
                .image("https://cdn2.cellphones.com.vn/358x358,webp,q100/media/catalog/product/1/4/14_1_9_2_9.jpg")
                .detail("Màn hình Dynamic Island - Sự biến mất của màn hình tai thỏ thay thế bằng thiết kế viên thuốc, OLED 6,7 inch, hỗ trợ always-on display <br> Cấu hình iPhone 14 Pro Max mạnh mẽ, hiệu năng cực khủng từ chipset A16 Bionic");
        Product.ProductBuilder productC =  Product.builder()
                .name("iPhone 14 128GB | Chính hãng VN/A")
                .price(18790000)
                .brand("Apple")
                .image("https://cdn2.cellphones.com.vn/358x358,webp,q100/media/catalog/product/i/p/iphone-14-storage-select-202209-6-1inch-y889.jpg")
                .detail("Màn hình Dynamic Island - Sự biến mất của màn hình tai thỏ thay thế bằng thiết kế viên thuốc, OLED 6,7 inch, hỗ trợ always-on display <br> Cấu hình iPhone 14 Pro Max mạnh mẽ, hiệu năng cực khủng từ chipset A16 Bionic");
        Product.ProductBuilder productD =  Product.builder()
                .name("iPhone 11 64GB | Chính hãng VN/A")
                .price(10550000)
                .brand("Apple")
                .image("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/5/_/5_158.jpg")
                .detail("Màn hình Dynamic Island - Sự biến mất của màn hình tai thỏ thay thế bằng thiết kế viên thuốc, OLED 6,7 inch, hỗ trợ always-on display <br> Cấu hình iPhone 14 Pro Max mạnh mẽ, hiệu năng cực khủng từ chipset A16 Bionic");
        Product.ProductBuilder product1 =  Product.builder()
                .name("SamSung Galaxy Z Fold5")
                .price(40990000)
                .brand("Samsung")
                .image("https://cdn2.cellphones.com.vn/358x358,webp,q100/media/catalog/product/b/4/b48cd136-7366-4d01-8d58-8ee3d5dc93b7_1.jpg")
                .detail("Samsung Z Fold5 được trang bị con chip Snapdragon 8 Gen 2 mạnh mẽ, bộ nhớ RAM 12GB <br> Bên cạnh đó, điện thoại Samsung này còn sở hữu tấm nền Dynamic AMOLED 2X");
        Product.ProductBuilder product2 =  Product.builder()
                .name("Samsung Galaxy S23 Ultra 256GB")
                .price(23790000)
                .brand("Samsung")
                .image("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/s/2/s23-ultra-xanh.png")
                .detail("Galaxy S23 Ultra 256GB được trang bị con chip Snapdragon 8 Gen 2 mạnh mẽ, bộ nhớ RAM 12GB <br> Bên cạnh đó, điện thoại Samsung này còn sở hữu tấm nền Dynamic AMOLED 2X");
        Product.ProductBuilder product3 =  Product.builder()
                .name("Nokia 105 4G Pro")
                .price(650000)
                .brand("Nokia")
                .image("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/n/o/nokia-105-4g-pro_8_.png")
                .detail("Màn hình IPS, kích thước chữ lớn cho hiển thị tốt hơn <br> Thiết kế nhỏ gọn cùng bàn phím lớn, phím nổi với độ đàn hồi cao");
        Product.ProductBuilder product4 =  Product.builder()
                .name("Nokia 8210 4G")
                .price(1490000)
                .brand("Nokia")
                .image("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/n/o/nokia-8210_4g-sand-front_back-int.png")
                .detail("Màn hình IPS, kích thước chữ lớn cho hiển thị tốt hơn <br> Thiết kế nhỏ gọn cùng bàn phím lớn, phím nổi với độ đàn hồi cao");
        Product.ProductBuilder product5 =  Product.builder()
                .name("Nokia 8210 4G")
                .price(1490000)
                .brand("Nokia")
                .image("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/n/o/nokia-8210_4g-sand-front_back-int.png")
                .detail("Màn hình IPS, kích thước chữ lớn cho hiển thị tốt hơn <br> Thiết kế nhỏ gọn cùng bàn phím lớn, phím nổi với độ đàn hồi cao");
        Account.AccountBuilder acc =  Account.builder()
                .username("vanthong")
                .password("12345");
        Account.AccountBuilder acc1 =  Account.builder()
                .username("thongbui")
                .password("12345");
        accountService.save(acc.build());
        accountService.save(acc1.build());
        productService.save(productA.build());
        productService.save(productB.build());
        productService.save(productC.build());
        productService.save(productD.build());
        productService.save(product1.build());
        productService.save(product2.build());
        productService.save(product3.build());
        productService.save(product4.build());
        productService.save(product5.build());
    }
}
