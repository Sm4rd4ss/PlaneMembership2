public class SilverMember extends BonusMember {

    public SilverMember(int memberNo, Personals personals, int bonuspoints, int year, int month, int day)
    {
        super(memberNo, personals, bonuspoints, year, month, day);

    }

    @Override
    public int registerPoints(int newPoints) {
        double points = (newPoints*FACTOR_SILVER);
        int i = (int) Math.ceil(points);
        this.bonuspoints = bonuspoints + i;
        return bonuspoints;
    }
}
