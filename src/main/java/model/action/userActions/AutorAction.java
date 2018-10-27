package model.action.userActions;

import model.DAO.UserDAOImpl;
import model.action.Action;
import model.action.Encryptor;
import model.entity.UserEntity;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AutorAction implements Action {

    private UserEntity getUser(String login) {
        UserDAOImpl userDAO = new UserDAOImpl();
        UserEntity user = userDAO.getByLogin(login);
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
                UserEntity user = getUser(login);
                httpSession.setAttribute("User", user);
                view = "/view/UserPage.jsp";
            } else {
                request.setAttribute("FalseLoginOrPassword","Неверный логин или пароль");
                view = "/view/Error.jsp";
                System.out.println("Неверный логин или пароль");
            }
        }
        else {
            request.setAttribute("IncorrectLoginOrPassword","Неккоректные данные");
            view="/view/Error.jsp";
            System.out.println("Некорректные данные");
        }
        return view;
    }

    private boolean checkLoginAndPassword(String login, String password){
        Encryptor encryptor = new Encryptor();
        boolean flag = true;
        try {
            String hashPassword = encryptor.getHashPassword(password);
            UserDAOImpl userDAO = new UserDAOImpl();
            UserEntity user = new UserEntity();
            user = userDAO.searchInDataBase(login, hashPassword);
            if (user.getLogin() == null) {
                flag = false;
            }
        } catch(LoginException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return flag;
    }
}
