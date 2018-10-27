package model.action.userActions;

import model.DAO.CarDAOImpl;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.CarEntity;
import model.entity.OrderEntity;
import model.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.GregorianCalendar;

public class BookingCar implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        UserEntity user = (UserEntity) httpSession.getAttribute("User");
        String view;
        if(user != null){
            long carId = Long.parseLong(request.getParameter("carId"));
            long userId = user.getId();
            bookCar(carId, userId);
            view = "/userPage.jsp";
        }else{
            view = "view/authorization.jsp";
        }
        return view;
    }

    public void bookCar(long carId, long userId){
        CarEntity car = new CarEntity();
        if(car.getStatus() == 2) {
            OrderEntity order = new OrderEntity();
            order.setCarId(carId);
            order.setUserId(userId);
            order.setDate(new GregorianCalendar().getTime());
            CarDAOImpl carDAO = new CarDAOImpl();
            carDAO.changeStatus(carId, 1);
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            orderDAO.saveOrUpdate(order);
        } else{
            System.out.println("This car isn't free");
        }
    }
}
