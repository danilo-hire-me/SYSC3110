public class BuddyInfo {

    public BuddyInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;

    public static void main(String[] args) {
        System.out.println("Hello");
        BuddyInfo buddyInfo = new BuddyInfo("homer");
        System.out.println(buddyInfo.getName());
    }
}
