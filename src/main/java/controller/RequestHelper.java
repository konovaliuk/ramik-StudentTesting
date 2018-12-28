package controller;

import commands.Command;
import commands.ErrorComand;
import commands.LoginComand;
import commands.RegistationComand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public final class RequestHelper {

    private static RequestHelper comand = null;
    private HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("submit", new LoginComand());
        commands.put("error", new ErrorComand());
        commands.put("Registration", new Command() {
            @Override
            public String execute(HttpServletRequest request, HttpServletResponse response) {
                return "registration.jsp";
            }
        });
        commands.put("RegistrationNewUser", new RegistationComand());

    }

    public Command getCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commandName == null) {
            commandName = "error";
        }
        return commands.get(commandName);
    }

    public static RequestHelper getInstance() {
        if (comand == null) {
            comand = new RequestHelper();
        }
        return comand;
    }
}
