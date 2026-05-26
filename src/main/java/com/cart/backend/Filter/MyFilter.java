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

        if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        String url = req.getRequestURI();

        String jwtUser = req.getHeader("userToken");
        String jwtAdmin = req.getHeader("adminToken");

        if (url.contains("auth")) {
            chain.doFilter(request, response);
        } else if (url.contains("admin")) {
            if (!StringUtils.hasLength(jwtAdmin)) {
                res.getWriter().write(JSONObject.toJSONString(Result.Fail("Not Log in!")));
                return;
            }
            try {
                JwtUtils.parseJwt(jwtAdmin);
            } catch (Exception e) {
                res.getWriter().write(JSONObject.toJSONString(Result.Fail("Not Log in!")));
                return;
            }
            chain.doFilter(request, response);
        } else if (url.contains("user")) {
            if (!StringUtils.hasLength(jwtUser)) {
                res.getWriter().write(JSONObject.toJSONString(Result.Fail("Not Log in!")));
                return;
            }
            try {
                JwtUtils.parseJwt(jwtUser);
            } catch (Exception e) {
                res.getWriter().write(JSONObject.toJSONString(Result.Fail("Not Log in!")));
                return;
            }
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
