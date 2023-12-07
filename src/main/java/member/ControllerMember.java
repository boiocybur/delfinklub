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

    public void registerMember(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive, int arrears) {
        db.registerNewMember(name, address, birthday, memberID, email, membershipType, isActive, arrears);
    }
    public void registerNewCompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive, int arrears, String coach, String discipline, String meet, String placement, LocalDate dateWhenAchieved, int minutes, int seconds, int milliseconds) {
        db.registerNewCompetitiveSwimmer(name, address, birthday, memberID, email, membershipType, isActive, arrears, coach, discipline, meet, placement, dateWhenAchieved, minutes, seconds, milliseconds);
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

    public void loadMembers() throws IOException {
        db.loadMembers();
    }
    public void saveMembers(){
        db.saveMembers();
    }
    public void saveCompetitiveSwimmers(){
        db.saveCompetitiveSwimmers();
    }

    public void loadCompetitiveSwimmers() throws IOException{
        db.loadCompetitiveSwimmers();
    }

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
    public void topFive(){
        db.printTopFiveSwimmersByDiscipline();
    }
    public void topFiveSenior(){
        db.printTopFiveSeniorSwimmersByDiscipline();
    }
}
