package member;
import data.Database;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerMember {
    private Database db;

    public ControllerMember() {
        this.db = new Database();

    }

    public void registerMember(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive) {
        db.registerNewMember(name, address, birthday, memberID, email, membershipType, isActive);
    }
    public void registerNewCompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, String coach, String discipline, String meet, String placement, LocalDate dateWhenAchieved, int minutes, int seconds, int milliseconds) {
        db.registerNewCompetitiveSwimmer(name, address, birthday, memberID, email, membershipType, coach, discipline, meet, placement, dateWhenAchieved, minutes, seconds, milliseconds);
    }
    public ArrayList<CompetitiveSwimmer> juniorTeam() {
        return db.getJuniorTeam();
    }

    public ArrayList<CompetitiveSwimmer> seniorTeam() {
        return db.getSeniorTeam();
    }

    public void editMember(Member memberToEdit) {
        db.editMember(memberToEdit);
    }

    public ArrayList<Member> getAllMembers() {
        return db.getAllMembers();
    }

    public ArrayList<CompetitiveSwimmer> getAllCompetitiveSwimmers() {
        return db.getAllCompetitiveSwimmers();
    }

    public void load() throws IOException {
        db.load();
    }
    public void save(){
        db.save();
    }

//    public int getAge(Member member) {
//        return db.getAge(member);
//    }

    public int IDCreation() {
        return db.IDCreation();
    }

    public void removeMember(String name) {
        db.removeMemberFromList(name);
    }
    public void sortBySpeed(){
        db.printSortedCompetitiveSwimmers();
    }

    public void editCompetitiveSwimmer(CompetitiveSwimmer swimmerToEdit) {
    }
}
