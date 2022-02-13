package com.trade.trader.resource;

import com.trade.trader.TraderApplication;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;


import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class TraderScheduledJobTest {
    @SpyBean
    private TraderScheduledJob traderScheduledJob;

    @Test
    public void checkSchedulerToUpdateExpiryFlag(){
        await()
                .atMost(Duration.ONE_MINUTE)
                .untilAsserted(()->verify(traderScheduledJob,times(1)).updatingMaturityDate());
    }

}