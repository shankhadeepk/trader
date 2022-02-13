package com.trade.trader.service;

import com.trade.trader.model.Trade;
import com.trade.trader.repo.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 *  The service shared methods that can be executed on the Trade in Store
 */
@Service
public class TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrades(){
        return tradeRepository.findAll();
    }
    public void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    public void updateMaturityFlag() {
        tradeRepository.findAll().stream().forEach(trade -> {
            if((trade.getMaturityDate().isEqual(LocalDate.now()) || trade.getMaturityDate().isBefore(LocalDate.now()))
                    && "N".equalsIgnoreCase(trade.getExpired())){
                trade.setExpired("Y");
                tradeRepository.save(trade);
                log.info("The trade with id = {} is updated",trade.getTradeId());
            }
        });
    }
}
