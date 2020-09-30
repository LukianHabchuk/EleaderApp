package service;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class WriteXMLTest {

    @Test
    public void shouldWriteFile() throws IOException {

        List<String> lines = new ArrayList<>();
        lines.add("<?xml version = \"1.0\"?>" +
                "\n<accounts>" +
                "\n        <account iban=\"PL61109010140000071219812875\">" +
                "\n              <name>correct account</name>" +
                "\n              <currency>PLN</currency>" +
                "\n              <balance>1234.56</balance>" +
                "\n             <closingDate>2029-10-11</closingDate>" +
                "\n           </account>" +
                "\n        <account iban=\"PP61109010140000071219812871\">" +
                "\n              <name>account with wrong iban</name>" +
                "\n              <currency>PLN</currency>" +
                "\n              <balance>1234.56</balance>" +
                "\n             <closingDate>2029-10-11</closingDate>" +
                "\n           </account>" +
                "\n</accounts>");

        WriteXML writeMock = mock(WriteXML.class);
        writeMock.writeFile(lines);

        verify(writeMock, Mockito.times(1)).writeFile(lines);
        verify(writeMock).writeFile(lines);
    }
}
