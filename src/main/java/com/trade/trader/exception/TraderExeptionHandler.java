package com.trade.trader.exception;


import com.trade.trader.model.TradeError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The exception handler maps the Invalid exception to appropriate
 * Handlers.
 *
 */
@ControllerAdvice
public class TraderExeptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(TraderExeptionHandler.class);

    @ExceptionHandler({InvalidTradeException.class})
    public ResponseEntity<TradeError> handletInvalidTrade(InvalidTradeException ex){
        log.error("The version of Trade is less than existing ",ex);
        TradeError tradeError=new TradeError(HttpStatus.NOT_FOUND,ex.getMessage());
        return new ResponseEntity<>(tradeError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<TradeError> handleAllException(Exception ex){
        log.error("Exception has occurred :",ex);
        TradeError error =new TradeError(HttpStatus.INTERNAL_SERVER_ERROR,"Failed! Exception has occurred, Please check");
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
