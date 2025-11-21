import javax.swing.DefaultListModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class AddressBook extends DefaultListModel<BuddyInfo> implements Serializable {

    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            this.addElement(buddy);
        }
    }

    public void removeBuddy(BuddyInfo buddy) {
        this.removeElement(buddy);
    }

    public void clearBook() {
        this.clear();
    }
    public void exportToXmlFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("<AddressBook>\n");
            for (int i = 0; i < getSize(); i++) {
                writer.write(getElementAt(i).toXML());
            }
            writer.write("</AddressBook>");
        }
    }

    public static AddressBook importFromXmlFile(String fileName) {
        AddressBook book = new AddressBook();
        try {
            File inputFile = new File(fileName);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Create an instance of the separate handler class
            AddressBookHandler handler = new AddressBookHandler(book);

            // Parse the file using that handler
            saxParser.parse(inputFile, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public void save(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < getSize(); i++) {
                writer.write(getElementAt(i).toString());
                writer.newLine();
            }
        }
    }

    public static AddressBook importAddressBook(String fileName) throws IOException {
        AddressBook book = new AddressBook();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    BuddyInfo buddy = BuddyInfo.importBuddyInfo(line);
                    book.addBuddy(buddy);
                }
            }
        }
        return book;
    }

    public void saveSerialized(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    public static AddressBook loadSerialized(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = in.readObject();
            if (obj instanceof AddressBook) {
                return (AddressBook) obj;
            }
            throw new IOException("Invalid data in file: " + fileName);
        }
    }
}
