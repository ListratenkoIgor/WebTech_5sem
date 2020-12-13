package classes;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private int id;
    private String author;
    private String title;
    private int count;

    public Book() {
        id = -1;
        author = "Noname";
        title = "Noname";
        count = -1;
    }

    public Book(int id, String author, String title, int count) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id &&
                count == book.count &&
                author.equals(book.author) &&
                title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, count);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}
