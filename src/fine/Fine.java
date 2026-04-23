package fine;

import java.time.LocalDate;

public class Fine {

        private int id;
        private int loanId;
        private int memberId;
        private String bookTitle;
        private double amount;
        private LocalDate issuedDate;
        private LocalDate paidDate;
        private String status;

        public Fine(int id, int loanId, int memberId, String bookTitle, double amount,
                    LocalDate issuedDate,
                    LocalDate paidDate, String status) {
            this.id = id;
            this.loanId = loanId;
            this.memberId = memberId;
            this.bookTitle = bookTitle;
            this.amount = amount;
            this.issuedDate = issuedDate;
            this.paidDate = paidDate;
            this.status = status;
        }


        public int getId() { return id; }
        public int getLoanId() { return loanId; }
        public int getMemberId() {
            return memberId;
        }
        public double getAmount() { return amount; }
        public String getBookTitle() { return bookTitle;}
        public LocalDate getIssuedDate() { return issuedDate; }
        public LocalDate getPaidDate() { return paidDate; }
        public String getStatus() { return status; }


        public void setStatus(String status) {
            this.status = status;
        }

        public void setPaidDate(LocalDate paidDate) {
            this.paidDate = paidDate;
        }


        @Override
        public String toString() {
            return  " | Member ID: " + memberId +
                    " | Book title : " + bookTitle +
                    " | Amount: " + amount +
                    " | Issued: " + issuedDate +
                    " | Status: " + status;

        }
}

