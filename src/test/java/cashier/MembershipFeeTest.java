package cashier;

import data.Database;
import member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipFeeTest {

    private Database mockDatabase;
    private MembershipFee membershipFee;

    @BeforeEach
    public void setUp() {
        mockDatabase = new Database();
        membershipFee = new MembershipFee();

        // Opret en liste af testmedlemmer
        List<Member> testMembers = Arrays.asList(
                new Member("John Doe", "123 Street", LocalDate.of(2005, 1, 1), 12345, "john@example.com", true, true, 0), // Junior
                new Member("Jane Doe", "456 Avenue", LocalDate.of(1980, 1, 1), 67890, "jane@example.com", true, true, 0), // Senior
                new Member("Bob Smith", "789 Road", LocalDate.of(1950, 1, 1), 13579, "bob@example.com", true, true, 0), // Elder
                new Member("Alice Johnson", "321 Lane", LocalDate.of(1990, 1, 1), 24680, "alice@example.com", false, false, 0)  // Passive
        );

        // Konfigurer mockDatabase til at returnere testmedlemmerne
    }


    @Test
    public void testContingent() {
        // arrange
        int totalAmount = 0;
        for (Member member : membershipFee.getAllMembers()) {
            LocalDate localDate = LocalDate.now();
            int age = Period.between(member.getBirthday(), localDate).getYears();
            boolean isActive = member.isActive();
            if (age < 18 && isActive) {
                totalAmount += membershipFee.getJuniorFee();
            } else if (age >= 18 && age < 60 && isActive) {
                totalAmount += membershipFee.getSeniorFee();
            } else if (age >= 60 && isActive) {
                totalAmount += membershipFee.getElderFee();
            } else {
                totalAmount += membershipFee.getPassiveFee();
            }
        }

            // Forvent
            int expectedTotal = 1000 + 1000 + 1000 + 1000;


            // Assert
            assertEquals(expectedTotal, totalAmount);
        }
    @org.junit.jupiter.api.Test
    public void elderFee() {
        // Arrange
        MembershipFee membershipFee = new MembershipFee();
        int seniorFee = membershipFee.getSeniorFee();
        int elderDiscount = 25;
        // Act
        int elderFee = membershipFee.elderFee();

        // Assert
        int expectedElderFee = seniorFee - (seniorFee * elderDiscount / 100);
        assertEquals(expectedElderFee, elderFee);
    }
}

