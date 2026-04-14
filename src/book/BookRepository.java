package book;
import java.sql.*;
import java.util.ArrayList;


public class BookRepository {
    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";



public ArrayList<Book> getAllBooks() {

    ArrayList<Book> books = new ArrayList<>();

try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
     Statement stmt = conn.createStatement()) {

    ResultSet rs = stmt.executeQuery("""

SELECT b.id, b.title, b.isbn, b.year_published, b.total_copies, b.available_copies,

GROUP_CONCAT(DISTINCT CONCAT (a.first_name, ' ', a.last_name) SEPARATOR ', ') AS authors,
GROUP_CONCAT(DISTINCT c.name SEPARATOR ', ') AS categories,
    bd.language,
    bd.summary,
    bd.page_count

    FROM books b

LEFT JOIN book_descriptions bd ON b.id = bd.book_id

LEFT JOIN book_authors ba ON b.id = ba.book_id
LEFT JOIN authors a ON ba.author_id = a.id

LEFT JOIN book_categories bc ON b.id = bc.book_id
LEFT JOIN categories c ON bc.category_id = c.id

GROUP BY b.id;

""");

    while (rs.next()) {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        int year = rs.getInt("year_published");

        Book book = new Book(title, id, year);
        books.add(book);
    }

    } catch (SQLException e) {
    System.out.println("Fel: " + e.getMessage());
}

return books;



}



}


