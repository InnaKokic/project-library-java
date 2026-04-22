package book;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {
    private final String URL = "jdbc:mysql://localhost:3306/bibliotek";
    private final String USER = "root";
    private final String PASSWORD = "";


    public BookAvailabilityDTO getBookById(int bookId) {
        String sql = "SELECT id, title, available_copies FROM books WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new BookAvailabilityDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("available_copies")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

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
    
    GROUP BY b.id
    ORDER BY b.title;
    
    """);

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String isbn = rs.getString("isbn");
            String authors = rs.getString("authors");
            int year = rs.getInt("year_published");
            int totalCopies = rs.getInt("total_copies");
            int availableCopies = rs.getInt("available_copies");
            String language = rs.getString("language");
            String summary = rs.getString("summary");
            int pageCount = rs.getInt("page_count");

            Book book = new Book(title, id, authors, year, isbn, totalCopies, availableCopies, language,
                    summary, pageCount);
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
                        rs.getString("authors"),
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
                        rs.getString("authors"),
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

    public void addBook (String title, String isbn, int year) {
        String sql = """
    INSERT INTO books (title, isbn, year_published) VALUES (?, ?, ?)
    """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, isbn);
            stmt.setInt(3, year);

            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Could not add book.");
            } else {
                System.out.println("Book added successfully.");
            }



        } catch (SQLException e) {
            System.out.println("Could not add book " + e.getMessage());
        }

    }

    public void deleteBook(int id) {

        String sql = """
                DELETE FROM books WHERE id = ?;
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Book not found");
            } else {
                System.out.println("Book successfully deleted.");
            }


        } catch (SQLException e) {
            System.out.println("Could not delete book " + e.getMessage());
        }

    }

    public void updateBook(int id, String newTitle, String newIsbn, int newYear) {


        String sql = """
                UPDATE books SET title = ?, isbn = ?, year_published = ?
                WHERE id = ?;
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newTitle);
            stmt.setString(2, newIsbn);
            stmt.setInt(3, newYear);
            stmt.setInt(4, id);

            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Book not found");
            }


        } catch (SQLException e) {
            System.out.println("Could not update book " + e.getMessage());
        }
    }

    public void categoriesBook(int id, int categoryId) {

        String sql = """
                INSERT INTO book_categories (book_id, category_id)
                VALUES (?, ?);
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, categoryId);

            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Could not categories book");
            } else {
                System.out.println("Book successfully categorised.");
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateAuthor(int id, String firstName, String lastName, String nationality, LocalDate birthDate) {

        String sql = """
                UPDATE authors SET first_name = ?, last_name = ?, nationality = ?, birth_date = ?
                WHERE id = ?;
                """;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, nationality);
            stmt.setDate(4, Date.valueOf(birthDate));
            stmt.setInt(5, id);

            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Author not found");
            } else {
                System.out.println("Author successfully updated.");
            }


        } catch (SQLException e) {
            System.out.println("Could not update author " + e.getMessage());
        }
    }

    public void addAuthor(String firstName, String lastName, String nationality, LocalDate birthDate) {

    String sql = """
            INSERT INTO authors (first_name, last_name, nationality, birth_date) VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, nationality);
            stmt.setDate(4, Date.valueOf(birthDate));

            //IF för att se om någon ändring görs i DB och få feedback om det.
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Could not add author");
            } else {
                System.out.println("Author successfully added.");
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}


