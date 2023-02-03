package org.api.tradinggame.leaderboard;

import org.api.tradinggame.account.model.entity.Account;

import java.util.Comparator;
import java.util.List;

public class SortAccountProfits {

    public static List<Account> sortAccountByProfits(List<Account> accounts) {
        accounts.sort(new Comparator<Account>() {
            @Override
            public int compare(Account account1, Account account2) {
                return account2.getTotalProfits().compareTo(account1.getTotalProfits());
            }
        });
        return accounts;
    }
}
