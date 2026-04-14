package book;

public class Book {

private String title;
private int id;
private int year;

public Book(String title, int id,int year) {
    this.title = title;
    this.id = id;
    this.year = year;
}

    public String getTitle() {return title;}
    public int getId() {return id;}
    public int getYear() {return year;}



    @Override
    public String toString() {
        return id + " | " + title + " | " + year;
    }

}
