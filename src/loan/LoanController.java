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
                case 1 -> System.out.println("BORROW");
                case 2 -> System.out.println("RETURN");
                case 3 -> System.out.println("EXTEND");
                case 4 -> showMemberLoans();
                case 5 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }

        }


    }


    public void showMemberLoans() {

        System.out.println("---------------------");
        System.out.println("SHOW LOANS");
        System.out.println("---------------------");
        System.out.println("Enter your member ID: ");
        int memberId = scanner.nextInt();

        List<Loan> loans = loanServices.showMemberLoans(memberId);
        for (Loan loan : loans) {System.out.println(loan);}


    }


}
