import entity.Account;
import handler.FileHandlerFromAccount;
import handler.FileHandlerToAccount;
import service.FilterAccounts;
import service.ReadXML;
import service.SortAccounts;
import service.WriteXML;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {

        ReadXML readXML = new ReadXML();
        List<String> lines = readXML.readFile();

        FileHandlerToAccount fileHandlerToAccount = new FileHandlerToAccount();
        List<Account> accountsMapped = fileHandlerToAccount.listToAccount(lines);

        FilterAccounts filterAccounts = new FilterAccounts();
        List<Account> accountsFiltered = filterAccounts.filterData(accountsMapped);

        SortAccounts sortAccounts = new SortAccounts();
        List<Account> accountsToSave = sortAccounts.sortByNameAsc(accountsFiltered);

        FileHandlerFromAccount fileHandlerFromAccount = new FileHandlerFromAccount();
        List<String> linesToSave = fileHandlerFromAccount.listFromAccount(accountsToSave);

        WriteXML writeXML = new WriteXML();
        writeXML.writeFile(linesToSave);

        Logger.getAnonymousLogger().log(Level.INFO, "total accounts number: "+accountsMapped.size());
        Logger.getAnonymousLogger().log(Level.INFO, "correct accounts number: "+accountsFiltered.size());
    }
}
