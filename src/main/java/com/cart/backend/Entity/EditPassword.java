package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditPassword {

    private Integer id;
    private String oldPassword;
    private String newPassword;

}
