package model.action.userActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.Car;
import model.entity.Order;
import model.entity.User;
import model.entity.enumeration.CarStatus;
import model.entity.enumeration.OrderStatus;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class CarReturner implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            SQLException{
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("authorizedUser");
        String view;
        if(user != null) {
            long userId = user.getId();
            OrderDAO orderDAO = new OrderDAOImpl();
            List<Order> listOrders = orderDAO.getByUserId(userId);
            try {
                request.setAttribute("orders", listOrders);
                long totalTime = getTotalTime(listOrders);
                request.setAttribute("totalTime", totalTime);
                for (Order order : listOrders) {
                    if (order.getUserId() == userId) {
                        orderDAO.changeStatus(OrderStatus.IS_DONE.getId(), order.getId());
                    }
                }
                changeCarStatus(userId);
                request.setAttribute("success", "Машина успешно возвращена");
                view = "/view/jsp/carReturn.jsp";
            } catch(IndexOutOfBoundsException e) {
                request.setAttribute("message", "У пользователя нет заказов");
                view = "/view/jsp/carReturn.jsp";
            }
        } else {
            request.setAttribute("error", "Ошибка возвращения машины");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }

    private void changeCarStatus(long userId){
        CarDAO carDAO = new CarDAOImpl();
        Car car = carDAO.getFromOrder(userId);
        carDAO.changeStatus(CarStatus.IS_FREE.getId(), car.getId());
    }

    private long getTotalTime(List<Order> listOrders){
        Order order = listOrders.get(listOrders.size() - 1); //Get the Last element of List
        return (System.currentTimeMillis() - order.getDate().getTime())/60000; //Convert Millis to Minute
    }
}
