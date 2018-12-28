package dao.intefaces;

import beans.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    
    int create(String email, String password, String firstName, String lastName, Connection connection);
    User read(int id, Connection connection);
    void update(int id, String email, String password, String firstName, String lastName, Connection connection);
    void delete(User user, Connection connection);
    List<User> getAll(Connection connection);
}
