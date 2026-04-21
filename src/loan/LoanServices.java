package loan;

import member.Member;
import member.MemberRepository;
import member.MemberSuspendedException;

import java.util.List;

public class LoanServices {

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

    public void createLoan(int memberID, int bookId){


        Member member = memberRepository.getMemberById(memberID);

        if (member != null && member.getStatus().equalsIgnoreCase("suspended")) {
            throw new MemberSuspendedException(member.getId());
        }
        //Vad händer om bookId inte finns?
        //Vad händer om memberId inte finns?
        //Vad händer om available_copies = 0?


loanRepository.createLoan(memberID, bookId);

    }

    public void extendLoan(int memberID, int bookId){
        Member member = memberRepository.getMemberById(memberID);

        if (member != null && member.getStatus().equalsIgnoreCase("suspended")) {
            throw new MemberSuspendedException(member.getId());
        }

loanRepository.extendLoan(memberID, bookId);

    }

    public void returnBook(int memberId, int bookId){

        //Returnera att bok inte ät utlånad

        loanRepository.returnBook(memberId, bookId);
    }

}
