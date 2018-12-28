package services;

import beans.User;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.intefaces.UserDao;
import exeptions.ExeptionDataBase;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.util.List;

public class LoginService {

    private UserDao userDao = DaoFactory.getUserDao();


    public User findUserByEmail(String email) {
        Connection connection = ConnectionPool.getConnection(true);
        User user = null;
        List<User> userList = userDao.getAll(connection);
        for (User users: userList) {
            if (email.equals(users.getEmail())) {
                return users;
            }
        }
        return null;

    }

    public List<User> getAllUsers(){
        Connection connection = ConnectionPool.getConnection(true);
        List<User> users = userDao.getAll(connection);
        return users;
    }
}
