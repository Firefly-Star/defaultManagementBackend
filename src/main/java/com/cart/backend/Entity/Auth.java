package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {

    Integer id;
    String username;
    String password;
    String email;
    String role;

}
