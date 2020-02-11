
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
    /**
     * Test class used to test the calculation of bonus points in the different classes
     * representing the different memberships.
     * To use this class, you first have to set up the Unit-test framework in your IDE.
     */
    class BonusMemberTest {
        private LocalDate testDate;
        private Personals ole;
        private Personals tove;
        @BeforeEach
        void setUp() {
            this.testDate = LocalDate.of(2008, 2, 10);
            this.ole = new Personals("Olsen", "Ole",
                    "ole.olsen@dot.com", "ole");
            this.tove = new Personals("Hansen", "Tove",
                    "tove.hansen@dot.com", "tove");
        }
        /**
         * Tests the accuracy of the calculation of points for the basic member Ole.
         * Ole was registered as Basic Member more than 365 days before 10/2-2008, and
         * should not qualify for upgrade.
         */
        @Test
        void testBasicMemberOle() {
            BasicMember b1 = new BasicMember(100, ole, 0, 2006, 2, 15);
            b1.registerPoints(30000);
            System.out.println("Test nr 1: should be checking to gold as hes passes the silver threshold");
            assertEquals(0, b1.findQualificationPoints(testDate));
            assertEquals(30000, b1.getBonuspoints());
            System.out.println("Test nr 2: Adding 15 000 points, still no qualification points");
            b1.registerPoints(15000);
            assertEquals(0, b1.findQualificationPoints(testDate));
            assertEquals(45000, b1.getBonuspoints());
        }
        /**
         * Tests the accuracy of the calculation of points for the absic member Tove,
         * who was registered with basic membership less than 365 days before 10/2-2008,
         * and hence does qualify for an upgrade, first to Silver, then to Gold.
         */
        @Test
        void testBasicMemberTove() {
            BasicMember b2 = new BasicMember(110, tove, 0 ,2007, 3, 5);
            b2.registerPoints(30000);
            System.out.println("Test nr 3: Tove should qualify");
            assertEquals(30000, b2.findQualificationPoints(testDate));
            assertEquals(30000, b2.getBonuspoints());
            System.out.println("Test nr 4: Tove as silver member");
            SilverMember b3 = new SilverMember(b2.getMemberNo(), b2.getPersonals(),
                     b2.getBonuspoints(), b2.getEnrolledDate().getYear(), b2.getEnrolledDate().
                    getMonthValue(),b2.getEnrolledDate().getDayOfMonth());
            b3.registerPoints(50000);
            assertEquals( 90000, b3.findQualificationPoints(testDate));
            assertEquals( 90000, b3.getBonuspoints());
            System.out.println("Test nr 5: Tove as gold member");
            GoldMember b4 = new GoldMember(b3.getMemberNo(), b3.getPersonals(),
                     b3.getBonuspoints(), b2.getEnrolledDate().getYear(), b2.getEnrolledDate().
                    getMonthValue(),b2.getEnrolledDate().getDayOfMonth());
            b4.registerPoints(30000);
            assertEquals( 135000, b4.findQualificationPoints(testDate));
            assertEquals( 135000, b4.getBonuspoints());
            System.out.println("Test nr 6: Changed test date on Tove");
            testDate = LocalDate.of(2008, 12, 10);
            assertEquals( 0, b4.findQualificationPoints(testDate));
            assertEquals( 135000, b4.getBonuspoints());
        }
        /**
         * Tests the passwords of both members.
         */
        @Test
        void testPasswords() {
            System.out.println("Test nr 7: Trying wrong password on Ole");
            assertFalse(ole.okPassword("000"));
            System.out.println("Test nr 8: Trying correct password on Tove.");
            assertTrue(tove.okPassword("tove"));
        }
    }
