package member;
import data.Database;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerMember {
    private Database db;

    public ControllerMember (){
        this.db = new Database();

    }

    public void registerMember(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive) {
        Member newMember = new CompetitiveSwimmer("Grete Bjerre", "Nyborggade 1tv", birthday, 192302, "GreteBjerre@gmail.com", true, true, "Michael", "Crawl", true);
        db.registerNewMember(name, address, birthday, memberID, email, membershipType, isActive);
    }

    public void editMember(Member memberToEdit){
        db.editMember(memberToEdit);
    }
    public ArrayList<Member> getAllMembers(){
        return db.getAllMembers();
    }
    public int getAge(){
        return db.getAge();
    }
    public int IDCreation(){
        return db.IDCreation();
    }
}
