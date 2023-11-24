package member;
import data.Database;
import member.Member;

import java.util.ArrayList;

public class ControllerMember {
    private Database db;

    public ControllerMember (){
        this.db = db;
    }

    public void registerMember(String name, String address, int age, int memberID, String email, boolean membershipType, boolean isActive){
        db.registerNewMember(name, address, age, memberID, email, membershipType, isActive);
    }

    public void editMember(Member memberToEdit){
        db.editMember(memberToEdit);
    }
    public ArrayList<Member> getAllMembers(){
        return db.getAllMembers();
    }

}
