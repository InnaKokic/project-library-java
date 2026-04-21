import book.Book;
import book.BookController;
import book.BookRepository;
import loan.LoanController;
import member.MemberController;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        BookController bookController = new BookController();
        LoanController loanController = new LoanController();
        MemberController memberController = new MemberController();

        Scanner scanner = new Scanner(System.in);

        boolean active = true;
        int choise;


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
    choise = scanner.nextInt();


    switch (choise) {
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
