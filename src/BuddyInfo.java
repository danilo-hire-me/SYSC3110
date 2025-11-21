import java.io.Serializable;

public class BuddyInfo implements Serializable {
    private String name;
    private String address;
    private String phone;

    public BuddyInfo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public static BuddyInfo importBuddyInfo(String data) {
        if (data == null) {
            return null;
        }
        String[] parts = data.split("#");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid buddy data: " + data);
        }
        return new BuddyInfo(parts[0], parts[1], parts[2]);
    }


    /**
     * Generates the XML representation of the BuddyInfo object.
     */
    public String toXML() {
        return STR."""
            \t<BuddyInfo>
            \t\t<name>\{name}</name>
            \t\t<address>\{address}</address>
            \t\t<phone>\{phone}</phone>
            \t</BuddyInfo>
            """;
    }

    @Override
    public String toString() {
        return name + "#" + address + "#" + phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BuddyInfo)) return false;
        BuddyInfo other = (BuddyInfo) obj;
        return name.equals(other.name) && address.equals(other.address) && phone.equals(other.phone);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}
