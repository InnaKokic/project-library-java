package member;

import loan.Loan;
import loan.LoanServices;

import java.util.List;
import java.util.Scanner;

public class MemberController {

    MemberRepository memberRepository = new MemberRepository();
    MemberServices memberServices = new MemberServices();
    LoanServices loanServices = new LoanServices();

    private final Scanner scanner = new Scanner(System.in);


    public void showMemberMenu() {

        boolean active = true;
        int choise;

        while (active) {

            System.out.println("--------------------------");
            System.out.println("MEMBER OPTIONS");
            System.out.println("--------------------------");
            System.out.println("1. See profile");
            System.out.println("2. Become member");
            System.out.println("3. Admin tools");
            System.out.println("0. Back");
            System.out.println("---------------------------");
            System.out.print("Choose an option (1-5): ");
            choise = scanner.nextInt();

            switch (choise) {
                case 1 -> showProfile();
                case 2 -> System.out.println("BECOME MEMBER");
                case 3 -> showAdminMenu();
                case 0 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }
        }
    }

    public void showProfile() {

        boolean active = true;
        int choise;

        System.out.println("-------------------");
        System.out.println("SEE PROFILE");
        System.out.println("-------------------");
        scanner.nextLine();
        System.out.print("Enter your email: ");
        String memberEmail = scanner.nextLine();


        System.out.println("-------------------");
        System.out.println("YOUR PROFILE");
        System.out.println("-------------------");
        List<Member> members = memberServices.showMemberProfile(memberEmail);
        for (Member member : members) {
            System.out.println(member);
        }

        while (active) {

        System.out.println("-------------------");
        System.out.println("MEMBER OPTIONS");
        System.out.println("-------------------");
        System.out.println("1. See my loans");
        System.out.println("2. Edit profile");
        System.out.println("3. Back");
        System.out.println("---------------------------");
        System.out.print("Choose an option (1-3): ");
        choise = scanner.nextInt();

        switch (choise) {
            case 1 -> showMemberLoans(memberEmail);
            case 2 -> editProfileMenu(memberEmail);
            case 3 -> active = false;
            default -> System.out.println("INVALID OPTION");
        }
        }



    }

    public void showMemberLoans(String memberEmail) {

        System.out.println("---------------------");
        System.out.println("YOUR ACTIVE LOANS");
        System.out.println("---------------------");

        List<Loan> loans = loanServices.showMemberLoans(memberEmail);
        for (Loan loan : loans) {System.out.println(loan);}


    }


    public void editProfileMenu(String memberEmail) {

    boolean active = true;
    int choise;

    System.out.println("-------------------");
    System.out.println("YOUR PROFILE");
    System.out.println("-------------------");

    List<Member> members = memberServices.showMemberProfile(memberEmail);
    for (Member member : members) {
        System.out.println(member);
    }

    while (active) {

        System.out.println("-------------------");
        System.out.println("OPTIONS");
        System.out.println("-------------------");
        System.out.println("1. Change email");
        System.out.println("2. Change name");
        System.out.println("3. Change membership type");
        System.out.println("4. Delete profile");
        System.out.println("5. Back");
        System.out.println("---------------------------");
        System.out.print("Choose an option (1-5): ");
        choise = scanner.nextInt();

        switch (choise) {
            case 1 -> editEmail(memberEmail);
            case 2 -> editName(memberEmail);
            case 3 -> System.out.println("opt 3");
            case 4 -> System.out.println("opt 4");
            case 5 -> active = false;
            default -> System.out.println("INVALID OPTION");
        }
    }


    }

    public void editEmail(String memberEmail) {
        System.out.println("-------------------");
        System.out.println("CHANGE EMAIL");
        System.out.println("-------------------");
        scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        memberServices.editProfileEmail(memberEmail, newEmail);



    }
    public void editName(String memberEmail) {
        System.out.println("-------------------");
        System.out.println("CHANGE NAME");
        System.out.println("-------------------");
        scanner.nextLine();
        System.out.print("Enter first name: ");
        String newFirstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String newLastName = scanner.nextLine();

        memberServices.editProfileName(newFirstName, newLastName, memberEmail);



    }

    public void showAdminMenu() {
        boolean active = true;
        int choise;

        while (active) {

            System.out.println("--------------------------");
            System.out.println("ADMIN TOOLS");
            System.out.println("--------------------------");
            System.out.println("1. Add new member");
            System.out.println("2. Suspend member");
            System.out.println("0. Back");
            System.out.println("---------------------------");
            System.out.print("Choose an option (1-5): ");
            choise = scanner.nextInt();

            switch (choise) {
                case 1 -> addMember();
                case 2 -> suspendMember();
                case 0 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }
        }

    }

    public void addMember() {
        System.out.println("--------------------------");
        System.out.println("ADD NEW MEMBER");
        System.out.println("--------------------------");
        scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Membership types: [STANDARD] [PREMIUM] ");
        System.out.print("Enter membership type: ");
        String memberType = scanner.nextLine();


        memberServices.addMember(firstName,lastName,email,memberType);



    }

    public void suspendMember() {

        int choice;

        System.out.println("--------------------------");
        System.out.println("SUSPEND A MEMBER");
        System.out.println("--------------------------");
        scanner.nextLine();
        System.out.print("Enter email of member to suspend: ");
        String memberEmail = scanner.nextLine();

        System.out.println("--------------------------");
        System.out.println("MEMBER PROFILE");
        System.out.println("--------------------------");

        List<Member> members = memberServices.showMemberProfile(memberEmail);
        for (Member member : members) {
            System.out.println(member);
        }
        System.out.println("--------------------------");
        System.out.println("1. Suspend member");
        System.out.println("2. Cancel");
        System.out.print("Choose an option (1-2): ");
        choice = scanner.nextInt();

        switch (choice) {

            case 1 -> memberServices.suspendMember(memberEmail);
            case 2 -> showAdminMenu();
        }


    }




}