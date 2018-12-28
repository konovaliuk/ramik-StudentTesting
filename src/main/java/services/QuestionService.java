package services;

import beans.Question;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.intefaces.QuestionDao;

import java.sql.Connection;
import java.util.List;

public class QuestionService {

    private QuestionDao questionDao = DaoFactory.getQuestionDao();

    public List<Question> getAllQustion() {
        Connection connection = ConnectionPool.getConnection(true);

        List<Question> questions =questionDao.getAll(connection);

        return questions;

    }
}
