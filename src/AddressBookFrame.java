import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class AddressBookFrame extends JFrame {
    private AddressBook addressBook;
    private JList<BuddyInfo> buddyList;

    public AddressBookFrame() {
        super("Address Book");
        addressBook = new AddressBook();
        buddyList = new JList<>(addressBook);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        setJMenuBar(createMenuBar());
        add(new JScrollPane(buddyList));

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu addressBookMenu = new JMenu("Address Book");
        JMenuItem newBookItem = new JMenuItem("New");
        newBookItem.addActionListener(e -> addressBook.clearBook());
        addressBookMenu.add(newBookItem);

        // --- Text Format ---
        JMenuItem exportItem = new JMenuItem("Export (Text)");
        exportItem.addActionListener(e -> exportAddressBookText());
        addressBookMenu.add(exportItem);

        JMenuItem importItem = new JMenuItem("Import (Text)");
        importItem.addActionListener(e -> importAddressBookText());
        addressBookMenu.add(importItem);

        // --- Serialized Format ---
        JMenuItem exportSerItem = new JMenuItem("Export (Serialized)");
        exportSerItem.addActionListener(e -> exportAddressBookSerialized());
        addressBookMenu.add(exportSerItem);

        JMenuItem importSerItem = new JMenuItem("Import (Serialized)");
        importSerItem.addActionListener(e -> importAddressBookSerialized());
        addressBookMenu.add(importSerItem);

        // --- XML Format (New Addition) ---
        JMenuItem exportXMLItem = new JMenuItem("Export (XML)");
        exportXMLItem.addActionListener(e -> exportAddressBookXML());
        addressBookMenu.add(exportXMLItem);

        JMenuItem importXMLItem = new JMenuItem("Import (XML)");
        importXMLItem.addActionListener(e -> importAddressBookXML());
        addressBookMenu.add(importXMLItem);

        JMenu buddyMenu = new JMenu("Buddy");
        JMenuItem addBuddyItem = new JMenuItem("Add Buddy");
        addBuddyItem.addActionListener(e -> addBuddy());
        JMenuItem removeBuddyItem = new JMenuItem("Remove Buddy");
        removeBuddyItem.addActionListener(e -> removeBuddy());
        buddyMenu.add(addBuddyItem);
        buddyMenu.add(removeBuddyItem);

        menuBar.add(addressBookMenu);
        menuBar.add(buddyMenu);

        return menuBar;
    }

    private void addBuddy() {
        String name = JOptionPane.showInputDialog(this, "Enter Buddy Name:");
        String address = JOptionPane.showInputDialog(this, "Enter Buddy Address:");
        String phone = JOptionPane.showInputDialog(this, "Enter Buddy Phone:");

        if (name != null && address != null && phone != null) {
            addressBook.addBuddy(new BuddyInfo(name, address, phone));
        }
    }

    private void removeBuddy() {
        BuddyInfo selected = buddyList.getSelectedValue();
        if (selected != null) {
            addressBook.removeBuddy(selected);
        } else {
            JOptionPane.showMessageDialog(this, "Select a buddy to remove.");
        }
    }

    private void exportAddressBookText() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name to export (text):");
        if (fileName != null && !fileName.isEmpty()) {
            try {
                addressBook.save(fileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting: " + ex.getMessage());
            }
        }
    }

    private void importAddressBookText() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name to import (text):");
        if (fileName != null && !fileName.isEmpty()) {
            try {
                AddressBook imported = AddressBook.importAddressBook(fileName);
                addressBook = imported;
                buddyList.setModel(addressBook);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File not found: " + fileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error importing: " + ex.getMessage());
            }
        }
    }

    private void exportAddressBookSerialized() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name to export (serialized):");
        if (fileName != null && !fileName.isEmpty()) {
            try {
                addressBook.saveSerialized(fileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting (serialized): " + ex.getMessage());
            }
        }
    }

    private void importAddressBookSerialized() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name to import (serialized):");
        if (fileName != null && !fileName.isEmpty()) {
            try {
                AddressBook imported = AddressBook.loadSerialized(fileName);
                addressBook = imported;
                buddyList.setModel(addressBook);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File not found: " + fileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error importing (serialized): " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Class not found while importing: " + ex.getMessage());
            }
        }
    }

    private void exportAddressBookXML() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name to export (XML):");
        if (fileName != null && !fileName.isEmpty()) {
            try {
                addressBook.exportToXmlFile(fileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting (XML): " + ex.getMessage());
            }
        }
    }

    private void importAddressBookXML() {
        String fileName = JOptionPane.showInputDialog(this, "Enter file name to import (XML):");
        if (fileName != null && !fileName.isEmpty()) {
            // Note: Ensure AddressBook.importFromXmlFile is implemented as per the previous step.
            // If the file is invalid or the format is wrong, the SAX parser might print errors to the console.
            AddressBook imported = AddressBook.importFromXmlFile(fileName);
            if (imported != null) {
                addressBook = imported;
                buddyList.setModel(addressBook);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddressBookFrame::new);
    }
}