package validator;

import entity.Account;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountValidatorTest {

    private Account correctAccount;

    @Before
    public void initiation() throws ParseException {
        correctAccount = new Account(
                "PL61109010140000071219812875", "correct account", "PLN",
                BigDecimal.valueOf(12345.67),
                new SimpleDateFormat("yyyy-MM-dd").parse("2029-10-11"));
    }

    @Test
    public void isCurrencyCorrectShouldReturnTrue() {
        Assert.assertTrue(AccountValidator.isCurrencyCorrect(correctAccount));
    }

    @Test
    public void isCurrencyCorrectShouldReturnFalse() {
        Account wrongCurrencyAccount = correctAccount;
        wrongCurrencyAccount.setCurrency("wrong");
        Assert.assertFalse(AccountValidator.isCurrencyCorrect(wrongCurrencyAccount));
    }

    @Test
    public void isBalanceCorrectShouldReturnTrue() {
        Assert.assertTrue(AccountValidator.isBalanceCorrect(correctAccount));
    }

    @Test
    public void isBalanceCorrectShouldReturnFalse() {
        Account wrongBalanceAccount = correctAccount;
        wrongBalanceAccount.setBalance(BigDecimal.valueOf(-12345.67));
        Assert.assertFalse(AccountValidator.isBalanceCorrect(wrongBalanceAccount));
    }

    @Test
    public void isClosingDateCorrectShouldReturnTrue() {
        Assert.assertTrue(AccountValidator.isClosingDateCorrect(correctAccount));
    }

    @Test
    public void isClosingDateCorrectShouldReturnFalse() throws ParseException {
        Account wrongDateAccount = correctAccount;
        wrongDateAccount.setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-11"));
        Assert.assertFalse(AccountValidator.isClosingDateCorrect(wrongDateAccount));
    }

    @Test
    public void isIbanCorrectShouldReturnTrue() {
        Assert.assertTrue(AccountValidator.isIbanCorrect(correctAccount.getIban()));
    }

    @Test
    public void isIbanCorrectShouldReturnFalse() {
        Account wrongIbanAccount = correctAccount;
        wrongIbanAccount.setIban("PP61109010140000071219812875");
        Assert.assertFalse(AccountValidator.isIbanCorrect(wrongIbanAccount.getIban()));
    }

    @Test
    public void isIbanCorrectHasIbanDigitsShouldReturnFalse() {
        Account wrongIbanAccount = correctAccount;
        wrongIbanAccount.setIban("PLP1109010140000071219812875");
        Assert.assertFalse(AccountValidator.isIbanCorrect(wrongIbanAccount.getIban()));
    }

    @Test
    public void isIbanCorrectHasIbanRightLengthShouldReturnFalse() {
        Account wrongIbanAccount = correctAccount;
        wrongIbanAccount.setIban("PL123456789123456789");
        Assert.assertFalse(AccountValidator.isIbanCorrect(wrongIbanAccount.getIban()));
    }
}
