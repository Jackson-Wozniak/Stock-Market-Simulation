package org.api.stocktradingservice.account.service;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.exception.AccountNotFoundException;
import org.api.stocktradingservice.account.model.payload.StockTransactionRequest;
import org.api.stocktradingservice.account.repository.LimitOrderRepository;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.LimitOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LimitOrderService {

    @Autowired
    private final LimitOrderRepository limitOrderRepository;
    @Autowired
    private final StockOwnedService stockOwnedService;

    public void saveLimitOrder(LimitOrder limitOrder) {
        limitOrderRepository.save(limitOrder);
    }

    public void deleteLimitOrder(LimitOrder limitOrder) {
        limitOrderRepository.delete(limitOrder);
    }

    public List<LimitOrder> findLimitOrdersByAccount(Account account) {
        return limitOrderRepository.findAll().stream()
                .filter(order -> order.getAccount().getUsername().equals(account.getUsername()))
                .collect(Collectors.toList());
    }

    public void processAllLimitOrders() {
        limitOrderRepository.findAll().forEach(order -> {
            if (order.getLimitPrice() < 100.0) {
                try {
                    stockOwnedService.buyStock(new StockTransactionRequest(
                            order.getAccount().getUsername(),
                            order.getAccount().getPassword(),
                            order.getTicker(),
                            order.getSharesToBuy()));
                    clearAndDeleteLimitOrder(order);
                } catch (AccountNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Transactional
    public void truncateLimitOrders() {
        limitOrderRepository.truncateTable();
    }

    private void clearAndDeleteLimitOrder(LimitOrder limitOrder) {
        limitOrder.setAccount(null);
        limitOrder.setTicker(null);

        saveLimitOrder(limitOrder);
        deleteLimitOrder(limitOrder);
    }
}
