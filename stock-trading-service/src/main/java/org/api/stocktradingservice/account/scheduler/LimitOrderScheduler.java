package org.api.stocktradingservice.account.scheduler;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.properties.SchedulingProperties;
import org.api.stocktradingservice.account.service.LimitOrderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class LimitOrderScheduler {

    private final LimitOrderService limitOrderService;

    @Scheduled(fixedRate = SchedulingProperties.EVERY_10_MINUTES)
    public void processOrders(){
        limitOrderService.processAllLimitOrders();
    }
}
