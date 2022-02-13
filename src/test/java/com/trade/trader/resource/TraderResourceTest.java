package com.trade.trader.resource;

import com.trade.trader.exception.InvalidTradeException;
import com.trade.trader.model.Trade;
import com.trade.trader.model.TradeResponse;
import com.trade.trader.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TraderResourceTest {

    public static final String TRADES="/trades";

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TraderResource traderResource;

    @Test
    public void checkTradeWhenAllParametersAreGood() throws Exception {
        // A trade with higer version added
        Trade trade=new Trade();
        trade.setTradeId("T3");
        trade.setVersion(3);
        trade.setExpired("N");
        trade.setBookId("B2");
        trade.setCounterPartyId("CP-3");
        trade.setMaturityDate("20/05/2022");

        ResponseEntity<TradeResponse> resp=traderResource.saveTrade(trade);
        TradeResponse tResponse=resp.getBody();

        assertEquals(201,resp.getStatusCode().value());
        assertEquals("Trade added/updated successfully",tResponse.getMessage());
    }

    @Test(expected = InvalidTradeException.class)
    public void checkTradeWhenLowerVersionIsAdded() throws Exception {
        // A trade with higer version added
        Trade trade=new Trade();
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate("20/05/2021");
        tradeService.saveTrade(trade);

        // A new trade added with less version
        trade=new Trade();
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate("20/05/2021");

        ResponseEntity resp=traderResource.saveTrade(trade);
    }

    @Test(expected = InvalidTradeException.class)
    public void checkTradeWhenMaturityDateIsMoreThanTodayDate() throws Exception {
        // A trade with higer version added
        Trade trade=new Trade();
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setExpired("N");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate("20/05/2021");

        ResponseEntity resp=traderResource.saveTrade(trade);
    }


}