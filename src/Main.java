import book.BookController;
import loan.LoanController;
import member.MemberController;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    private static BookController bookController = new BookController();
    private static LoanController loanController = new LoanController();
    private static MemberController memberController = new MemberController();

    private static Scanner scanner = new Scanner(System.in);

    //En metod för att förhindra annan input än int i scanner som kräver int
    //använder inbyggd java klass InputMismatchException
    private static int readInt() {
        while (true) {
            try {
                int input = scanner.nextInt();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {




        boolean active = true;
        int choice;


while (active){
    System.out.println("**************************");
    System.out.println("WELCOME TO THE LIBRARY");
    System.out.println("**************************");
    System.out.println("1. Book options");
    System.out.println("2. Loan options");
    System.out.println("3. Membership options");
    System.out.println("0. Exit");
    System.out.println("**************************");
    System.out.print("Choose an option (1-3): ");
    choice = readInt();


    switch (choice) {
        case 1 -> bookController.showBookMenu();
        case 2 -> loanController.showLoanMenu();
        case 3 -> memberController.showMemberMenu();
        case 0 -> active = false;
        default -> System.out.println("Invalid option");
    }
}

        scanner.close();


        }


    }
