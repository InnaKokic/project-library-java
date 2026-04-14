package book;

import java.util.ArrayList;
import java.util.List;

public class BookServices {

    BookRepository bookRepository = new BookRepository();

   // funktion som gör och validerar sökningen????? och sen skickas in i controller som skickar
    // tillbaka search query som services skickar till repo

    public List<Book> searchTitle(String title) {

        return bookRepository.searchBookByTitle(title);

    }
    public List<Book> searchYear(int year) {

        return bookRepository.searchBookByYear(year);

    }


}
