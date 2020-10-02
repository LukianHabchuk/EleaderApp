package handler;

import entity.Account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandlerFromAccount {

    public List<String> listFromAccount(List<Account> accounts) {
        List<String> lines = new ArrayList<>();
        lines.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "\n"
                + "<accounts>");

        accounts
                .forEach(a -> lines.add(getIbanLine(a)
                        + "\n"
                        + getNameLine(a)
                        + "\n"
                        + getCurrencyLine(a)
                        + "\n"
                        + getBalanceLine(a)
                        + "\n"
                        + getClosingDateLine(a)
                        + "\n"
                        + "        </account>"));

        lines.add("</accounts>");
        return lines;
    }

    private String getIbanLine(Account a) {
        return "        <account iban=\"" + a.getIban() + "\">";
    }

    private String getNameLine(Account a) {
        return "                <name>" + a.getName() + "</name>";
    }

    private String getCurrencyLine(Account a) {
        return "                <currency>" + a.getCurrency() + "</currency>";
    }

    private String getBalanceLine(Account a) {
        return "                <balance>" + a.getBalance() + "</balance>";
    }

    private String getClosingDateLine(Account a) {
        return "                <closingDate>"
                + formatDateToString(a.getClosingDate())
                + "</closingDate>";
    }

    private String formatDateToString(Date date) {
        String dataFormat = "yyyy-MM-dd";
        return new SimpleDateFormat(dataFormat).format(date);
    }
}
