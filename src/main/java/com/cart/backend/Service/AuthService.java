package com.cart.backend.Service;


import com.cart.backend.DAO.AuthMapper;
import com.cart.backend.Entity.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

    public void Register(Auth auth) {
        authMapper.insertAuth(auth);
    }

}
