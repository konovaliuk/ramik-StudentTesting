package dao.implementations;

import beans.Chapter;
import dao.intefaces.ChapterDao;
import exeptions.ExeptionDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDaoImpl implements ChapterDao {

    private static final Logger logger= LogManager.getLogger(AnswerOptionsDaoImpl.class);

    @Override
    public int create(String name, Integer idTestCase, Connection connection) {
        String sql = "INSERT INTO chapters (chapter_name, id_test_case) VALUES ( ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idTestCase);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("can't create DB chapter", e.getCause());
            throw new ExeptionDataBase("Can't create chapter");
        }
        return id;
    }


    @Override
    public Chapter read(int id, Connection connection) {
        Chapter chapter = null;
        String sql = "SELECT * FROM chapters WHERE id_chapter=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chapter = new Chapter.Builder()
                        .setId(resultSet.getInt("id_chapter"))
                        .setName(resultSet.getString("chapter_name"))
                        .setIdTestCase(resultSet.getInt("id_test_case"))
                        .build();
            }

        } catch (SQLException e) {
            logger.error("can't read DB chapter", e.getCause());
            throw new ExeptionDataBase("Can't read chapter");
        }
        return chapter;
    }

    @Override
    public void update(int id, String name, Integer idTestCase, Connection connection) {
        String sql = "UPDATE chapters SET chapter_name=?, id_test_case=? WHERE id_chapter=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idTestCase);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("can't update DB chapter", e.getCause());
            throw new ExeptionDataBase("Can't update chapter");
        }
    }

    @Override
    public void delete(Chapter chapter, Connection connection) {
        String sql = "DELETE FROM chapters WHERE id_chapter=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, chapter.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("can't delete DB chapter", e.getCause());
            throw new ExeptionDataBase("Can't delete chapter");
        }
    }

    @Override
    public List<Chapter> getAll(Connection connection) {
        List<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM chapters ORDER BY id_chapter";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Chapter.Builder()
                        .setId(resultSet.getInt("id_chapter"))
                        .setName(resultSet.getString("chapter_name"))
                        .setIdTestCase(resultSet.getInt("id_test_case"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("can't get all DB chapter", e.getCause());
            throw new ExeptionDataBase("Can't get all chapter");
        }
        return list;
    }
}
