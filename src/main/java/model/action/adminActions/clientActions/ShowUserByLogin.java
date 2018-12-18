package model.action.adminActions.clientActions;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.action.Action;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ShowUserByLogin implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        UserDAO userDAO = new UserDAOImpl();
        request.setAttribute("user", userDAO.getByLogin(request.getParameter("login")));
        return "/view/jsp/show/showUserBy.jsp";
    }
}