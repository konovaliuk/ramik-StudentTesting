package dao;

import dao.implementations.*;
import dao.intefaces.*;

public class DaoFactory {

    public static AnswerOptionsDao getAnswerOptionsDao(){
        return new AnswerOptionsDaoImpl();
    }

     public static ChapterDao getChapterDao(){
        return new ChapterDaoImpl();
    }

    public static QuestionDao getQuestionDao() {
        return new QuestionDaoImpl();
    }

    public static ResultDao getResultDao() {
        return new ResultDaoImpl();
    }

    public static TestCaseDao getTestCaseDao() {
        return new TestCaseDaoImpl();
    }

    public static UsersAnswersDao getUsersAnswersDao() {
        return new UserAnswerDaoImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }






}
