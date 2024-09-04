package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reason {

    private Integer id;
    private String cause;
    private int enable;
    private String type;

}
