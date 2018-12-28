package dao.intefaces;

import beans.Question;

import java.sql.Connection;
import java.util.List;

public interface QuestionDao {
    int create(String text, Integer chapterId, Connection connection);
    Question read(int id, Connection connection);
    void update(int id, String text, Integer chapterId, Connection connection);
    void delete(Question question, Connection connection);
    List<Question> getAll(Connection connection);
}
