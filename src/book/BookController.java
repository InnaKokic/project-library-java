package book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookController {

    BookRepository bookRepository = new BookRepository();


    private final Scanner scanner = new Scanner(System.in);



    public void showBookMenu() {

        boolean active = true;
        int choise;


        while (active) {

            System.out.println("--------------------------");
            System.out.println("ALTERNATIV FÖR BÖCKER");
            System.out.println("--------------------------");
            System.out.println("1. Se alla böcker");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. Tillbaka");
            System.out.println("---------------------------");
            System.out.print("Välj ett alternativ (1-4): ");
            choise = scanner.nextInt();

            switch (choise) {
                case 1 -> showAllBooks();
                case 2 -> System.out.println("2");
                case 3 -> System.out.println("3");
                case 4 -> active = false;
                default -> System.out.println("OGILTIGT VAL");
            }

        }


    }

    public void showAllBooks(){
        ArrayList<Book> books = bookRepository.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

}
