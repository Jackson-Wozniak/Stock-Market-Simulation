package org.api.stocktradingservice.account.service;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.exception.InvalidAccountException;
import org.api.stocktradingservice.account.model.payload.DepositRequest;
import org.api.stocktradingservice.account.repository.AccountRepository;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.exception.AccountNotFoundException;
import org.api.stocktradingservice.account.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByName(String username) throws AccountNotFoundException {
        return accountRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("No account with that username"));
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void createNewAccount(String username, String password) throws InvalidAccountException {
        if (accountExists(username)) {
            throw new InvalidAccountException("Account already exists with that username");
        }
        accountRepository.save(new Account(username, password));
    }

    public boolean accountExists(String username) {
        try {
            getAccountByName(username);
            return true;
        } catch (AccountNotFoundException ex) {
            return false;
        }
    }

    public void updateBalanceAndSave(DepositRequest request)
            throws AccountNotFoundException, AccountBalanceException {
        Account account = getAccountByName(request.getUsername());
        account.updateAccountBalance(request.getValue());
        saveAccount(account);
    }

    public void updateBalanceAndSave(Account account, double amountToAdd) {
        account.updateAccountBalance(amountToAdd);
        saveAccount(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("Cannot find " + username));
    }
}
