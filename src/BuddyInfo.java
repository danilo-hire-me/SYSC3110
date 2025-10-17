public class BuddyInfo {
    private String name;
    private String address;
    private String phone;

    public BuddyInfo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return name + " - " + address + " (" + phone + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BuddyInfo)) return false;
        BuddyInfo other = (BuddyInfo) obj;
        return name.equals(other.name) && address.equals(other.address) && phone.equals(other.phone);
    }
}
