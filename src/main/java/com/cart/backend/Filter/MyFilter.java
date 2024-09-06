package com.cart.backend.Filter;


import com.alibaba.fastjson.JSONObject;
import com.cart.backend.Entity.Result;
import com.cart.backend.Utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 设置 CORS 相关响应头，允许跨域
        res.setHeader("Access-Control-Allow-Origin", "*"); // 设置允许的源
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"); // 设置允许的方法
        res.setHeader("Access-Control-Allow-Headers", "userToken, adminToken, Content-Type"); // 设置允许的请求头
        res.setHeader("Access-Control-Allow-Credentials", "true"); // 允许携带凭证（如Cookie）

        // 如果是 OPTIONS 请求，直接返回成功
        if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        String url = req.getRequestURI();

        String jwtUser = req.getHeader("userToken");
        String jwtAdmin = req.getHeader("adminToken");

        if (url.contains("auth"))
        {
            chain.doFilter(request, response);
            return;
        }
        else if (url.contains("admin"))
        {
            if (!StringUtils.hasLength(jwtAdmin))
            {
                Result err = Result.Fail("Not Log in!");
                String s = JSONObject.toJSONString(err);
                res.getWriter().write(s);
                return;
            }

            try {
                JwtUtils.parseJwt(jwtAdmin);
            }catch (Exception e)
            {
                e.printStackTrace();
                Result err = Result.Fail("Not Log in!");
                String s = JSONObject.toJSONString(err);
                res.getWriter().write(s);
                return;
            }

            chain.doFilter(request, response);
        }
        else if (url.contains("user"))
        {
            if (!StringUtils.hasLength(jwtUser))
            {
                Result err = Result.Fail("Not Log in!");
                String s = JSONObject.toJSONString(err);
                res.getWriter().write(s);
                return;
            }

            try {
                JwtUtils.parseJwt(jwtUser);
            }catch (Exception e)
            {
                e.printStackTrace();
                Result err = Result.Fail("Not Log in!");
                String s = JSONObject.toJSONString(err);
                res.getWriter().write(s);
                return;
            }

            chain.doFilter(request, response);
        }
    }

}
