package dao.impl;

import classes.Book;
import dao.BookDao;
import dao.exception.DAOException;
import dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLBookDao implements BookDao {
    /**
     * Adds book to table
     * @param book book
     * @throws DAOException default
     */
    @Override
    public void addBook(Book book) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            var sql = "INSERT INTO books (author, title, count) VALUES (?, ?, ?)";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    /**
     * Edit book from table
     * @param book book
     * @throws DAOException default
     */
    @Override
    public void editBook(Book book) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            var sql = "UPDATE books SET author=?, title=?, count=? WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getCount());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    /**
     * Delete book from table
     * @param book book
     * @throws DAOException default
     */
    @Override
    public void deleteBook(Book book) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            var sql = "DELETE FROM books WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    /**
     * Gets book from table by id
     * @param id id of book
     * @return book
     * @throws DAOException default
     */
    @Override
    public Book getBookById(int id) throws DAOException {
        var book = new Book();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            var sql = "SELECT * FROM books WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCount(rs.getInt("count"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
        return book;
    }

    /**
     * Gets all books from table
     * @return list of books
     * @throws DAOException default
     */
    @Override
    public List<Book> getBooks() throws DAOException {
        List<Book> books = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            var sql = "SELECT * FROM books";
            var statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                var book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setCount(rs.getInt("count"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
        return books;
    }

    /**
     * Searches for books containing in title or author string substring, which passed as a parameter
     * @param request substring for search
     * @return list of appropriate books
     * @throws DAOException default
     */
    @Override
    public List<Book> searchBooks(String request) throws DAOException {
        List<Book> books = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            var sql = "SELECT * FROM books WHERE title LIKE '%" + request + "%' OR author LIKE '%" + request + "%'";
            var statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                var book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setCount(rs.getInt("count"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null)
                pool.returnConnection(connection);
        }
        return books;
    }
}
