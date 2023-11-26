package member;

import java.time.LocalDate;


public class CompetitiveSwimmer extends Member {
    private String coach;
    private String discipline;

    public CompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean isActive, String coach, String discipline) {
        super(name, address, birthday, memberID, email, true, isActive);
        this.coach = coach;
        this.discipline = discipline;
    }

    public String getCoach() {
        return coach;
    }

    public String getDiscipline() {
        return discipline;
    }



    @Override
    public String toString() {
        return super.toString() +
                "CompetitiveSwimmer{" +
                "coach='" + coach + '\'' +
                ", discipline='" + discipline + '\'' +
                '}';
    }

}


