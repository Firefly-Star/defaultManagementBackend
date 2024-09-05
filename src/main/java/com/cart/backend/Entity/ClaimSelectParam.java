package com.cart.backend.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimSelectParam {

    String clientName;
    String reasonName;
    String reviewStatus;
    String severity;
    Date claimTime;
    Date reviewTime;
    String externSeverity;
    String type;

    Integer claimerId;
    Integer reviewerId;

}
