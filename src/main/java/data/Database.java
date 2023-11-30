package data;

import comparator.BestTimeComparator;
import member.CompetitiveSwimmer;
import member.Member;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Database {
    private ArrayList<Member> members;
    BestTimeComparator btc = new BestTimeComparator();

    public Database() {
        this.members = new ArrayList<>();
        LocalDate birthday = LocalDate.of(1987, 12, 3);
        members.add(new CompetitiveSwimmer("Grete Bjerre", "Nyborggade 1tv", birthday, 198712377, "GreteBjerre@gmail.com", true, "Michael", "Crawl",LocalDate.of(2001, 12, 3) , 4, 5,6));
        members.add(new CompetitiveSwimmer("Hello World", "Fubarveh 42", LocalDate.of(2010, 10, 26), 261082126, "hello@gmail.com", true, "Michael", "Butterfly",LocalDate.of(1987, 12, 3),4,5,6));
        members.add(new CompetitiveSwimmer("Grete Bjerre", "Nyborggade 1tv", birthday, 198712377, "GreteBjerre@gmail.com", true, "Michael", "Crawl",LocalDate.of(1987, 12, 3), 1,2,3));
        members.add(new CompetitiveSwimmer("Hello World", "Fubarveh 42", LocalDate.of(2010, 10, 26), 261082126, "hello@gmail.com", true, "Michael", "Butterfly",LocalDate.of(1987, 12, 3),10,11,12));
        members.add(new CompetitiveSwimmer("Grete Bjerre", "Nyborggade 1tv", birthday, 198712377, "GreteBjerre@gmail.com", true, "Michael", "Crawl",LocalDate.of(1987, 12, 3), 7,8,9));
        members.add(new CompetitiveSwimmer("Hello World", "Fubarveh 42", LocalDate.of(2010, 10, 26), 261082126, "hello@gmail.com", true, "Michael", "Butterfly",LocalDate.of(1987, 12, 3),7,8,9));
        members.add(new Member("Hej Bror", "Villa lort 1337", LocalDate.of(1994, 12, 29), 123451234, "fubar@gmail.com", false, true));
    }


    public void registerNewMember(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive) {
        Member member = new Member(name, address, birthday, memberID, email, membershipType, isActive);
        members.add(member);

    }

    public void registerNewCompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, String coach, String discipline, LocalDate dateWhenAchieved, int minutes, int seconds, int milliseconds) {
        members.add(new CompetitiveSwimmer(name, address, birthday, memberID, email,membershipType, coach, discipline, dateWhenAchieved, minutes, seconds, milliseconds));
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

    public ArrayList<Member> getAllMembers() {
        return members;
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

