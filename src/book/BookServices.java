package book;

import java.time.LocalDate;
import java.util.List;
import java.time.Year;

public class BookServices {

    BookRepository bookRepository = new BookRepository();

    public List<BookDTO> getAllBooksDTO() {
        return bookRepository.getAllBooks().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthors(),
                        book.getIsbn(),
                        book.getYear(),
                        book.getAvailableCopies(),
                        book.getLanguage(),
                        book.getSummary()
                ))
                .toList();
    }

    public List<Book> showAllBooks() {

        return bookRepository.getAllBooks();
    }

    public List<Book> searchTitle(String title) {

        if (title == null) {
            System.out.println("Search cannot be empty.");
            return List.of(); //List.of() returnerar en tom lista, istället för att returnera null
        }

        // trim() tar bort mellanslag före och efter sökordet.
        String cleanedTitle = title.trim();


        // Om användaren bara skriver mellanslag blir cleanedTitle en tom sträng.
        // Då stoppar vi här och returnerar en tom lista i stället för
        // att skicka en dålig sökning vidare till repot.
        if (cleanedTitle.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return List.of();
        }

        // Kräver en minsta längd på söktexten.
        // Förhindrar väldigt korta sökningar som "a", som ger för många träffar.
        if (cleanedTitle.length() < 2) {
            System.out.println("Title must contain at least 2 characters.");
            return List.of();
        }

        // Först när titeln är validerad anropar vi repot.
        return bookRepository.searchBookByTitle(cleanedTitle);

    }
    public List<Book> searchYear(int year) {

        //hämtar aktuellt år dynamiskt i stället för att hårdkoda ett värde.
        // Det gör att valideringen fortsätter fungera nästa år utan att koden behöver ändras.
        int currentYear = Year.now().getValue();

        // kontrollerar att året är rimligt innan vi söker i databasen.
        // stoppar negativa årtal och årtal som är senare än aktuellt år.
        if (year < 0 || year > currentYear) {
            System.out.println("Year must be between 0 and " + currentYear + ".");
            return List.of();
        }

        // ÄNDRING:
        // Bara giltiga årtal skickas vidare till repot.
        return bookRepository.searchBookByYear(year);

    }

    public void addBook(String title, String isbn, int year) {

        //Om titel är tom, även efter trimmning av mellanslag, kommer man ej vidare
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }


        // currentYear hämtar nuvarande år.
        int currentYear = Year.now().getValue();
        //Kollar om input år är tidigare än år 0, eller i framtiden.
        if (year < 0 || year > currentYear) {
            System.out.println("Year must be between 0 and " + currentYear);
            return;
        }
        bookRepository.addBook(title, isbn, year);
    }

    public void deleteBook(int id) {

        bookRepository.deleteBook(id);

    }

    public void updateBook(int id, String newTitle, String newIsbn, int newYear ){

        bookRepository.updateBook(id, newTitle, newIsbn, newYear);
    }

    public void addAuthor(String firstName, String lastName, String nationality,
                          LocalDate birthDate) {
        bookRepository.addAuthor(firstName,lastName,nationality,birthDate);
    }
    public void updateAuthor(int id, String firstName, String lastName, String nationality,
                          LocalDate birthDate) {
        bookRepository.updateAuthor(id, firstName,lastName,nationality,birthDate);
    }

    public void categoriesBook(int id, int categoryId) {

        if (categoryId < 1 || categoryId > 12) {
            System.out.println("Invalid category. Choose between 1 and 12.");
            return;
        }

        bookRepository.categoriesBook(id, categoryId);
    }
}
