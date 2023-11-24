package member;

public class Member {
    private String name;
    private String address;
    private int age;
    private int memberID;
    private String email;
    private boolean membershipType;
    private boolean isActive;

    public Member(String name, String address, int age, int memberID, String email, boolean membershipType, boolean isActive){
        this.name = name;
        this.address = address;
        this.age = age;
        this.memberID = memberID;
        this.email = email;
        this.membershipType = membershipType;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMembershipType() {
        return membershipType;
    }

    public void setMembershipType(boolean membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", adresse='" + address + '\'' +
                ", alder=" + age +
                ", medlemsnummer=" + memberID +
                ", email='" + email + '\'' +
                ", medlemstype=" + membershipType +
                '}';
    }
}
