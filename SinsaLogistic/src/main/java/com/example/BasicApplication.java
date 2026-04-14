package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BasicApplication {
    public static void main(String[] args) {
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("HASH: " + encoder.encode("admin123"));
        
        SpringApplication.run(BasicApplication.class, args);
        
        System.out.println("HASH cliente123: " + encoder.encode("cliente123"));
        System.out.println("HASH admin123: " + encoder.encode("admin123"));
    }
}
    
