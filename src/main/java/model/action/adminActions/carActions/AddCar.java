package model.action.adminActions.carActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.action.Action;
import model.action.userActions.Validator;
import model.entity.Car;
import model.entity.enumeration.CarStatus;
import model.entity.enumeration.Color;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AddCar implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        Validator validator = new Validator();
        String view;
        String name = request.getParameter("name");
        String gosNo = request.getParameter("gosNo");
        Color color = Color.getColors(request.getParameter("color"));
        if(validator.validateGosNo(gosNo)) {
            addCar(name, gosNo, color);
            request.setAttribute("success", "Машина успешно добавлена");
            view = "/view/jsp/success.jsp";
        } else {
            request.setAttribute("error", "Введены некорректные данные");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }

    private void addCar(String name, String gosNo, Color color){
        CarDAO carDAO = new CarDAOImpl();
        Car car = new Car();
        car.setName(name);
        car.setGosNo(gosNo);
        car.setColor(color);
        car.setStatus(CarStatus.IS_FREE);
        carDAO.saveOrUpdate(car);
    }
}
