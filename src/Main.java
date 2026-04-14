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
    System.out.println("VÄLKOMMEN TILL BIBLIOTEKET");
    System.out.println("**************************");
    System.out.println("1. Alternativ för böcker");
    System.out.println("2. Alternativ för lån");
    System.out.println("3. Alternativ för medlemskap");
    System.out.println("4. Avsluta");
    System.out.println("**************************");
    System.out.print("Välj ett alternativ (1-4): ");
    choise = scanner.nextInt();


    switch (choise) {
        case 1 -> bookController.showBookMenu();
        case 2 -> System.out.println("Lån");
        case 3 -> System.out.println("Medlemskap");
        case 4 -> active = false;
        default -> System.out.println("Ogiltigt alternativ");
    }





}

        scanner.close();


        }


    }
