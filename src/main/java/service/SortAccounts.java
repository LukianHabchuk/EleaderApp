package service;

import entity.Account;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortAccounts {

    public List<Account> sortByNameAsc(List<Account> accounts) {
        return accounts.stream()
                .sorted(Comparator.comparing(Account::getName))
                .collect(Collectors.toList());
    }

}
