import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 */
public class BonusMember {


    private final int memberNo;
    private final Personals personals;
    private final LocalDate enrolledDate;
    private int bonuspoints = 0;
   public final double FACTOR_SILVER = 1.2;
    public final double FACTOR_GOLD = 1.5;


    /**
     *
     */
    public BonusMember(int memberNo, Personals personals, int bonuspoints, int year, int month, int day )
    {
        if (memberNo == 0
        || personals == null
        || bonuspoints < 0
        || year == 0
        || month == 0
        || day == 0)
        {throw new IllegalArgumentException("One or more of the parameters are invalid."); }
            else
        {
            this.enrolledDate = LocalDate.of(year,month,day);
            this.memberNo = memberNo;
            this.personals = personals;
            }
            this.bonuspoints = bonuspoints;

        }



    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public Personals getPersonals() {
        return personals;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public int getBonuspoints() {
        return bonuspoints;
    }

    public int findQualificationPoints(LocalDate date) {
           int points = 0;
        if (ChronoUnit.DAYS.between(this.enrolledDate, date) < 365) {
            points = bonuspoints;



    }
    return points;
    }


        public boolean okPassword(String password){
            boolean strongPass = personals.okPassword(password);
            return strongPass;
        }
        public int registerPoints(int newPoints){
            bonuspoints = bonuspoints + newPoints;
            return bonuspoints;
        }
    }
