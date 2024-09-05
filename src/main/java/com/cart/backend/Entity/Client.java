package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Integer id;
    private String name;
    private String gender;
    private String industry;
    private String area;
    private String phone;

}
