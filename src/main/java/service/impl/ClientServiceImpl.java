package service.impl;

import classes.User;
import dao.UserDao;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import service.ClientService;
import service.exception.ServiceException;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final UserDao userDao = DAOFactory.getInstance().getUserDAO();

    /**
     * Checks if pair of login and password exists
     * @param user user
     * @return true if pair of login and password exists, else false
     * @throws ServiceException default
     */
    private boolean isPasswordCorrect(User user) throws ServiceException {
        try {
            List<User> users = userDao.getUsers();
            User comparedUser = users.stream()
                    .filter(u -> u.getLogin().equals(user.getLogin()))
                    .findFirst().orElse(null);
            return (comparedUser == null) ? false : comparedUser.getPassword().equals(user.getPassword());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Checks if passed login is unique
     * @param user user
     * @return true if passed login is unique, else false
     * @throws ServiceException default
     */
    private boolean isLoginUnique(User user) throws ServiceException {
        try {
            List<User> users = userDao.getUsers();
            return users.stream().noneMatch(u -> u.getLogin().equals(user.getLogin()));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Checks entering param and calls DAO function to sign user in
     * @param user existing user
     * @return existing user, if he signed in successfully
     * @throws ServiceException default
     */
    @Override
    public User signIn(User user) throws ServiceException {
        if (user.getLogin().equals("")) {
            throw new ServiceException("Login can't be empty");
        } else {
            try {
                if (isPasswordCorrect(user)) {
                    return userDao.signIn(user);
                } else {
                    return null;
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    /**
     * Checks entering param and calls DAO function to sign user up
     * @param user new user
     * @return new user, if he signed up successfully
     * @throws ServiceException default
     */
    @Override
    public User signUp(User user) throws ServiceException {
        if (user.getLogin().equals("") || user.getPassword().equals("") || user.getFirstName().equals("") ||
                user.getLastName().equals("")) {
            throw new ServiceException("Fields can't be empty");
        } else {
            try {
                if (isLoginUnique(user)) {
                    return userDao.signUp(user);
                } else {
                    return null;
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    /**
     * Calls DAO function to get user by login
     * @param login login
     * @return user by login
     * @throws ServiceException default
     */
    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            User user = userDao.getUserByLogin(login);
            if (user.getId() == -1) {
                throw new ServiceException("No such user with specified login");
            } else {
                return user;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
