package loan;

import java.util.List;

public class LoanServices {

    LoanRepository loanRepository = new LoanRepository();

    public List<Loan> showMemberLoans(String memberEmail){

        //Visa bara lån som ej är returnerade

        return loanRepository.getAllMemberLoans(memberEmail);
    }

    public void createLoan(int memberID, int bookId){

        //Vad händer om bookId inte finns?
        //Vad händer om memberId inte finns?
        //Vad händer om available_copies = 0?

loanRepository.createLoan(memberID, bookId);

    }

    public void extendLoan(int memberID, int bookId){

loanRepository.extendLoan(memberID, bookId);

    }

    public void returnBook(int memberId, int bookId){

        loanRepository.returnBook(memberId, bookId);
    }

}
