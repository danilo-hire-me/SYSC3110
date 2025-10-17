import javax.swing.DefaultListModel;

public class AddressBook extends DefaultListModel<BuddyInfo> {

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
}
