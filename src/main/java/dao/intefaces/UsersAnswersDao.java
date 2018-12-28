package dao.intefaces;

import beans.UsersAnswers;

import java.sql.Connection;
import java.util.List;

public interface UsersAnswersDao {
    
    int create(Integer idUser, Integer questionId, Integer answerId, Connection connection);
    UsersAnswers read(int id, Connection connection);
    void update(int id, Integer IdUser, Integer questionId, Integer answerId, Connection connection);
    void delete(UsersAnswers usersAnswers, Connection connection);
    List<UsersAnswers> getAll(Connection connection);
}

