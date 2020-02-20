public class GoldMember extends BonusMember{

    public GoldMember(int memberNo, Personals personals, int bonuspoints, int year, int month, int day)
    {
        super(memberNo, personals, bonuspoints, year, month, day);

    }

    @Override
    public int registerPoints(int newPoints) {
      double points = (newPoints*FACTOR_GOLD);
      int i = (int) Math.ceil(points);
        this.bonuspoints = bonuspoints + i;
        return bonuspoints;
    }
}
