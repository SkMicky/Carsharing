package model.action.userActions;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.action.Action;
import model.action.Encryptor;
import model.entity.User;
import model.entity.enumeration.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AutorAction implements Action {

    private static final Logger LOGGER = LogManager.getLogger(AutorAction.class.getName());

    private User getUser(String login) {
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getByLogin(login);
        return user;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Validator validator = new Validator();
        String view = null;
        HttpSession httpSession = request.getSession();
        if (validator.validateLogin(login) && validator.validatePassword(password)) {
            if (checkLoginAndPassword(login, password)) {
                User user = getUser(login);
                httpSession.setAttribute("authorizedUser", user);
                if(user.getRole() == UserRole.ADMIN){
                    view ="/view/jsp/adminPage.jsp";
                } else{
                    view = "/view/jsp/userPage.jsp";
                }
            } else {
                request.setAttribute("FalseLoginOrPassword","Неверный логин или пароль");
                view = "/view/jsp/error.jsp";
            }
        }
        else {
            request.setAttribute("IncorrectLoginOrPassword","Неккоректные данные");
            view="/view/jsp/error.jsp";
        }
        return view;
    }

    private boolean checkLoginAndPassword(String login, String password){
        Encryptor encryptor = new Encryptor();
        boolean flag = true;
        try {
            String hashPassword = encryptor.getHashPassword(password);
            UserDAO userDAO = new UserDAOImpl();
            User user = new User();
            user = userDAO.searchInDataBase(login, hashPassword);
            if (user.getLogin() == null) {
                flag = false;
            }
        } catch(LoginException | NoSuchAlgorithmException e){
            LOGGER.error(e);
        }
        return flag;
    }
}
