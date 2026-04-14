import book.Book;
import book.BookController;
import book.BookRepository;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        BookController bookController = new BookController();

        boolean active = true;
        int choise;
Scanner scanner = new Scanner(System.in);

while (active){
    System.out.println("**************************");
    System.out.println("WELCOME TO THE LIBRARY");
    System.out.println("**************************");
    System.out.println("1. Book options");
    System.out.println("2. Loan options");
    System.out.println("3. Membership options");
    System.out.println("4. Exit");
    System.out.println("**************************");
    System.out.print("Choose an option (1-4): ");
    choise = scanner.nextInt();


    switch (choise) {
        case 1 -> bookController.showBookMenu();
        case 2 -> System.out.println("Loans");
        case 3 -> System.out.println("Membership");
        case 4 -> active = false;
        default -> System.out.println("Invalid option");
    }
}

        scanner.close();


        }


    }
