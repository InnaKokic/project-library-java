package book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {
    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";



public List<Book> getAllBooks() {

    List<Book> books = new ArrayList<>();

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
        String isbn = rs.getString("isbn");
        int year = rs.getInt("year_published");
        int totalCopies = rs.getInt("total_copies");
        int availableCopies = rs.getInt("available_copies");
        String language = rs.getString("language");
        String summary = rs.getString("summary");
        int pageCount = rs.getInt("page_count");

        Book book = new Book(title, id, year, isbn, totalCopies, availableCopies, language, summary, pageCount);
        books.add(book);
    }

    } catch (SQLException e) {
    System.out.println("Error: " + e.getMessage());
}

return books;



}


public List<Book> searchBookByTitle(String title) {
    List<Book> books = new ArrayList<>();

    String sql = """
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

WHERE b.title LIKE ?

GROUP BY b.id;
""";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + title + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            books.add(new Book(
                    rs.getString("title"),
                    rs.getInt("id"),
                    rs.getInt("year_published"),
                    rs.getString("isbn"),
                    rs.getInt("total_copies"),
                    rs.getInt("available_copies"),
                    rs.getString("language"),
                    rs.getString("summary"),
                    rs.getInt("page_count")
            ));
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return books;
}


public List<Book> searchBookByYear(int year) {
    List<Book> books = new ArrayList<>();

    String sql = """
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

WHERE b.year_published LIKE ?

GROUP BY b.id;
""";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, year);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            books.add(new Book(
                    rs.getString("title"),
                    rs.getInt("id"),
                    rs.getInt("year_published"),
                    rs.getString("isbn"),
                    rs.getInt("total_copies"),
                    rs.getInt("available_copies"),
                    rs.getString("language"),
                    rs.getString("summary"),
                    rs.getInt("page_count")
            ));
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return books;
}

}


