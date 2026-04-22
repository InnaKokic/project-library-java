package loan;

import exception.LibraryException;

public class NoActiveLoanException extends LibraryException {
    public NoActiveLoanException(int memberId, int bookId) {
        super("No active loan found for member [ID: " + memberId + "] and book [ID: " + bookId + "].");
    }
}
