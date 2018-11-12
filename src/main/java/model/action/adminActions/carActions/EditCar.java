package model.action.adminActions.carActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.action.Action;
import model.action.userActions.Validator;
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

public class EditCar implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        Validator validator = new Validator();
        String view;
        long carId = Long.parseLong(request.getParameter("carId"));
        String name = request.getParameter("name");
        String gosNo = request.getParameter("gosNo");
        String color = request.getParameter("color");
        int carStatusId = Integer.parseInt(request.getParameter("status"));
        CarStatus carStatus = CarStatus.getCarStatus(carStatusId);
        if (validator.validateGosNo(gosNo)){
            doEdit(carId, name, gosNo, color, carStatus);
            request.setAttribute("success","Вы успешно изменили данные");
            view = "/view/jsp/success.jsp";
        } else{
            request.setAttribute("IncorrectData","Вы ввели некорректные данные");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }

    private void doEdit(long carId, String name, String gosNo, String color, CarStatus carStatus){
        Car car = new Car();
        car.setId(carId);
        car.setName(name);
        car.setGosNo(gosNo);
        car.setColor(color);
        car.setStatus(carStatus);
        CarDAO carDAO = new CarDAOImpl();
        carDAO.saveOrUpdate(car);
    }
}
