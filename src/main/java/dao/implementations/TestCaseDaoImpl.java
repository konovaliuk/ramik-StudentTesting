package dao.implementations;

import beans.TestCase;
import dao.intefaces.TestCaseDao;
import exeptions.ExeptionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestCaseDaoImpl implements TestCaseDao {
    @Override
    public int create(String name, Connection connection) {
        String sql = "INSERT INTO test_case (name) VALUES (?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new ExeptionDataBase("Can't creat test case");
        }
        return id;
    }

    @Override
    public TestCase read(int id, Connection connection) {
        TestCase testCase = null;
        String sql = "SELECT * FROM test_case WHERE id_test_case=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               testCase = new TestCase.Builder()
                       .setId(resultSet.getInt("id_test_case"))
                       .setName(resultSet.getString("name"))
                       .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();

            throw new ExeptionDataBase("Can't read test case");
        }
        return testCase;
    }

    @Override
    public void update(int id, String name, Connection connection) {
        String sql = "UPDATE test_case SET name=? WHERE id_test_case=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't update test case");
        }
    }

    @Override
    public void delete(TestCase testCase, Connection connection) {
        String sql = "DELETE FROM test_case WHERE id_test_case=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, testCase.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't delete test case");
        }
    }

    @Override
    public List<TestCase> getAll(Connection connection) {
        List<TestCase> list = new ArrayList<>();
        String sql = "SELECT * FROM test_case ORDER BY id_test_case";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new TestCase.Builder()
                        .setId(resultSet.getInt("id_test_case"))
                        .setName(resultSet.getString("name"))
                        .build());
            }
        } catch (SQLException e) {

            throw new ExeptionDataBase("Can't get all test case");
        }
        return list;
    }
}
