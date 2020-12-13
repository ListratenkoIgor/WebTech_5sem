package service;

import classes.Book;
import classes.Order;
import classes.User;

import service.exception.ServiceException;

import java.util.List;

public interface LibraryService {
    boolean addNewBook(Book book) throws ServiceException;
    boolean addEditedBook(Book book) throws ServiceException;
    void deleteBook(Book book) throws ServiceException;
    List<Book> getBookList() throws ServiceException;
    List<Book> searchBooks(String request) throws ServiceException;
    Book getBookById(String id) throws ServiceException;
    void addNewOrder(Order order) throws ServiceException;
    List<Order> getOrders() throws ServiceException;
    List<Order> getUserOrders(User user) throws ServiceException;
    void deleteOrder(Order order) throws ServiceException;
    void updateOrder(Order order) throws ServiceException;
}
