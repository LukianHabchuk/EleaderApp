package service;

import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class ReadXMLTest {

    @Test
    public void shouldReadDefaultFile() {
        ReadXML readXML = new ReadXML();
        List<String> lines = readXML.readFile();

        Assert.assertEquals(58, lines.size());
        Assert.assertEquals("<?xml version = \"1.0\"?>", lines.get(0).trim());
        Assert.assertEquals("<account iban=\"PL61109010140000071219812874\">", lines.get(37).trim());
        Assert.assertEquals("</accounts>", lines.get(57).trim());
    }

}
