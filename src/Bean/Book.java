package Bean;

public class Book {

    private String isbn;
    private String title;
    private double prize;
    private String author;
    private int rest;
    private int count;
    public Book(String isbn, String title, double prize, String author, int rest) {
        this.isbn = isbn;
        this.title = title;
        this.prize = prize;
        this.author = author;
        this.rest = rest;
    }

    public Book(String isbn, String title, String author,double prize, int count) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.count = count;
        this.prize = prize;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getRest() {
        return rest;
    }




    public double getPrize() {
        return prize;
    }

    public String getAuthor() {
        return author;
    }


}
