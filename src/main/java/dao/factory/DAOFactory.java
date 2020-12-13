package dao.factory;

import dao.BookDao;
import dao.OrderDao;
import dao.UserDao;
import dao.impl.SQLBookDao;
import dao.impl.SQLOrderDao;
import dao.impl.SQLUserDao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final BookDao sqlBookImpl = new SQLBookDao();
    private final UserDao sqlUserImpl = new SQLUserDao();
    private final OrderDao sqlOrderImpl = new SQLOrderDao();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public BookDao getBookDAO() {
        return sqlBookImpl;
    }

    public UserDao getUserDAO() {
        return sqlUserImpl;
    }

    public OrderDao getOrderDAO() {
        return sqlOrderImpl;
    }
}
