package book;

public class Book {

private String title;
private int id;
private String authors;
private int year;
private String isbn;
private int totalCopies;
private int availableCopies;
private String language;
private String summary;
private int pageCount;

public Book(String title, int id, String authors, int year,String isbn, int totalCopies,
            int availableCopies,
            String language, String summary, int pageCount) {
    this.title = title;
    this.id = id;
    this.authors = authors;
    this.year = year;
    this.isbn = isbn;
    this.totalCopies = totalCopies;
    this.availableCopies = availableCopies;
    this.language = language;
    this.summary = summary;
    this.pageCount = pageCount;
}

    public int getAvailableCopies() { return availableCopies;}
    public String getLanguage() {return language;}
    public String getSummary() {return summary;}
    public int getPageCount() { return pageCount;}
    public String getTitle() {return title;}
    public int getId() {return id;}
    public int getYear() {return year;}
    public String getIsbn() { return isbn;}
    public String getAuthors() { return authors; }
    public int getTotalCopies() {return totalCopies;}

    @Override
    public String toString() {
        return
                        title +
                        " | " + "Year: " + year +
                        " | " + "Language: " + language +
                        " | " + "Pages: " + pageCount +
                        " | " + "Summary: " + summary +
                        " | " + "Copies in stock: " + "[" + availableCopies + "]";
    }

}
