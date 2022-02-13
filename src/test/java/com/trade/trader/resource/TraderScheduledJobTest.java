package com.trade.trader.resource;

import com.trade.trader.TraderApplication;
import org.awaitility.Duration;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;


@SpringJUnitConfig(TraderApplication.class)
public class TraderScheduledJobTest {
    @SpyBean
    private TraderScheduledJob traderScheduledJob;

    @Test
    public void checkSchedulerToUpdateExpiryFlag(){
        await()
                .atMost(Duration.TWO_MINUTES)
                .untilAsserted(()->verify(traderScheduledJob,atLeast(2)).updatingMaturityDate());
    }

}