package member;

import java.time.LocalDate;


public class CompetitiveSwimmer extends Member {
    private String coach;
    private String discipline;
    private boolean division;

    public CompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive, String coach, String discipline, boolean division) {
        super(name, address, birthday, memberID, email, membershipType, isActive);
        this.coach = coach;
        this.discipline = discipline;
        this.division = division;
    }

    public String getCoach() {
        return coach;
    }

    public String getDiscipline() {
        return discipline;
    }

    public boolean getDivision() {
        return division;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CompetitiveSwimmer{" +
                "coach='" + coach + '\'' +
                ", discipline='" + discipline + '\'' +
                ", division=" + (division ? "Junior" : "Senior")+
                '}';
    }

}


