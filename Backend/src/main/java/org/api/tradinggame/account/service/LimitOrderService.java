package org.api.tradinggame.account.service;

import lombok.AllArgsConstructor;
import org.api.tradinggame.account.exception.AccountNotFoundException;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.LimitOrder;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.repository.LimitOrderRepository;
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
            if (order.getLimitPrice() < order.getStock().getPrice()) {
                try {
                    stockOwnedService.buyStock(new BuyStockRequest(
                            order.getAccount().getUsername(),
                            order.getStock().getTicker(),
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
        limitOrder.setStock(null);

        saveLimitOrder(limitOrder);
        deleteLimitOrder(limitOrder);
    }
}
