package dao.intefaces;

import beans.TestCase;

import java.sql.Connection;
import java.util.List;

public interface TestCaseDao {
    
    int create(String name, Connection connection);
    TestCase read(int id, Connection connection);
    void update(int id, String name, Connection connection);
    void delete(TestCase testCase, Connection connection);
    List<TestCase> getAll(Connection connection);
}
