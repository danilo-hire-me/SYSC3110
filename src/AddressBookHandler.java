import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AddressBookHandler extends DefaultHandler {
    private AddressBook addressBook;
    private String tempName;
    private String tempAddress;
    private String tempPhone;
    private StringBuilder data; // To hold characters between tags

    public AddressBookHandler(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.data = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Reset the data buffer when a new element starts
        data.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("name")) {
            tempName = data.toString().trim();
        } else if (qName.equalsIgnoreCase("address")) {
            tempAddress = data.toString().trim();
        } else if (qName.equalsIgnoreCase("phone")) {
            tempPhone = data.toString().trim();
        } else if (qName.equalsIgnoreCase("BuddyInfo")) {
            // We reached the end of a BuddyInfo tag, create the object and add it
            BuddyInfo buddy = new BuddyInfo(tempName, tempAddress, tempPhone);
            addressBook.addBuddy(buddy);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        // Accumulate characters into the buffer
        data.append(new String(ch, start, length));
    }
}