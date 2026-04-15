package loan;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";


    public List<Loan> getAllMemeberLoans(int memberId) {

List<Loan> loans = new ArrayList<>();

String sql = """

        SELECT b.title, l.id, l.book_id, l.member_id, l.loan_date, l.due_date
         FROM loans l
         JOIN books b ON l.book_id = b.id
         WHERE l.member_id = ?;
""";

try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
PreparedStatement stmt = conn.prepareStatement(sql)) {

    stmt.setInt(1, memberId);

    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        loans.add(new Loan(
                rs.getInt("id"),
                rs.getInt("book_id"),
                rs.getInt("member_id"),
                rs.getString("title"),
                rs.getDate("loan_date").toLocalDate(),
                rs.getDate("due_date").toLocalDate(),
                null
        ));

    }
} catch (SQLException e) {
    System.out.println("Error: " + e.getMessage());
}


return loans;
    }



}
