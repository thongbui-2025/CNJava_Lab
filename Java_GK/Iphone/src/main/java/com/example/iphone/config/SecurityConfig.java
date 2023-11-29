package com.example.iphone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/cart").permitAll()
                        .requestMatchers("/purchase-history").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .permitAll()
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .successForwardUrl("/")
                )
                .httpBasic(withDefaults());
        return http.build();
    }
    //    không bị lock resources
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/resources/static/*").anyRequest();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("vanthong")
                .password("12345")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

}
