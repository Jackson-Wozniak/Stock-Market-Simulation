package org.api.tradinggame.account.utils;

import org.api.tradinggame.account.exception.AccountBalanceException;
import org.api.tradinggame.account.model.entity.Account;

import java.text.DecimalFormat;

public class SetAccountBalance {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void setAccountBalance(Account account, double amountToAdd)
            throws AccountBalanceException {
        String formattedAmount = decimalFormat.format(
                account.getAccountBalance() + amountToAdd);
        account.setAccountBalance(Double.parseDouble(formattedAmount));
        if(account.getAccountBalance() < 0 ){
            throw new AccountBalanceException("Must have more money in account");
        }
    }
}
