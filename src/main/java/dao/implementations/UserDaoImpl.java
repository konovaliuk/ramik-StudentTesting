package dao.implementations;

import beans.User;
import dao.intefaces.UserDao;
import exeptions.ExeptionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    
    @Override
    public int create(String email, String password, String firstName, String lastName, Connection connection) {
        String sql = "INSERT INTO users (email, user_password, first_name, last_name) VALUES (?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new ExeptionDataBase("Can't create user");
        }
        return id;   
    }

    @Override
    public User read(int id, Connection connection) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE id_user=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("user_password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(resultSet.getString("role"));
                
            }
        } catch (SQLException e) {
            throw new ExeptionDataBase("Can't read user");
        }
        return user;
    }

    @Override
    public void update(int id, String email, String password, String firstName, String lastName, Connection connection) {
     String sql = "UPDATE users SET email=?, user_password=?, first_name=?, last_name=? WHERE id_user=?";
          
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //logger.error("Can't update user in DB", e.getCause());
            throw new ExeptionDataBase("Can't update user");
        }
    }


    


    @Override
    public void delete(User user, Connection connection) {
        String sql = "DELETE FROM users WHERE id_user=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExeptionDataBase("Can't delete user");
        }
    }

    @Override
    public List<User> getAll(Connection connection) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id_user";
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new User.Builder()
                        .setId(resultSet.getInt("id_user"))
                        .setEmail(resultSet.getString("email"))
                        .setPassword(resultSet.getString("user_password"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setRole(resultSet.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            throw new ExeptionDataBase("Can't get all user");
        }
        return list;

    }

   
}
