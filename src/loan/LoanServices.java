package loan;

import java.util.List;

public class LoanServices {

    LoanRepository loanRepository = new LoanRepository();

    public List<Loan> showMemberLoans(int memberId){

        return loanRepository.getAllMemeberLoans(memberId);
    }

}
