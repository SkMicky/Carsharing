package model.action.userActions;

import model.DAO.CarDAOImpl;
import model.DAO.UserDAOImpl;
import model.action.Action;
import model.entity.CarEntity;
import model.entity.UserEntity;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowFreeCars implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        CarDAOImpl carDAO = new CarDAOImpl();
        List<CarEntity> listCars = new ArrayList<>();
        listCars.add(carDAO.getByStatus(2));
        HttpSession session = request.getSession();
        session.setAttribute("cars", listCars);
        return "/view/listFreeCars.jsp";
    }
}
