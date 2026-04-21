package loan;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";


    public List<Loan> getAllMemberLoans(String memberEmail) {

List<Loan> loans = new ArrayList<>();

String sql = """

        SELECT
            b.title,
            l.id,
            l.book_id,
            l.member_id,
            l.loan_date,
            l.due_date,
            l.return_date
        FROM loans l
        JOIN books b ON l.book_id = b.id
        JOIN members m ON l.member_id = m.id
        WHERE m.email = ? AND return_date IS NULL
""";

try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
PreparedStatement stmt = conn.prepareStatement(sql)) {

    stmt.setString(1, memberEmail);

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

    public List<Loan> getAllActiveLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = """
                SELECT
                            b.title,
                            l.id,
                            l.book_id,
                            l.member_id,
                            l.loan_date,
                            l.due_date,
                            l.return_date
                        FROM loans l
                        JOIN books b ON l.book_id = b.id
                        JOIN members m ON l.member_id = m.id
                        WHERE return_date is null
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
           Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

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

    public List<Loan> getOverdueLoans() {

        List<Loan> loans = new ArrayList<>();

        String sql = """
        SELECT b.title, l.id, l.book_id, l.member_id,
               l.loan_date, l.due_date
        FROM loans l
        JOIN books b ON l.book_id = b.id
        JOIN members m ON l.member_id = m.id
        WHERE l.return_date IS NULL
        AND l.due_date < CURRENT_DATE
        """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    public List<PopularBookDTO> getPopularBooks() {

        List<PopularBookDTO> popularBooks = new ArrayList<>();

        String sql = """
                SELECT
                    b.title,
                    COUNT(l.id) AS total_loans
                FROM loans l
                JOIN books b ON l.book_id = b.id
                GROUP BY b.id, b.title
                ORDER BY total_loans DESC
                LIMIT 5
                """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                popularBooks.add(new PopularBookDTO(
                        rs.getString("title"),
                        rs.getInt("total_loans")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return popularBooks;
    }

    public void createLoan(int memberId, int bookId) {



        String sql = """
                INSERT INTO loans (book_id, member_id, loan_date, due_date, return_date)
                VALUES (?, ?, ?, ?, NULL);
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            stmt.setInt(2, memberId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setDate(4, Date.valueOf(LocalDate.now().plusWeeks(2)));

            stmt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void extendLoan(int memberId, int bookId){

        String sql = """
                UPDATE loans SET due_date = ?
                WHERE member_id = ? AND book_id = ?
                """;
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now().plusWeeks(2)));
            stmt.setInt(2, memberId);
            stmt.setInt(3, bookId);

            stmt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
    public void returnBook(int memberId, int bookId){

        String sql = """
                UPDATE loans SET return_date = ?
                WHERE member_id = ? AND book_id = ?
                """;
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, memberId);
            stmt.setInt(3, bookId);

            stmt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }


}
