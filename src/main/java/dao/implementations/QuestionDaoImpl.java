package dao.implementations;

import beans.Question;
import dao.intefaces.QuestionDao;
import exeptions.ExeptionDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private static final Logger logger= LogManager.getLogger(AnswerOptionsDaoImpl.class);

    @Override
    public int create(String text, Integer chapterId, Connection connection) {
        String sql = "INSERT INTO questions (question_text, id_chapter) VALUES (?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, chapterId);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("can't create DB option", e.getCause());
            throw new ExeptionDataBase("Can't create options");
        }
        return id;
    }

    @Override
    public Question read(int id, Connection connection) {
        Question question = null;
        String sql = "SELECT * FROM questions WHERE id_question=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                question = new Question.Builder()
                        .setId(resultSet.getInt("id_question"))
                        .setText(resultSet.getString("question_text"))
                        .setChapterId(resultSet.getInt("id_chapter"))
                        .build();
            }

        } catch (SQLException e) {
            logger.error("can't read DB option", e.getCause());
            throw new ExeptionDataBase("Can't read options");
        }
        return question;
    }

    @Override
    public void update(int id, String text, Integer chapterId, Connection connection) {
        String sql = "UPDATE questions SET question_text=?, id_chapter=? WHERE id_question=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, chapterId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("can't update DB option", e.getCause());
            throw new ExeptionDataBase("Can't update options");
        }
    }


    @Override
    public void delete(Question question, Connection connection) {
        String sql = "DELETE FROM questions WHERE id_question=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("can't delete DB option", e.getCause());
            throw new ExeptionDataBase("Can't delete options");
        }
    }

    @Override
    public List<Question> getAll(Connection connection) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions ORDER BY id_question";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Question.Builder()
                        .setId(resultSet.getInt("id_question"))
                        .setText(resultSet.getString("question_text"))
                        .setChapterId(resultSet.getInt("id_chapter"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("can't get all DB option", e.getCause());
            throw new ExeptionDataBase("Can't get all options");
        }
        return list;
    }
}
