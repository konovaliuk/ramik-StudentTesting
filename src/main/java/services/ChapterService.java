package services;

import beans.Chapter;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.intefaces.ChapterDao;

import java.sql.Connection;
import java.util.List;

public class ChapterService {
    private ChapterDao chapterDao = DaoFactory.getChapterDao();

    public List<Chapter> getAllChapters() {
        Connection connection = ConnectionPool.getConnection(true);

        List<Chapter> chapters = chapterDao.getAll(connection);
        return chapters;

    }
}
