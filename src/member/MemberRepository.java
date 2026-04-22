package member;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";

    public Member getMemberById(int memberId) {
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
            WHERE m.id = ?;
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("membership_date").toLocalDate(),
                        rs.getString("membership_type"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
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


            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Could not add email");
            } else {
                System.out.println("Email changed successfully.");
            }


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


            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Name not changed");
            } else {
                System.out.println("Name changed successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }
    public void editMemberStatus(String memberEmail, String memberStatus) {

        String sql = """
                UPDATE members SET status = ? WHERE email = ?
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberStatus);
            stmt.setString(2, memberEmail);



            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Could not change status");
            } else {
                System.out.println("Status changed successfully.");
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }
    public void addMember(String firstName, String lastName, String email, String memberType ) {

        String sql = """
                INSERT INTO members (
                    first_name,
                    last_name,
                    email,
                    membership_date,
                    membership_type,
                    status
                )
                VALUES (?, ?, ?, ?, ?, ?);
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.setString(5, memberType);
            stmt.setString(6, "active");


            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("New member not created");
            } else {
                System.out.println("New member created successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void suspendMember(String memberEmail) {

        String sql = """
                UPDATE members SET status = "suspended"
                WHERE email = ?
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberEmail);


            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Member not suspended");
            } else {
                System.out.println("Member suspended successfully.");
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
