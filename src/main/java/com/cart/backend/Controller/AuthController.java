package com.cart.backend.Controller;

import com.cart.backend.DAO.AuthMapper;
import com.cart.backend.Entity.Auth;
import com.cart.backend.Entity.AuthInfo;
import com.cart.backend.Entity.EditPassword;
import com.cart.backend.Entity.Result;
import com.cart.backend.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8081/user"})
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
        Auth returnAuth = authMapper.selectAuth(auth);
        if (returnAuth == null)
        {
            return Result.Fail("Failed to login: username or password incorrect");
        }
        else
        {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", returnAuth.getId());
            claims.put("username", returnAuth.getUsername());
            claims.put("role", returnAuth.getRole());
            return Result.Success(JwtUtils.GenJwt(claims));
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
        Auth returnAuth = authMapper.selectAuth(auth);
        if (returnAuth == null)
        {
            return Result.Fail("Failed to login: username or password incorrect");
        }
        else
        {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", returnAuth.getId());
            claims.put("username", returnAuth.getUsername());
            claims.put("role", returnAuth.getRole());
            return Result.Success(JwtUtils.GenJwt(claims));
        }
    }

    @RequestMapping("/api/user/info")
    public Result UserInfo(@RequestHeader("userToken") String userToken)
    {
        Map<String, Object> claims = JwtUtils.parseJwt(userToken);
        Integer id = (Integer)claims.get("id");

        Auth auth = authMapper.UserInfo(id);

        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(auth.getId());
        authInfo.setName(auth.getName());
        authInfo.setEmail(auth.getEmail());

        return Result.Success(authInfo);
    }

    @RequestMapping("/api/admin/info")
    public Result AdminInfo(@RequestHeader("adminToken") String adminToken)
    {
        Map<String, Object> claims = JwtUtils.parseJwt(adminToken);
        Integer id = (Integer)claims.get("id");

        Auth auth = authMapper.UserInfo(id);

        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(auth.getId());
        authInfo.setName(auth.getName());
        authInfo.setEmail(auth.getEmail());

        return Result.Success(authInfo);
    }

    @RequestMapping("/api/auth/user/editPassword")
    public Result UserEditPassword(@RequestBody EditPassword editPassword)
    {
        int result = authMapper.updateAuth(editPassword);
        if (result == 0)
        {
            return Result.Fail("Failed to update password");
        }
        else
        {
            return Result.Success("Success");
        }
    }

}
