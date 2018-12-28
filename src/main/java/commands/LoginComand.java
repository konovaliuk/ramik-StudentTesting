package commands;

import beans.Chapter;
import beans.Question;
import beans.TestCase;
import beans.User;
import services.ChapterService;
import services.LoginService;
import services.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        LoginService loginService = new LoginService();
        ChapterService chapterService = new ChapterService();

        String email = request.getParameter("email");
        System.out.println(email);
        String password = request.getParameter("password");
        System.out.println(password);
        User user = null;


        user = loginService.findUserByEmail(email);
        System.out.println(user);
        if(user!=null && user.getPassword().equals(password) && user.getRole().equals("STUDENT")){
            QuestionService questionService = new QuestionService();
            List<Question> question = questionService.getAllQustion();
            List<User> users = loginService.getAllUsers();
            List<Chapter> chapters = chapterService.getAllChapters();
            request.setAttribute("question", question);
            request.setAttribute("chapter", chapters);
            request.setAttribute("user", user);
            request.setAttribute("users", users);


            return "userpage.jsp";


        } else if(user.getPassword().equals(password) && user.getRole().equals("ADMIN")) {
            QuestionService questionService = new QuestionService();
            List<User> users = loginService.getAllUsers();
            List<Question> questions = questionService.getAllQustion();
            List<Chapter> chapters = chapterService.getAllChapters();
            request.setAttribute("users", users);
            request.setAttribute("question", questions);
            request.setAttribute("chapter", chapters);


            return "adminpage.jsp";

        }else{
            request.setAttribute("errormessage", "ERROR");
            return "error.jsp";
        }

    }
}
