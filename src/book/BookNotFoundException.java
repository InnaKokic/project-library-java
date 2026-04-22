package book;

import exception.LibraryException;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(int bookId) {
        super(
                "Book could "
        );
    }
}
