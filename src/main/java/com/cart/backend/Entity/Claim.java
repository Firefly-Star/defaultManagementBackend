package com.cart.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

    Integer id;
    Integer clientId;
    Integer reasonId;
    String reviewStatus;
    String severity;
    Integer reviewerId;
    Timestamp claimTime;
    Timestamp reviewTime;
    String externSeverity;
    String type;
    Integer claimerId;

}
