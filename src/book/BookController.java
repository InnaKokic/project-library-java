package book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookController {

    BookRepository bookRepository = new BookRepository();
    BookServices bookServices = new BookServices();



    private final Scanner scanner = new Scanner(System.in);



    public void showBookMenu() {

        boolean active = true;
        int choise;


        while (active) {

            System.out.println("--------------------------");
            System.out.println("BOOK OPTIONS");
            System.out.println("--------------------------");
            System.out.println("1. View all books");
            System.out.println("2. Search for books ");
            System.out.println("3. ");
            System.out.println("4. Back");
            System.out.println("---------------------------");
            System.out.print("Choose an option (1-4): ");
            choise = scanner.nextInt();

            switch (choise) {
                case 1 -> showAllBooks();
                case 2 -> searchBooks();
                case 3 -> System.out.println("3");
                case 4 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }
        }


    }

    public void showAllBooks(){
        List<Book> books = bookRepository.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBooks() {
        boolean active = true;
        int choise;

        scanner.nextLine();
        System.out.println("SEARCH FOR BOOKS");
        System.out.println("--------------------------");
        System.out.println("1. Search by title");
        System.out.println("2. Search by year");
        System.out.println("3. Back");
        System.out.println("---------------------------");
        System.out.print("Choose an option (1-3): ");
        choise = scanner.nextInt();


switch (choise) {
    case 1 -> searchByTitle();
    case 2 -> searchByYear();
    case 3 -> active = false;
    default -> System.out.println("INVALID OPTION");
}




    }

    public void searchByTitle() {

        scanner.nextLine();
        System.out.println("SEARCH FOR BOOKS BY TITLE");
        System.out.println("--------------------------");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        List<Book> books = bookServices.searchTitle(title);

        for(Book book : books) {
            System.out.println(book);
        }

    }


    public void searchByYear() {

        scanner.nextLine();
        System.out.println("SEARCH FOR BOOKS BY YEAR");
        System.out.println("--------------------------");
        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        List<Book> books = bookServices.searchYear(year);

        for(Book book : books) {
            System.out.println(book);
        }

    }


}
