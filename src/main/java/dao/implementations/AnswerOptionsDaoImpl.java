package dao.implementations;

import beans.AnswerOption;
import dao.intefaces.AnswerOptionsDao;
import exeptions.ExeptionDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AnswerOptionsDaoImpl implements AnswerOptionsDao {

    private static final Logger logger= LogManager.getLogger(AnswerOptionsDaoImpl.class);

    @Override
    public int create(Integer questionId, String text, int isCorrect, Connection connection) {
        String sql = "INSERT INTO answers_options (id_question, text_of_answer, is_correct) VALUES (?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, questionId);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, isCorrect);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("can't create DB answer option ", e.getCause());
            throw new ExeptionDataBase("Can't create answer option");
        }
        return id;
    }

    @Override
    public AnswerOption read(int id, Connection connection) {
        AnswerOption answerOption = null;
        String sql = "SELECT * FROM answers_options WHERE id_answer=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                answerOption = new AnswerOption.Builder()
                        .setId(resultSet.getInt("id_answer"))
                        .setQuestionId(resultSet.getInt("id_question"))
                        .setText(resultSet.getString("text_of_answer"))
                        .setIsCorrect(resultSet.getInt("is_correct"))
                        .build();
            }

        } catch (SQLException e) {
            logger.error("can't read DB answer option", e.getCause());
            throw new ExeptionDataBase("Can't read answer option");

        }
        return answerOption;
    }

    @Override
    public void update(int id, Integer questionId, String text, int isCorrect, Connection connection) {
        String sql = "UPDATE answers_options SET id_question=?, text_of_answer=?, is_correct=? WHERE id_answer=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, questionId);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, isCorrect);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("can't update DB answer option", e.getCause());
            throw new ExeptionDataBase("Can't update answer option");
        }
    }

    @Override
    public void delete(AnswerOption answerOption, Connection connection) {
        String sql = "DELETE FROM answers_options WHERE id_answer=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, answerOption.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("can't delete DB answer option", e.getCause());
            throw new ExeptionDataBase("Can't delete answer option");
        }
    }

    @Override
    public List<AnswerOption> getAll(Connection connection) {
        List<AnswerOption> list = new ArrayList<>();
        String sql = "SELECT * FROM answers_options ORDER BY id_answer";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new AnswerOption.Builder()
                        .setId(resultSet.getInt("id_answer"))
                        .setQuestionId(resultSet.getInt("id_question"))
                        .setText(resultSet.getString("text_of_answer"))
                        .setIsCorrect(resultSet.getInt("is_correct"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("can't get all DB answer option", e.getCause());
            throw new ExeptionDataBase("Can't get all answer option");
        }
        return list;
    }


    
}
