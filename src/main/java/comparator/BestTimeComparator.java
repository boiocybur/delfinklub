package comparator;

import member.CompetitiveSwimmer;
import member.Member;

import java.util.Comparator;

public class BestTimeComparator implements Comparator<CompetitiveSwimmer> {
    @Override
    public int compare(CompetitiveSwimmer o1, CompetitiveSwimmer o2) {
        return Long.compare(o1.getBestTime().toMillis(), o2.getBestTime().toMillis());
    }
}



