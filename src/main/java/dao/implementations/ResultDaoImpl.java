package dao.implementations;

import beans.Result;
import dao.intefaces.ResultDao;
import exeptions.ExeptionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

    @Override
    public int create(Integer userId,Integer idChapter, String mark,  Connection connection) {
        String sql = "INSERT INTO results (id_Results, id_user, id_chapter) VALUES (?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, idChapter);
            preparedStatement.setString(3, mark);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't create result");
        }
        return id;
    }


    @Override
    public Result read(int id, Connection connection) {
        Result result = null;
        String sql = "SELECT * FROM results WHERE id_Results=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new Result.Builder()
                        .setId(resultSet.getInt("id_Results"))
                        .setUserId(resultSet.getInt("id_user"))
                        .setIdChapter(resultSet.getInt("id_chapter"))
                        .setMark(resultSet.getString("mark"))
                        .build();
            }

        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't read result");
        }
        return result;
    }

    @Override
    public void update(int id, Integer userId,Integer idChapter, String mark,  Connection connection) {
        String sql = "UPDATE results SET id_user=?, id_chapter=?, mark=? WHERE id_Results=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, idChapter);
            preparedStatement.setString(3, mark);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't update result");
        }
    }

    @Override
    public void delete(Result result, Connection connection) {
        String sql = "DELETE FROM results WHERE id_Results=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, result.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't delete result");
        }
    }

    @Override
    public List<Result> getAll(Connection connection) {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM results ORDER BY id_Results";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Result.Builder()
                        .setId(resultSet.getInt("id_Results"))
                        .setUserId(resultSet.getInt("id_user"))
                        .setIdChapter((resultSet.getInt("id_chapter")))
                        .setMark(resultSet.getString("mark"))
                        .build());
            }
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't get all result");
        }
        return list;
    }
}
