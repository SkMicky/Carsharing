package model.action.userActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.Order;
import model.entity.User;
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
        User user = (User) httpSession.getAttribute("authorizedUser");
        String view;
        if(user != null){
            bookCar(Long.parseLong(request.getParameter("carId")), user.getId());
            view = "/view/jsp/userPage.jsp";
        }else{
            view = "/view/jsp/authorization.jsp";
        }
        return view;
    }

    private void bookCar(long carId, long userId){
        Order order = new Order();
        order.setCarId(carId);
        order.setUserId(userId);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setStatus(OrderStatus.IS_EXECUTING);
        OrderDAO orderDAO = new OrderDAOImpl();
        orderDAO.saveOrUpdate(order);
        CarDAO carDAO = new CarDAOImpl();
        carDAO.changeStatus(CarStatus.IN_RENT.getId(), carId);
    }
}
