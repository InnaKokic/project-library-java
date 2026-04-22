package loan;

import exception.LibraryException;

import java.util.List;
import java.util.Scanner;

public class LoanController {

    LoanServices loanServices = new LoanServices();

    private final Scanner scanner = new Scanner (System.in);


    public void showLoanMenu() {

        boolean active = true;
        int choice;

        while (active) {

            System.out.println("--------------------------");
            System.out.println("LOAN OPTIONS");
            System.out.println("--------------------------");
            System.out.println("1. Borrow book");
            System.out.println("2. Return book");
            System.out.println("3. Extend loan");
            System.out.println("4. Admin tools");
            System.out.println("0. Back");
            System.out.println("---------------------------");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> borrowBook();
                case 2 -> returnBook();
                case 3 -> extendLoan();
                case 4 -> showAdminMenu();
                case 0 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }

        }


    }

public void showAdminMenu() {

    boolean active = true;
    int choice;

    while (active) {
        System.out.println("---------------------------");
        System.out.println("ADMIN TOOLS");
        System.out.println("--------------------------");
        System.out.println("1. Show all active loans");
        System.out.println("2. Show overdue loans");
        System.out.println("3. Return a book manually");
        System.out.println("4. See list of 5 most borrowed books");
        System.out.println("0. Back");
        System.out.print("Choose an option (1-4): ");
        choice = scanner.nextInt();


        switch (choice) {
            case 1 -> showAllLoans();
            case 2 -> showOverdueLoans();
            case 3 -> returnBook();
            case 4 -> showPopularBooks();
            case 0 -> active = false;
          default -> System.out.println("INVALID OPTION");
        }
    }
}
    public void showAllLoans() {

        System.out.println("---------------------");
        System.out.println("ALL ACTIVE LOANS");
        System.out.println("---------------------");

        List<Loan> loans = loanServices.showAllLoans();
        for (Loan loan : loans)
            System.out.println(loan);



    }
    public void showOverdueLoans() {

        System.out.println("---------------------");
        System.out.println("ALL OVERDUE LOANS");
        System.out.println("---------------------");

        List<Loan> loans = loanServices.showOverdueLoans();
        for (Loan loan : loans)
            System.out.println(loan);



    }

    public void showPopularBooks() {
        System.out.println("---------------------");
        System.out.println("5 MOST BORROWED BOOKS");
        System.out.println("---------------------");

        List<PopularBookDTO> popularBooks = loanServices.showPopularBooks();
        for (PopularBookDTO book : popularBooks) {
            System.out.println(book.title() + " - Number of loans: " + book.loanCount());
        }

    }


    public void borrowBook() {

        System.out.println("---------------------");
        System.out.println("BORROW BOOK");
        System.out.println("---------------------");

        System.out.print("Enter your member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();



        try {
            loanServices.createLoan(memberId, bookId);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }


    }
    public void extendLoan() {

        System.out.println("---------------------");
        System.out.println("EXTEND LOAN");
        System.out.println("---------------------");

        System.out.print("Enter your member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        try{
            loanServices.extendLoan(memberId, bookId);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }





    }
    public void returnBook() {

        System.out.println("---------------------");
        System.out.println("RETURN BOOK");
        System.out.println("---------------------");

        System.out.print("Enter borrowers member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        loanServices.returnBook(memberId, bookId);



    }




}
