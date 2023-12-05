package member;

import java.time.LocalDate;

public class CasualSwimmer extends Member {
    public CasualSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive, int arrears) {
        super(name, address, birthday, memberID, email, membershipType, isActive, arrears);
    }
}

