import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class AddressBookIOTest {

    @Test
    public void testTextExportImport() throws IOException {
        AddressBook book1 = new AddressBook();
        book1.addBuddy(new BuddyInfo("Mr. Buddy", "111 Fake Street", "613-555-5555"));
        book1.addBuddy(new BuddyInfo("Ms. Friend", "222 Real Road", "613-555-1234"));

        String fileName = "testAddressBook.txt";
        book1.save(fileName);

        AddressBook book2 = AddressBook.importAddressBook(fileName);

        assertEquals(book1.getSize(), book2.getSize());
        for (int i = 0; i < book1.getSize(); i++) {
            assertEquals(book1.getElementAt(i), book2.getElementAt(i));
        }
    }

    @Test
    public void testSerializedExportImport() throws Exception {
        AddressBook book1 = new AddressBook();
        book1.addBuddy(new BuddyInfo("Mr. Buddy", "111 Fake Street", "613-555-5555"));
        book1.addBuddy(new BuddyInfo("Ms. Friend", "222 Real Road", "613-555-1234"));

        String fileName = "testAddressBook.ser";
        book1.saveSerialized(fileName);

        AddressBook book2 = AddressBook.loadSerialized(fileName);

        assertEquals(book1.getSize(), book2.getSize());
        for (int i = 0; i < book1.getSize(); i++) {
            assertEquals(book1.getElementAt(i), book2.getElementAt(i));
        }
    }
    @Test
    public void testXMLExportImport() throws IOException {
        AddressBook book1 = new AddressBook();
        book1.addBuddy(new BuddyInfo("Alice XML", "123 XML Lane", "555-1111"));
        book1.addBuddy(new BuddyInfo("Bob XML", "456 XML Drive", "555-2222"));

        String fileName = "testAddressBook.xml";
        book1.exportToXmlFile(fileName);

        File f = new File(fileName);
        assertTrue(f.exists());

        AddressBook book2 = AddressBook.importFromXmlFile(fileName);

        assertEquals(book1.getSize(), book2.getSize());
        for (int i = 0; i < book1.getSize(); i++) {
            assertEquals(book1.getElementAt(i), book2.getElementAt(i));
        }
    }
}
