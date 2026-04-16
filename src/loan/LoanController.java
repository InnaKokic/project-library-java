package loan;

import java.util.List;
import java.util.Scanner;

public class LoanController {

    LoanRepository loanRepository = new LoanRepository();
    LoanServices loanServices = new LoanServices();

    private final Scanner scanner = new Scanner (System.in);


    public void showLoanMenu() {

        boolean active = true;
        int choise;

        while (active) {

            System.out.println("--------------------------");
            System.out.println("LOAN OPTIONS");
            System.out.println("--------------------------");
            System.out.println("1. Borrow book");
            System.out.println("2. Return book");
            System.out.println("3. Extend loan");
            System.out.println("4. Show my active loans");
            System.out.println("5. Back");
            System.out.println("---------------------------");
            System.out.print("Choose an option (1-5): ");
            choise = scanner.nextInt();

            switch (choise) {
                case 1 -> borrowBook();
                case 2 -> returnBook();
                case 3 -> extendLoan();
                case 4 -> System.out.println(" ");
                case 5 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }

        }


    }


//    public void showMemberLoans() {
//
//        System.out.println("---------------------");
//        System.out.println("SHOW LOANS");
//        System.out.println("---------------------");
//        System.out.print("Enter your member ID: ");
//        int memberId = scanner.nextInt();
//
//        List<Loan> loans = loanServices.showMemberLoans(memberEmail);
//        for (Loan loan : loans) {System.out.println(loan);}
//
//
//    }

    public void borrowBook() {

        System.out.println("---------------------");
        System.out.println("BORROW BOOK");
        System.out.println("---------------------");

        System.out.print("Enter your member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        loanServices.createLoan(memberId, bookId);



    }
    public void extendLoan() {

        System.out.println("---------------------");
        System.out.println("EXTEND LOAN");
        System.out.println("---------------------");

        System.out.print("Enter your member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        loanServices.extendLoan(memberId, bookId);



    }
    public void returnBook() {

        System.out.println("---------------------");
        System.out.println("RETURN BOOK");
        System.out.println("---------------------");

        System.out.print("Enter your member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        loanServices.returnBook(memberId, bookId);



    }




}
