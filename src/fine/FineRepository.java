package fine;

import member.Member;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FineRepository {

    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";

    public List<Fine> showMemberFines(String memberEmail) {
        List<Fine> fines = new ArrayList<>();

        String sql = """
                SELECT
                    f.id,
                    f.loan_id,
                    m.id AS member_id,
                    b.title AS book_title,
                    f.amount,
                    f.issued_date,
                    f.paid_date,
                    f.status
                FROM fines f
                JOIN loans l ON f.loan_id = l.id
                JOIN members m ON l.member_id = m.id
                JOIN books b ON l.book_id = b.id
                WHERE m.email = ?
                AND f.status = 'pending';
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberEmail);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                fines.add(new Fine(
                        rs.getInt("id"),
                        rs.getInt("loan_id"),
                        rs.getInt("member_id"),
                        rs.getString("book_title"),
                        rs.getDouble("amount"),
                        rs.getDate("issued_date").toLocalDate(),
                        null,
                        rs.getString("status")
                ));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return fines;

    }


 }
