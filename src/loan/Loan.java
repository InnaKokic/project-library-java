package loan;

import java.time.LocalDate;

public class Loan {

    private int id;
    private int bookId;
    private int memberId;
    private String bookTitle;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(int id, int bookId, int memberId, String bookTitle, LocalDate loanDate,
                LocalDate dueDate,
                LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }


    public int getId() {
        return id;
    }
    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() { return bookTitle;}

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }




    @Override
    public String toString() {
        return "Title: " + bookTitle + " | " + "Loan date: " + loanDate +
                " | " + "Due date: " + dueDate;
    }

}
