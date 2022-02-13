package com.trade.trader.service;

import com.trade.trader.exception.InvalidTradeException;
import com.trade.trader.model.Trade;
import com.trade.trader.repo.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TradeValidator {

    private static final Logger log = LoggerFactory.getLogger(TradeValidator.class);

    @Autowired
    private TradeRepository tradeRepository;

    public void isVersionValid(Trade newVersionTrade) throws InvalidTradeException{
        Optional<Trade> oldVersionTrade=tradeRepository.findById(newVersionTrade.getTradeId());
        if(oldVersionTrade.isPresent()){
            if(newVersionTrade.getVersion() < oldVersionTrade.get().getVersion()){
                log.error("The version of New Trade ID:{} is less than existing",newVersionTrade.getTradeId());
                throw new InvalidTradeException("The version Of Trade Id "+newVersionTrade.getTradeId()+" is less than existing");
            }
        }
    }

    public void isMaturityDateLessThanTodays(Trade trade) throws InvalidTradeException {
        if(trade.getMaturityDate()!=null && trade.getMaturityDate().isBefore(LocalDate.now())){
                log.error("The Trade Maturity Date is less than today date");
                throw new InvalidTradeException("The Trade Maturity Date is less than today date");
        }
    }
}
