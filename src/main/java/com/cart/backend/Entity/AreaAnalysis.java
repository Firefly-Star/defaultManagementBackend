package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaAnalysis {

    private int year;
    private String area;
    private int totalClaim;
    private int totalDefaultClaim;
    private int totalRebirthClaim;

}
