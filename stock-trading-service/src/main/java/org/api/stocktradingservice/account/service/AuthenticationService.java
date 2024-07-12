package org.api.stocktradingservice.account.service;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.payload.DepositRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;

    public Account authenticate(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (Account) authentication.getPrincipal();
    }

    public void createAccount(String username, String password){
        accountService.createNewAccount(username, password);
    }

    public void updateBalance(DepositRequest request){
        Account account = authenticate(request.getUsername(), request.getPassword());
        accountService.updateBalanceAndSave(account, request.getValue());
    }
}
