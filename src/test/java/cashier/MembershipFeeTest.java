package cashier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MembershipFeeTest {
    private MembershipFee membership;
    @BeforeEach
    void setUp() {
membership = new MembershipFee();
    }

    @AfterEach
    void tearDown() {
membership = null;
    }

    @org.junit.jupiter.api.Test
    void elderFee() {
        //arrange
        MembershipFee elder = new MembershipFee();
        int seniorFee = 1600;
        int elderIntermediary = 25;



        //act
        int result = membership.elderFee();
        int actualElderFee = 1200;
        //Assert
        assertEquals(actualElderFee, result);
    }

    @org.junit.jupiter.api.Test
    void totalContingent() {

    }
}