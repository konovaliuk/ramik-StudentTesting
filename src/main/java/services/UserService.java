package services;

import beans.User;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.intefaces.UserDao;
import dao.intefaces.UsersAnswersDao;

import java.sql.Connection;
import java.util.List;

public class UserService {

    private UserDao userDao = DaoFactory.getUserDao();
    private UsersAnswersDao usersAnswersDao = DaoFactory.getUsersAnswersDao();

    public boolean checkUser(String email) {
        Connection connection = ConnectionPool.getConnection(true);
        List<User> userList = userDao.getAll(connection);
        for (User users : userList) {
            if (email.equals(users.getEmail())) {
                return true;

            }
        }
        return false;
    }


        public int createUser (String email, String password, String firstName, String lastName ){
            Connection connection = ConnectionPool.getConnection(true);
            int newUserId = userDao.create(email, password, firstName, lastName, connection);
            return newUserId;
        }

        public User readUser(int id){
        Connection connection = ConnectionPool.getConnection(true);
        User user = userDao.read(id, connection);
        return user;
        }


}
