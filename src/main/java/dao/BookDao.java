package dao;

import classes.Book;
import dao.exception.DAOException;

import java.util.List;

public interface BookDao {
    void addBook(Book book) throws DAOException;
    void editBook(Book book) throws DAOException;
    void deleteBook(Book book) throws DAOException;
    Book getBookById(int id) throws DAOException;
    List<Book> getBooks() throws DAOException;
    List<Book> searchBooks(String request) throws DAOException;
}
