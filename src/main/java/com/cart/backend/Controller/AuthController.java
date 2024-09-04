package com.cart.backend.Controller;

import com.cart.backend.DAO.AuthMapper;
import com.cart.backend.Entity.Auth;
import com.cart.backend.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthMapper authMapper;

    @RequestMapping("/api/auth/user/register")
    public Result UserRegister(@RequestBody Auth auth)
    {
        auth.setRole("用户");
        authMapper.insertAuth(auth);
        if (auth.getId() != null)
        {
            return Result.Success("User register successful");
        }
        else
        {
            return Result.Fail("Failed to register user");
        }
    }

    @RequestMapping("/api/auth/user/login")
    public Result UserLogin(@RequestBody Auth auth)
    {
        auth.setRole("用户");
        Integer id = authMapper.selectAuth(auth);
        if (id == null)
        {
            return Result.Fail("Failed to login: username or password incorrect");
        }
        else
        {
            return Result.Success("User login successful");
        }
    }

    @RequestMapping("/api/auth/admin/register")
    public Result AdminRegister(@RequestBody Auth auth)
    {
        auth.setRole("管理员");
        authMapper.insertAuth(auth);
        if (auth.getId() != null)
        {
            return Result.Success("User register successful");
        }
        else
        {
            return Result.Fail("Failed to register user");
        }
    }

    @RequestMapping("/api/auth/admin/login")
    public Result AdminLogin(@RequestBody Auth auth)
    {
        auth.setRole("管理员");
        Integer id = authMapper.selectAuth(auth);
        if (id == null)
        {
            return Result.Fail("Failed to login: username or password incorrect");
        }
        else
        {
            return Result.Success("User login successful");
        }
    }


}
