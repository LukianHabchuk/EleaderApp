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
public class FilterAccountsTest {

    private List<Account> accountList;
    private final String DATA_FORMAT = "yyyy-MM-dd";
    private final String CORRECT_CURRENCY = "PLN";
    private final String WRONG_CURRENCY = "WRONG";

    @Before
    public void initiateAccountList() throws ParseException {
        accountList = new ArrayList<>();
        accountList.add(new Account(
                "PL61109010140000071219812875", "correct account", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PP61109010140000071219812871", "account with wrong iban", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL123456789123456789", "account with wrong iban length", CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812870", "account with wrong currency", WRONG_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812870", "account with negative balance", CORRECT_CURRENCY,
                BigDecimal.valueOf(-12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2029-10-11")));
        accountList.add(new Account(
                "PL61109010140000071219812870","account with expired date",CORRECT_CURRENCY,
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat(DATA_FORMAT).parse("2019-10-11")));
    }

    @Test
    public void shouldFilterWrongData() {
        List<Account> accounts = accountList;

        FilterAccounts filterAccounts = new FilterAccounts();
        List<Account> accountsResult = filterAccounts.filterData(accounts);
        Assert.assertEquals(1, accountsResult.size());
        Assert.assertEquals("PL61109010140000071219812875", accountsResult.get(0).getIban());
    }


}
