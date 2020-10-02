package service;

import entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static validator.AccountValidator.*;

public class FilterAccounts {

    public List<Account> filterData(List<Account> accounts) {

        List<Account> accountsResult = new ArrayList<>();
        accounts
                .forEach(a -> {
                    if (isCurrencyCorrect(a)
                            && isBalanceCorrect(a)
                            && isClosingDateCorrect(a)
                            && isIbanCorrect(a.getIban())) {
                        accountsResult.add(a);
                    }
                });
        Logger.getAnonymousLogger().log(Level.INFO, "accounts filtered successfully");
        return accountsResult;
    }


}