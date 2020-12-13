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
