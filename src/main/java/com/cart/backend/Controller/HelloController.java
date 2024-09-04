package com.cart.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/Hello")
    public String Hello()
    {
        System.out.println("Hello World!");
        return "Hello World!";
    }


}
