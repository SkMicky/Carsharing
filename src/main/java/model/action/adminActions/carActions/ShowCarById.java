package model.action.adminActions.carActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.action.Action;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ShowCarById implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        CarDAO carDAO = new CarDAOImpl();
        request.setAttribute("car", carDAO.getById(Long.parseLong(request.getParameter("carId"))));
        return "/view/jsp/show/showCarBy.jsp";
    }
}
