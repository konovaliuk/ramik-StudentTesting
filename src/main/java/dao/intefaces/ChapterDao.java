package dao.intefaces;

import beans.Chapter;

import java.sql.Connection;
import java.util.List;


public interface ChapterDao {
    int create(String name, Integer idTestCase, Connection connection);
    Chapter read(int id, Connection connection);
    void update(int id, String name, Integer idTestCase, Connection connection);
    void delete(Chapter chapter, Connection connection);
    List<Chapter> getAll(Connection connection);
}
