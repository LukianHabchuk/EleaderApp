package validator;

import entity.Account;

import java.math.BigDecimal;
import java.util.Date;

public final class AccountValidator {
    private AccountValidator() { }

    public static boolean isCurrencyCorrect(Account a) {
        return a.getCurrency().equals("PLN");
    }

    public static boolean isBalanceCorrect(Account a) {
        return (a.getBalance().compareTo(BigDecimal.ZERO) >= 0);
    }

    public static boolean isClosingDateCorrect(Account a) {
        return a.getClosingDate().after(new Date());
    }

    public static boolean isIbanCorrect(String iban) {
        return (isIbanPL(iban)
                && hasIbanDigits(iban)
                && hasIbanRightLength(iban));
    }

    private static boolean isIbanPL(String iban) {
        return iban.substring(0, 2).equals("PL");
    }

    private static boolean hasIbanDigits(String iban) {
        return iban.substring(2).chars().allMatch(Character::isDigit);
    }

    private static boolean hasIbanRightLength(String iban) {
        int ibanLength = 28;
        return iban.length() == ibanLength;
    }

}
