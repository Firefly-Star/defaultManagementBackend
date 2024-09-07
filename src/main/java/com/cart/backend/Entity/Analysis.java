package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Analysis {

    private int year;
    private int totalClaims;
    private int totalDefaultClaims;
    private int totalRebirthClaims;

}
