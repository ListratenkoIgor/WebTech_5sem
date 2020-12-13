package service;

import classes.User;

import service.exception.ServiceException;

public interface ClientService {
    User signIn(User user) throws ServiceException;
    User signUp(User user) throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
}
