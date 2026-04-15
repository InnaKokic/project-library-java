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
            System.out.println("3. Admin tools");
            System.out.println("4. Back");
            System.out.println("---------------------------");
            System.out.print("Choose an option (1-4): ");
            choise = scanner.nextInt();

            switch (choise) {
                case 1 -> showAllBooks();
                case 2 -> searchBooks();
                case 3 -> adminTools();
                case 4 -> active = false;
                default -> System.out.println("INVALID OPTION");
            }
        }


    }
/* ------ OPTION 1 - View all books ---- */
public void showAllBooks(){
        List<Book> books = bookRepository.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

/* ----- OPTION 2 - Search books ----- */
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

/* ----- OPTION 3 - Admin tools ----- */
public void adminTools() {
    boolean active = true;
    int choise;

    scanner.nextLine();
    System.out.println("ADMIN TOOLS");
    System.out.println("--------------------------");
    System.out.println("1. Add a book");
    System.out.println("2. Update a existing book");
    System.out.println("3. Delete a book");
    System.out.println("4. Back");
    System.out.println("---------------------------");
    System.out.print("Choose an option (1-4): ");
    choise = scanner.nextInt();


    switch (choise) {
        case 1 -> addBook();
        case 2 -> updateBook();
        case 3 -> deleteBook();
        case 4 -> active = false;
    }

}
    public void addBook() {

    System.out.println("ADD A BOOK");
    System.out.println("-------------");

    scanner.nextLine(); //Tömmer blankspace för att title inte ska spara tom sträng

    System.out.print("Title: ");
    String title = scanner.nextLine();

    System.out.print("ISBN: ");
    String isbn = scanner.nextLine();

    System.out.print("YEAR: ");
    int year = scanner.nextInt();

    bookServices.addBook(title, isbn, year);

}
    public void deleteBook() {
        System.out.println("DELETE A BOOK");
        System.out.println("-------------");

        scanner.nextLine();
        System.out.print("Enter id of book to delete: ");
        int id = scanner.nextInt();

        bookServices.deleteBook(id);

    }
    public void updateBook() {
        System.out.println("UPDATE A BOOK");
        System.out.println("-------------");

        scanner.nextLine();
        System.out.print("Enter ID of the book you want to update: ");
        int bookId = scanner.nextInt();

        scanner.nextLine();
        System.out.print("New title: ");
        String newTitle = scanner.nextLine();
        System.out.print("New isbn: ");
        String newIsbn = scanner.nextLine();
        System.out.print("New year: ");
        int newYear = scanner.nextInt();


        bookServices.updateBook(bookId, newTitle, newIsbn, newYear);


    }
}


