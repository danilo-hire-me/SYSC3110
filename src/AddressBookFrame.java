import javax.swing.*;
import java.awt.event.*;

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

        // AddressBook Menu
        JMenu addressBookMenu = new JMenu("Address Book");
        JMenuItem newBookItem = new JMenuItem("New");
        newBookItem.addActionListener(e -> addressBook.clearBook());
        addressBookMenu.add(newBookItem);

        // Buddy Menu
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
        String name = JOptionPane.showInputDialog("Enter Buddy Name:");
        String address = JOptionPane.showInputDialog("Enter Buddy Address:");
        String phone = JOptionPane.showInputDialog("Enter Buddy Phone:");

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddressBookFrame::new);
    }
}
