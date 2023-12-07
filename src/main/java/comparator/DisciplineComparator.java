package comparator;

import member.CompetitiveSwimmer;
import member.Member;

import java.util.Comparator;

public class DisciplineComparator implements Comparator<CompetitiveSwimmer> {
    @Override
    public int compare(CompetitiveSwimmer o1, CompetitiveSwimmer o2) {
        return o1.getDiscipline().compareTo(o2.getDiscipline());
    }
}