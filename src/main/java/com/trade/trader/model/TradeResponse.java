package com.trade.trader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeResponse {

    @JsonProperty("message")
    private String message;

    public TradeResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
