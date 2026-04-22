package loan;

import book.Book;
import book.BookAvailabilityDTO;
import book.BookRepository;
import member.Member;
import member.MemberNotFoundException;
import member.MemberRepository;
import member.MemberSuspendedException;

import java.util.List;

public class LoanServices {

    BookRepository bookRepository = new BookRepository();
    MemberRepository memberRepository = new MemberRepository();
    LoanRepository loanRepository = new LoanRepository();

    public List<Loan> showMemberLoans(String memberEmail){

        //Visa bara lån som ej är returnerade

        return loanRepository.getAllMemberLoans(memberEmail);
    }

    public List<Loan> showAllLoans() {

      return loanRepository.getAllActiveLoans();
    }

    public List<Loan> showOverdueLoans() {

        return loanRepository.getOverdueLoans();
    }

    public List<PopularBookDTO> showPopularBooks() {

        return loanRepository.getPopularBooks();
    }

    public void createLoan(int memberID, int bookId){

        BookAvailabilityDTO book = bookRepository.getBookById(bookId);
        Member member = memberRepository.getMemberById(memberID);

        if (member == null) {
            throw new MemberNotFoundException("ID: " + memberID);
        }

        if (member.getStatus().equalsIgnoreCase("suspended")) {
            throw new MemberSuspendedException(member.getId());
        }


        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.availableCopies() == 0) {
            System.out.println("No available copies of this book.");
            return;
        }



loanRepository.createLoan(memberID, bookId);

    }

    public void extendLoan(int memberID, int bookId){
        Member member = memberRepository.getMemberById(memberID);

        if (member == null) {
            throw new MemberNotFoundException("ID: " + memberID);
        }
        if (member.getStatus().equalsIgnoreCase("suspended")) {
            throw new MemberSuspendedException(member.getId());
        }

loanRepository.extendLoan(memberID, bookId);

    }

    public void returnBook(int memberId, int bookId){

        //Returnera att bok inte ät utlånad

        loanRepository.returnBook(memberId, bookId);
    }

}
