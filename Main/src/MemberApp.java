import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberApp implements Comparable {
    private  MemberArchive members;

    private final int ADD_MEMBER = 1;
    private final int LIST_ALL_MEMBERS = 2;
    private final int UPGRADE_MEMBER = 3;
    private final int REGISTER_POINTS = 4;
    private final int LIST_ALL_MEMBERS_BY_POINTS = 5;
    // ---- add more constants as needed ---
    private final int EXIT = 9;

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     *                 If 0 is returned, the user has entered a wrong value
     */
    private int showMenu()

    {
        int menuChoice = 0;

        System.out.println("\n***** BonusMemberApp v0.1 *****\n");
        System.out.println("1. Add BonusMember");
        System.out.println("2. List all BonusMembers");
        System.out.println("3. Upgrade Qualified Members");
        System.out.println("4. Register New Points");


        System.out.println("9. Quit"); // Or another number than 9
        System.out.println("\nPlease select from the menu.\n");
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt())
        {
            menuChoice = sc.nextInt();
        } else
        {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * Initial start conditions needed for the class to work
     */
    public void init()
    {
        this.members = new MemberArchive();

    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;

        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice)
            {
                case ADD_MEMBER :
                    addMember();
                    break;

                case LIST_ALL_MEMBERS:
                    listAllMembers();
                    break;

                case UPGRADE_MEMBER:
                    upgradeMembers();
                    break;

                case REGISTER_POINTS:
                    registerPoints();
                    break;

                case LIST_ALL_MEMBERS_BY_POINTS:
                        getArchive();
                    break;
                // ---- Add more cases here if needed ----

                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;
                    break;

                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }

    }/**
     * adds books to the bookregistry by calling to the bookregistry class
     *
     */
    private void addMember()
    {
        //variables for creating a new and adding HearingAid//
        int memberNo = 0;
        boolean borrowed = false;
        String firstname = "";
        String surname = "";
        String emailAdress = "";
        String password = "";
        int bonuspoints = 0;
        int year = 0;
        int month = 0;
        int day = 0;
        //variable for maintain if a an input is acceptable//
        boolean validInput = true;
         memberNo = members.findAvailableNo();

        Scanner scnr = new Scanner(System.in);

        System.out.println("Please write your firstname):");
        if (scnr.hasNextLine())
        {
            firstname = scnr.nextLine();
        }
        else
        {
            System.out.println("Please enter an actual name");
            validInput = false;

        }
        System.out.println("Please write your Surname):");
        if (scnr.hasNextLine())
        {
            surname = scnr.nextLine();
        }
        else
        {
            System.out.println("Please enter an actual name");
            validInput = false;

        }
        System.out.println("Please write your emailAdress):");
        if (scnr.hasNextLine())
        {
            emailAdress = scnr.nextLine();
        }
        else
        {
            System.out.println("Please enter an actual emailAdress");
            validInput = false;

        }
        System.out.println("Please write your password):");
        if (scnr.hasNextLine() && scnr.nextLine().length() > 7)
        {
            password = scnr.nextLine();
        }
        else
        {
            System.out.println("Please enter an PASSWORD with more than 7 characters");
            validInput = false;

        }

        if (validInput = true)
        {

            Personals personals = new Personals(firstname, surname, emailAdress, password);
            members.addMember(personals);

            System.out.println("Thank you for joining our BonusMembership");

        }
    }
    private void listAllMembers()
    {
        for (Iterator<BonusMember> it = members.iterator(); it.hasNext(); ) {
            BonusMember bonusMember = it.next();
               String member = bonusMember.getPersonals().getFirstname() +" "+ bonusMember.getPersonals().getSurname();
                System.out.println(member);
        }

    }
    private void upgradeMembers()
    {
        members.checkMembers();
    }


    public List<BonusMember> getArchive()
    {
        //ArrayList<BonusMember> memlist =  members.getMembers().stream().sorted();  //.collect(Collectors.toCollection(ArrayList::new));
        Stream<BonusMember> bonusmemberStream = Stream.of();
        ArrayList<BonusMember> memList = members.getArchive();
        ArrayList<BonusMember> sortedList  = memList.stream()
                .sorted(BonusMember::compareTo)
                .collect(Collectors.toCollection(ArrayList::new));
        for (BonusMember bonusMember : sortedList
             ) { System.out.print(bonusMember.getPersonals().getFirstname() + bonusMember.getPersonals().getSurname() + bonusMember.getBonuspoints());

        }
            return sortedList;
    }
    private void registerPoints()
    {
        boolean validInput = true;
        int memberNo = 0;
        int newPoints = 0;
        upgradeMembers();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please write your memberNumber");
        if(scnr.hasNextInt())
        {
            memberNo = scnr.nextInt();
            scnr.nextLine();
        }
        else{
            validInput = false;
            System.out.print("please write a membernumber");
        }
        System.out.println("Please write your memberNumber");
        if(scnr.hasNextInt())
        {
            newPoints = scnr.nextInt();
        }
        else{
            validInput = false;
            System.out.print("please write a number");

        }
        members.findMember(memberNo).registerPoints(newPoints);
        members.findMember(memberNo);

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
