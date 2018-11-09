package model.action.userActions;

import model.DAO.CarDAOImpl;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.CarEntity;
import model.entity.OrderEntity;
import model.entity.UserEntity;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        HttpSession httpSession = request.getSession();
        UserEntity user = (UserEntity) httpSession.getAttribute("authorizedUser");
        String view;
        if(user != null) {
            long userId = user.getId();
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            List<OrderEntity> listOrders = orderDAO.getByUserId(userId);
            long totalTime = getTotalTime(listOrders);
            request.setAttribute("totalTime", totalTime);
            request.setAttribute("orders", listOrders);
            changeCarStatus(userId);
            for(OrderEntity order : listOrders){
                long orderId = order.getId();
                long idUser = order.getUserId();
                if(idUser == userId){
                    orderDAO.changeStatus(OrderStatus.IS_DONE.getId(), orderId);
                }
            }
            request.setAttribute("success", "Машина успешно возвращена");
            view = "/view/jsp/carReturn.jsp";
        } else {
            request.setAttribute("error", "Ошибка возвращения машины");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }

    private void changeCarStatus(long userId){
        CarDAOImpl carDAO = new CarDAOImpl();
        CarEntity car = carDAO.getFromOrder(userId);
        long carId = car.getId();
        carDAO.changeStatus(CarStatus.IS_FREE.getId(), carId);
    }

    private long getTotalTime(List<OrderEntity> listOrders){
        OrderEntity order = listOrders.get(listOrders.size() - 1); //Get the Last element of List
        return (System.currentTimeMillis() - order.getDate().getTime())/60000; //Convert Millis to Minute
    }
}
