import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.xml.sax.SAXException;
import ru.los.parser.Transfer;

import java.io.*;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class TransferTest {
	@Test
	public void testTransfer() throws IOException, SAXException {

		Transfer.serializeToXml(Transfer.deserializeFromXML("testempl.xml"), "testdepartments.xml");
		FileInputStream fis1 = new FileInputStream("testdepartments.xml");
		File file = new File("src/test/resources/testdeptarget.xml");
		FileInputStream fis2 = new FileInputStream(file.getAbsolutePath());
		BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
		BufferedReader target = new BufferedReader(new InputStreamReader(fis2));
		XMLUnit.setIgnoreWhitespace(true);

		assertXMLEqual(source, target);
	}

}