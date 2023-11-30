package data;

import comparator.BestTimeComparator;
import member.CompetitiveSwimmer;
import member.Member;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Database {
    private Filehandler file = new Filehandler();
    private ArrayList<Member> members;
    BestTimeComparator btc = new BestTimeComparator();

    public Database() {
        this.members = new ArrayList<>();
        LocalDate birthday = LocalDate.of(1987, 12, 3);
        members.add(new CompetitiveSwimmer("Grete Bjerre", "Nyborggade 1tv", LocalDate.of(1987, 12, 3), 198712377, "GreteBjerre@gmail.com", true, "Michael", "Crawl", "Meet1", "1st", LocalDate.of(2023, 11, 24), 4, 5, 6));
        members.add(new CompetitiveSwimmer("Hello World", "Fubarveh 42", LocalDate.of(2010, 10, 26), 2010102626, "hello@gmail.com", true, "Michael", "Butterfly", "Meet2", "2nd", LocalDate.of(2023, 11, 24), 4, 5, 6));
        members.add(new CompetitiveSwimmer("John Doe", "Street 123", LocalDate.of(1935, 5, 15), 193551534, "john.doe@email.com", true, "Trainer1", "Backstroke", "Meet3", "3rd", LocalDate.of(2023, 11, 25), 3, 55, 78));
        members.add(new CompetitiveSwimmer("Alice Smith", "Avenue 456", LocalDate.of(2000, 8, 20), 200082021, "alice.smith@email.com", true, "Trainer2", "Breaststroke", "Meet4", "2nd", LocalDate.of(2023, 11, 25), 2, 30, 45));
        members.add(new CompetitiveSwimmer("Bob Johnson", "Lane 789", LocalDate.of(1998, 3, 10), 199831033, "bob.johnson@email.com", true, "Trainer1", "Butterfly", "Meet3", "5th", LocalDate.of(2023, 11, 25), 5, 10, 22));
        members.add(new CompetitiveSwimmer("Emma White", "Road 101", LocalDate.of(2002, 6, 5), 20026577, "emma.white@email.com", true, "Trainer2", "Freestyle", "Meet4", "1st", LocalDate.of(2023, 11, 25), 1, 58, 33));
        members.add(new CompetitiveSwimmer("Charlie Brown", "Square 246", LocalDate.of(1997, 9, 8), 19979899, "charlie.brown@email.com", true, "Trainer1", "Backstroke", "Meet3", "4th", LocalDate.of(2023, 11, 25), 4, 20, 15));
        members.add(new Member("Frederik Jensen", "Borgergade 13", LocalDate.of(1994, 12, 29), 1994122934, "fubar@gmail.com", false, true));
    }


    public void registerNewMember(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive) {
        Member member = new Member(name, address, birthday, memberID, email, membershipType, isActive);
        members.add(member);

    }

    public void registerNewCompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, String coach, String discipline, String meet, String placement, LocalDate dateWhenAchieved, int minutes, int seconds, int milliseconds) {
        members.add(new CompetitiveSwimmer(name, address, birthday, memberID, email,membershipType, coach, discipline, meet, placement, dateWhenAchieved, minutes, seconds, milliseconds));
    }

    public void editMember(Member memberToEdit) {
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.isActive() && member.equals(memberToEdit)) {
                members.set(i, memberToEdit);
                break;
            }
        }
    }

    public void load() throws IOException {
        file.load();
        members.addAll(file.getMemberFiles());
    }
    public void save(){
        file.save(members);
    }

    public ArrayList<Member> getAllMembers() {
        return members;
    }
    public ArrayList<CompetitiveSwimmer> getAllCompetitiveSwimmers() {
        ArrayList<CompetitiveSwimmer> competitiveSwimmers = new ArrayList<>();
        for (Member member : members) {
            if (member instanceof CompetitiveSwimmer) {
                competitiveSwimmers.add((CompetitiveSwimmer) member);
            }
        }
        return competitiveSwimmers;
    }

    public ArrayList<CompetitiveSwimmer> getJuniorTeam() {
        ArrayList<CompetitiveSwimmer> juniorTeam = new ArrayList<>();
        for (Member member : members) {
            if (member.isCompetitiveSwimmer() && member.isJunior()) {
                juniorTeam.add((CompetitiveSwimmer) member);
            }
        }
        return juniorTeam;
    }

    public ArrayList<CompetitiveSwimmer> getSeniorTeam() {
        ArrayList<CompetitiveSwimmer> seniorTeam = new ArrayList<>();
        for (Member member : members) {
            if (member.isCompetitiveSwimmer() && member.isSenior()) {
                seniorTeam.add((CompetitiveSwimmer) member);
            }
        }
        return seniorTeam;
    }

    public int getAge(Member member) {
        if (member.getBirthday() == null) {
            return 0;
        }
        LocalDate localDate = LocalDate.now();
        return Period.between(member.getBirthday(), localDate).getYears();
    }


    public int IDCreation() {
        Random random = new Random();
        int r = random.nextInt(100);
        for (Member member : members) {
            if (member.getBirthday() != null) {
                LocalDate birthday = member.getBirthday();
                int dateAsInt = birthday.getYear() * 10000 + birthday.getMonthValue() * 100 + birthday.getDayOfMonth() + r;
                System.out.println(dateAsInt);
            }
        }
        return 0;
    }
    public void removeMemberFromList(String name){
        ArrayList<Member> memberToRemove = new ArrayList<>();
        for (Member memberInDatabase : members) {
            if (memberInDatabase.getName().trim().equalsIgnoreCase(name)) {
                memberToRemove.add(memberInDatabase);
            }
        }
        members.removeAll(memberToRemove);
    }
    public void printSortedCompetitiveSwimmers() {
        List<CompetitiveSwimmer> sortedSwimmers = getSortedCompetitiveSwimmers();
        for (CompetitiveSwimmer swimmer : sortedSwimmers) {
            System.out.println(swimmer);
        }
    }

    private List<CompetitiveSwimmer> getSortedCompetitiveSwimmers() {
        return members.stream()
                .filter(member -> member instanceof CompetitiveSwimmer)
                .map(member -> (CompetitiveSwimmer) member)
                .sorted(new BestTimeComparator())
                .collect(Collectors.toList());
    }

}

