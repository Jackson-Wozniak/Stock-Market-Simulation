package stocktradingsimulator.account.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.exception.InvalidAccountException;
import stocktradingsimulator.account.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountByName(String username) throws AccountNotFoundException {
        return accountRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("No account with that username"));
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public void createNewAccount(String username) throws InvalidAccountException {
        if(doesAccountExist(username)){
            throw new InvalidAccountException("Account already exists with that username");
        }
        accountRepository.save(new Account(username));
    }

    public boolean doesAccountExist(String username){
        try{
            getAccountByName(username);
            return true;
        }catch(AccountNotFoundException ex){
            return false;
        }
    }
}
