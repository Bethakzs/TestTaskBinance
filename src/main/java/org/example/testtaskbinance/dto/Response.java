package org.example.testtaskbinance.dto;

import lombok.Data;

@Data
public class Response {
    private String symbol;
    private String markPrice;
    private String indexPrice;
    private String estimatedSettlePrice;
    private String lastFundingRate;
    private Long nextFundingTime;
    private String interestRate;
    private Long time;
}
