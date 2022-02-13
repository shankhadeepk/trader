package com.trade.trader.resource;


import com.trade.trader.exception.InvalidTradeException;
import com.trade.trader.model.Trade;
import com.trade.trader.model.TradeResponse;
import com.trade.trader.service.TradeService;
import com.trade.trader.service.TradeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides the endpoints to perform operations on the "trades" resource
 *
 */
@RestController
@RequestMapping("/trades")
public class TraderResource {

    private static final Logger log = LoggerFactory.getLogger(TraderResource.class);

    /**
     * The flag can be set as per validation required
     * 1.   True - if Version validation required.
     * 2.   False - if Version Validation not required.
     */
    @Value("#{new Boolean('${enable.version.validity}')}")
    private Boolean isVersionValidationEnabled;

    /**
     * The flag can be set as per validation required
     * 1.   True - if Maturity Date check required
     * 2.   False - if Maturity Date check not required
     */
    @Value("#{new Boolean('${enable.maturityDate.validity}')}")
    private Boolean isMaturityDateValidationEnabled;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeValidator tradeValidator;

    /**
     * The method provides the endpoint to add or update trade resource
     *
     * @param trade
     * @return Response containing the success message "Trade added/updated successfully", if trade added or updated
     *         Successfully
     * @throws InvalidTradeException
     * @throws Exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TradeResponse> saveTrade(@RequestBody Trade trade) throws InvalidTradeException,Exception {
        log.info("Request received : {}",trade);
        if(isVersionValidationEnabled)
        tradeValidator.isVersionValid(trade);
        if(isMaturityDateValidationEnabled)
        tradeValidator.isMaturityDateLessThanTodays(trade);
        tradeService.saveTrade(trade);
        return new ResponseEntity<>(new TradeResponse("Trade added/updated successfully"), HttpStatus.CREATED);
    }

    /**
     * Provides endpoint to get the list of trades.
     *
     * @return  list of trades
     */
    @GetMapping
    public List<Trade> getAllTrades(){
        return tradeService.getAllTrades();
    }

}
