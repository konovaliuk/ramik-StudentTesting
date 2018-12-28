package dao.intefaces;

import beans.AnswerOption;

import java.sql.Connection;
import java.util.List;


public interface AnswerOptionsDao {
    int create(Integer questionId, String text, int isCorrect, Connection connection);
    AnswerOption read(int id, Connection connection);
    void update(int id, Integer questionId, String text, int isCorrect, Connection connection);
    void delete(AnswerOption answerOption, Connection connection);
    List<AnswerOption> getAll(Connection connection);

}
