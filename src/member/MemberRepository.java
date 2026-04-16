package member;

import loan.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";

    public List<Member> showMemberProfile(String memberEmail) {

        List<Member> members = new ArrayList<>();

        String sql = """
                SELECT
                    m.id,
                    m.first_name,
                    m.last_name,
                    m.email,
                    m.membership_date,
                    m.membership_type,
                    m.status
                
                FROM members m
                
                WHERE m.email = ?;
                """;
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberEmail);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("membership_date").toLocalDate(),
                        rs.getString("membership_type"),
                        rs.getString("status")

                ));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

return members;
    }

    public void editProfileEmail(String newEmail, String memberEmail) {

        String sql = """
                UPDATE members SET email = ? WHERE email = ?
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newEmail);
            stmt.setString(2, memberEmail);


            stmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }
    public void editProfileName(String newFirstName, String newLastName, String memberEmail) {

        String sql = """
                UPDATE members SET first_name = ?, last_name = ? WHERE email = ?
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setString(3, memberEmail);


            stmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }

}
