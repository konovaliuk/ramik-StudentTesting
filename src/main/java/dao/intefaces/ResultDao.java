package dao.intefaces;

import beans.Result;

import java.sql.Connection;
import java.util.List;

public interface ResultDao {

    int create(Integer userId, Integer idChapter, String mark, Connection connection);
    Result read(int id, Connection connection);
    void update(int id, Integer userId, Integer idChapter, String mark, Connection connection);
    void delete(Result result, Connection connection);
    List<Result> getAll(Connection connection);
}

