package service;

import entity.Account;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class SortAccountsTest {

    private final String DATA_FORMAT = "yyyy-MM-dd";
    private final String CORRECT_CURRENCY = "PLN";

    private List<Account> accountList;

    @Before
    public void initiateAccountList() throws ParseException {
        accountList = new ArrayList<>();
        accountList.add(new Account(
                "PL61109010140000071219812873", "correct account3", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812872", "correct account2", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812871", "correct account1", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812875", "correct account5", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812876", "correct account6", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812874", "correct account4", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
    }

    @Test
    public void sortByNameAsc() {

        List<Account> accounts = accountList;

        SortAccounts sortAccounts = new SortAccounts();
        List<Account> accountsResult= sortAccounts.sortByNameAsc(accounts);

        Assert.assertEquals(6, accountsResult.size());
        Assert.assertEquals("correct account1", accountsResult.get(0).getName());
        Assert.assertEquals("correct account2", accountsResult.get(1).getName());
        Assert.assertEquals("correct account3", accountsResult.get(2).getName());
        Assert.assertEquals("correct account4", accountsResult.get(3).getName());
        Assert.assertEquals("correct account5", accountsResult.get(4).getName());
        Assert.assertEquals("correct account6", accountsResult.get(5).getName());

    }
}
