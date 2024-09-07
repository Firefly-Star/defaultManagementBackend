package com.cart.backend.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimSelectParam {

    Integer id;
    String clientName;
    String reviewerName;
    String reviewStatus;
    String reasonName;
    String severity;
    Date claimTime;
    Date reviewTime;
    String externSeverity;
    String type;

    Integer claimerId;
    Integer reviewerId;

}
