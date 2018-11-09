package model.action.adminActions.carActions;

import model.DAO.CarDAOImpl;
import model.action.Action;
import model.entity.CarEntity;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class ShowCarsByColor implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        String color = request.getParameter("color");
        CarDAOImpl carDAO = new CarDAOImpl();
        List<CarEntity> listCars = carDAO.getByColor(color);
        request.setAttribute("car", listCars);
        return "/view/jsp/show/showAllCars.jsp";
    }
}
