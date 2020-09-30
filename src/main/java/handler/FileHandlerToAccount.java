package handler;

import entity.Account;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandlerToAccount {

    public List<Account> listToAccount(List<String> lines) {
        List<Account> accounts = new ArrayList<>();

        for(int i = 0; i< lines.size(); i++) {
            if (lines.get(i).contains("<account") && lines.get(i+5).contains("</account>")) {
                try {
                    accounts.add(new Account(
                            getIbanFromLine(lines.get(i)),
                            getStringData(lines.get(i+1)),
                            getStringData(lines.get(i+2)),
                            getBalanceFromLine(lines.get(i+3)),
                            getDateFromLine(lines.get(i+4))));
                    i+=5;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
        String DATA_FORMAT = "yyyy-MM-dd";
        DateFormat format = new SimpleDateFormat(DATA_FORMAT);
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
