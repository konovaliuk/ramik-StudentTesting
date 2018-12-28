package dao.implementations;

import beans.UsersAnswers;
import dao.intefaces.UsersAnswersDao;
import exeptions.ExeptionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAnswerDaoImpl implements UsersAnswersDao {
    @Override
    public int create(Integer idUser, Integer questionId, Integer answerId, Connection connection) {
        String sql = "INSERT INTO user_answers (id_user, id_question, id_Answers ) VALUES (?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, questionId);
            preparedStatement.setInt(3, answerId);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new ExeptionDataBase("Can't create user answer");
        }
        return id;
    }

    @Override
    public UsersAnswers read(int id, Connection connection) {
        UsersAnswers usersAnswers = null;
        String sql = "SELECT * FROM user_answers WHERE id_user_answer=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usersAnswers = new UsersAnswers.Builder()
                        .setId(resultSet.getInt("id_user_answer"))
                        .setUserId(resultSet.getInt("id_user"))
                        .setQestionId(resultSet.getInt("id_question"))
                        .setAnswerId(resultSet.getInt("id_Answers"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();

            throw new ExeptionDataBase("Can't read user answer");
        }
        return usersAnswers;
    }

    @Override
    public void update(int id, Integer idUser, Integer questionId, Integer answerId, Connection connection) {
        String sql = "UPDATE user_answers SET id_user_answer=?, id_user=?, id_question=?, id_Answers=? WHERE id_user_answer=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, questionId);
            preparedStatement.setInt(3, answerId);
           ;
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't update user answer");
        }
    }

    @Override
    public void delete(UsersAnswers usersAnswers, Connection connection) {
        String sql = "DELETE FROM user_answers WHERE id_user_answer=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, usersAnswers.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't update user answer");
        }
    }

    @Override
    public List<UsersAnswers> getAll(Connection connection) {
        List<UsersAnswers> list = new ArrayList<>();
        String sql = "SELECT * FROM user_answers ORDER BY id_user_answer";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new UsersAnswers.Builder()
                        .setId(resultSet.getInt("id"))
                        .setUserId(resultSet.getInt("id_user"))
                        .setQestionId(resultSet.getInt("id_question"))
                        .setAnswerId(resultSet.getInt("id_Answers"))
                        .build());
            }
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't get all user answer");
        }
        return list;
    }
}
