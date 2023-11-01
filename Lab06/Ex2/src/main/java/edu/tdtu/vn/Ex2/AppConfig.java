package edu.tdtu.vn.Ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Product product1() {
        Product p = new Product();
        p.setId(1);
        p.setName("Fridge");
        p.setPrice(1000);
        return p;
    }

    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product(2, "Air conditioner", 200);
    }

    @Bean
    public Product product3() {
        return new Product(3, "Fan MU", 0);
    }
}
