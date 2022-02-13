package com.trade.trader.exception;

/**
 * Custom exception created when Validation for
 * Version
 * Maturity Date
 * fails
 *
 */
public class InvalidTradeException extends Exception {

    private final String message;
    public InvalidTradeException(final String message) {
        super(message);
        this.message=message;
    }
}
