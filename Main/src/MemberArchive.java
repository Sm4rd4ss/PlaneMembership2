import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class MemberArchive implements Iterable<BonusMember> {
    private HashMap<Integer,BonusMember> Members = new HashMap();

    public MemberArchive()
    { }
    //public Iterator<BonusMember> getIterator(){
    //    return this.Members.iterator(); {
    //}


    public HashMap<Integer, BonusMember> getMembers() {
        return Members;

    }
    public boolean addMember(Personals pers){
    int bonusPoints = 0;
    boolean added = false;
    int memberNo = findAvailableNo();
    BonusMember sicMember = new BasicMember(memberNo, pers, bonusPoints, LocalDate.now().getYear(),
            LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
    Members.put(memberNo, sicMember);
        if (Members.get(memberNo) != null){
            added = true;
        }
        return added;
    }
    public int findAvailableNo(){
        Random random = new Random();
        random.nextInt(100000);
        int c = 0;
            while(Members.get(random) != null){
               c = random.nextInt(100000);

            }
            return c;

    }
    public void checkMembers(){
        for (Iterator<BonusMember> it = Members.values().iterator(); it.hasNext(); ) {
            BonusMember bonusMember = it.next();
            if(bonusMember.getBonuspoints() > 24999){
                Members.remove(bonusMember.getMemberNo());
                bonusMember = new SilverMember(bonusMember.getMemberNo(), bonusMember.getPersonals(),
                        bonusMember.getBonuspoints(),bonusMember.getEnrolledDate().getYear(),
                        bonusMember.getEnrolledDate().getMonthValue(),bonusMember.getEnrolledDate().getDayOfMonth());
                Members.put(bonusMember.getMemberNo(), bonusMember);
            }
            if (bonusMember.getBonuspoints() > 74999){
                Members.remove(bonusMember.getMemberNo());
                bonusMember = new GoldMember(bonusMember.getMemberNo(), bonusMember.getPersonals(),
                        bonusMember.getBonuspoints(),bonusMember.getEnrolledDate().getYear(),
                        bonusMember.getEnrolledDate().getMonthValue(),bonusMember.getEnrolledDate().getDayOfMonth());
                Members.put(bonusMember.getMemberNo(), bonusMember);

            }

        }

    }
    public BonusMember findMember(int memberNo){
        BonusMember foundMember = null;
        foundMember = Members.get(memberNo);
        return foundMember;

    }
    public boolean removeMember(int memberNo){
        boolean removed = false;
         Members.remove(memberNo);
         if(findMember(memberNo)== null)
         {
            removed = true;
         }
         return removed;
    }
    public int findPoints(int memberNo, String password){
            BonusMember member = findMember(memberNo);
            Personals person= member.getPersonals();
            int points = 0;
            if (person.okPassword(password)){
                points = member.getBonuspoints();
            }
            else{ points = -1;}

        return points;
    }


    @Override
    public Iterator<BonusMember> iterator() {
      Iterator<BonusMember> it = Members.values().iterator();
      return it;
    }
}




