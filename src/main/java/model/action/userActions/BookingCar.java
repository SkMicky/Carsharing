package model.action.userActions;

import model.DAO.CarDAOImpl;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.OrderEntity;
import model.entity.UserEntity;
import model.entity.enumeration.CarStatus;
import model.entity.enumeration.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

public class BookingCar implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        UserEntity user = (UserEntity) httpSession.getAttribute("authorizedUser");
        String view;
        if(user != null){
            long carId = Long.parseLong(request.getParameter("carId"));
            long userId = user.getId();
            bookCar(carId, userId);
            view = "/view/jsp/userPage.jsp";
        }else{
            view = "/view/jsp/authorization.jsp";
        }
        return view;
    }

    private void bookCar(long carId, long userId){
        OrderEntity order = new OrderEntity();
        order.setCarId(carId);
        order.setUserId(userId);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setStatus(OrderStatus.IS_EXECUTING);
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        orderDAO.saveOrUpdate(order);
        CarDAOImpl carDAO = new CarDAOImpl();
        carDAO.changeStatus(CarStatus.IN_RENT.getId(), carId);
    }
}
