package com.cart.backend.Configuration;

import com.cart.backend.Utils.JwtUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expire}")
    private int jwtExpire;

    @PostConstruct
    public void init() {
        JwtUtils.init(jwtSecret, jwtExpire);
    }
}
