package book;

public class Book {

private String title;
private int id;
private int year;
private int availableCopies;
private String language;
private String summary;
private int pageCount;

public Book(String title, int id, int year,String isbn, int totalCopies, int availableCopies,
            String language, String summary, int pageCount) {
    this.title = title;
    this.year = year;
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



    @Override
    public String toString() {
        return title + " | " + "Year: " + year + " | " + "Language: " + language +
                " | " + "Pages: " + pageCount + " | " + "Summary: " + summary + " | " +
                "Copies in stock: " + "[" + availableCopies + "]";
    }

}
