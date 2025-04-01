package org.api.stocktradingservice.account.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*
Util class to map operations to response entities
 */
public class AccountControllerUtils {

    public static ResponseEntity<String> createdAccount(String username, double balance){
        return new ResponseEntity<>(
                String.format("Account created, user '%s' with balance %.2f", username, balance),
                HttpStatus.CREATED);
    }

    public static ResponseEntity<String> depositedToAccount(String username, double newBalance){
        return ResponseEntity.ok(String.format("User '%s' balance updated. New balance: %.2f", username, newBalance));
    }
}
