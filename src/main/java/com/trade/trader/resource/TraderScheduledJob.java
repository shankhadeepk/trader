package com.trade.trader.resource;

import com.trade.trader.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * The Scheduler at present runs every day once at 11:50 PM to check and update
 * the trade Maturity Flag
 */
@Component
public class TraderScheduledJob {

    private static final Logger log = LoggerFactory.getLogger(TraderScheduledJob.class);

    @Autowired
    private TradeService tradeService;

    @Scheduled(cron = "${trade.expiring.interval}")
    public void updatingMaturityDate(){
        log.info("The scheduler has started to update Maturity Flag");
        tradeService.updateMaturityFlag();
    }
}
