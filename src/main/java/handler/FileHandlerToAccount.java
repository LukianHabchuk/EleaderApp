package handler;

import entity.Account;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static constants.AccountTags.*;

public class FileHandlerToAccount {

    public List<Account> listToAccount(List<String> lines) {
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();

        for(int i = 0; i< lines.size(); i++) {

            if (lines.get(i).contains(IBAN)) {
                account.setIban(getIbanFromLine(lines.get(i)));

                account.setName(getStringData(lines.get(i+1)));

                account.setCurrency(getStringData(lines.get(i+2)));

                account.setBalance(getBalanceFromLine(lines.get(i+3)));

                try {
                    account.setClosingDate(getDateFromLine(lines.get(i+4)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (lines.get(i).contains("</account>")) {
                accounts.add(account);
                account = new Account();
            }
        }

        return accounts;
    }

    private String getStringData(String s) {
        return s.substring(startIndex(s), endIndex(s));
    }

    private String getIbanFromLine(String s) {
        return s.substring(startIbanIndex(s), endIbanIndex(s));
    }

    private BigDecimal getBalanceFromLine(String s) {
        return new BigDecimal(getStringData(s));
    }

    private Date getDateFromLine(String s) throws ParseException {
        String dataFormat = "yyyy-MM-dd";
        DateFormat format = new SimpleDateFormat(dataFormat);
        return format.parse(getStringData(s));
    }

    private int startIndex(String line) {
        return line.indexOf(">") + 1;
    }

    private int endIndex(String line) {
        return line.lastIndexOf("<");
    }

    private int startIbanIndex(String line) {
        return line.indexOf("\"") + 1;
    }

    private int endIbanIndex(String line) {
        return line.lastIndexOf("\"");
    }

}
