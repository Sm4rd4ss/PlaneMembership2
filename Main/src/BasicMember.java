

public class BasicMember extends BonusMember
{
    public BasicMember(int memberNo, Personals personals, int bonuspoints, int year, int month, int day)
    {

       super(memberNo, personals, bonuspoints, year, month, day);

    }

    @Override
    public int registerPoints(int newPoints) {
        this.bonuspoints = bonuspoints + newPoints;
        return bonuspoints;
    }
}
