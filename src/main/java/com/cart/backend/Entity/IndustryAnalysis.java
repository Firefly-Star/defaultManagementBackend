package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndustryAnalysis {

    String Industry;
    Integer Year;
    BigDecimal DefaultCount;
    BigDecimal ResurrectCount;
    Long TotalClaims;
    BigDecimal DefaultPercentage;
    BigDecimal ResurrectPercentage;
    BigDecimal PreviousDefaultCount;
    BigDecimal PreviousResurrectCount;
    BigDecimal DefaultGrowthRate;
    BigDecimal ResurrectGrowthRate;

}
