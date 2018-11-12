package model.action.userActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.action.Action;
import model.entity.Car;
import model.entity.enumeration.CarStatus;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class ShowFreeCars implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        CarDAO carDAO = new CarDAOImpl();
        List<Car> listCars = carDAO.getByStatus(CarStatus.IS_FREE.getId());
        request.setAttribute("cars", listCars);
        return "/view/jsp/show/listFreeCars.jsp";
    }
}