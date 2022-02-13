package com.trade.trader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class TradeError {

    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("errorMessage")
    private String errorMessage;

    public TradeError(HttpStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
