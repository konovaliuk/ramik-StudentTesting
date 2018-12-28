package commands;

import beans.User;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistationComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        UserService service = new UserService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        User user = null;
        if( !service.checkUser(email)){
            int curentUserId = service.createUser(email,password,firstName,lastName);
            user = service.readUser(curentUserId);

            request.setAttribute("user", user);

            return "userpage.jsp";

        }

        request.setAttribute("errormessage", "cant create");
        return "error.jsp";


    }
}
