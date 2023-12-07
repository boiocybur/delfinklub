package data;

import comparator.BestTimeComparator;
import comparator.DisciplineComparator;
import member.CompetitiveSwimmer;
import member.Member;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Database {
    private Filehandler file = new Filehandler();
    private ArrayList<Member> members;
    private ArrayList<CompetitiveSwimmer> competitiveSwimmers;
    BestTimeComparator btc = new BestTimeComparator();

    public Database() {
        this.members = new ArrayList<>();
        this.competitiveSwimmers = new ArrayList<>();
        }


    public void registerNewMember(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive, int arrears) {
        Member member = new Member(name, address, birthday, memberID, email, membershipType, isActive, arrears);
        if (!(member instanceof CompetitiveSwimmer)) {
            members.add(member);
        }
    }

    public void registerNewCompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive, int restance, String coach, String discipline, String meet, String placement, LocalDate dateWhenAchieved, int minutes, int seconds, int milliseconds) {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(name, address, birthday, memberID, email, membershipType, isActive, restance, coach, discipline, meet, placement, dateWhenAchieved, minutes, seconds, milliseconds);
        members.add(swimmer);
        competitiveSwimmers.add(swimmer);
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

    public void loadMembers() throws IOException {
        ArrayList<Member> loadedMembers = file.loadMembers();
        members.addAll(loadedMembers);
    }

    public void loadCompetitiveSwimmers() throws IOException {
        competitiveSwimmers = file.loadCompetitiveSwimmers();
        // members.addAll(file.getCompetitiveFiles());
        members.addAll(competitiveSwimmers);
    }

    public void saveMembers() {
        file.saveMembers(members);
    }
    public void saveCompetitiveMembers(){
        file.saveCompetitiveSwimmers(competitiveSwimmers);
    }


    public ArrayList<Member> getAllMembers() {
        return members;
    }

    public ArrayList<CompetitiveSwimmer> getAllCompetitiveSwimmers() {
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

    public int getAge() {
        for (Member member : members) {

        }
        return 0;
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

    public void removeMemberFromList(String name) {
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

    public void printTopFiveJuniorSwimmersByDiscipline() {

        List<CompetitiveSwimmer> swimmers = getAllCompetitiveSwimmers();
        swimmers.sort(Comparator.comparing(CompetitiveSwimmer::getBestTime));

        String[] disciplines = {"Crawl", "Butterfly", "Rygcrawl", "Bryst"};
        Collections.sort(swimmers, new DisciplineComparator());

        for (String discipline : disciplines) {
            System.out.println("Discipline: " + discipline);
            int count = 0;
            for (CompetitiveSwimmer swimmer : swimmers) {
                if (swimmer.getDiscipline().equals(discipline)) {
                    if (swimmer.isJunior()) {
                        if (count < 5) {
                            System.out.println(swimmer.getName() + " " + swimmer.getMemberID() + " " + swimmer.getDateWhenAchieved() + " - Time: " + swimmer.formatBestTime());
                            count++;
                        } else {
                            break;
                        }
                    }
                }
            }
            System.out.println();
        }
    }
    public void printTopFiveSeniorSwimmersByDiscipline() {

        List<CompetitiveSwimmer> swimmers = getAllCompetitiveSwimmers();
        swimmers.sort(Comparator.comparing(CompetitiveSwimmer::getBestTime));

        String[] disciplines = {"Crawl", "Butterfly", "Rygcrawl", "Bryst"};
        Collections.sort(swimmers, new DisciplineComparator());

        for (String discipline : disciplines) {
            System.out.println("Discipline: " + discipline);
            int count = 0;
            for (CompetitiveSwimmer swimmer : swimmers) {
                if (swimmer.getDiscipline().equals(discipline)) {
                    if (swimmer.isSenior()) {
                        if (count < 5) {
                            System.out.println(swimmer.getName() + " " + swimmer.getMemberID() + " " + swimmer.getDateWhenAchieved() + " - Time: " + swimmer.formatBestTime());
                            count++;
                        } else {
                            break;
                        }
                    }
                }

            }
            System.out.println();
        }
    }
}