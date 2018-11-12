package model.action.adminActions.clientActions;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.action.Action;
import model.entity.Order;
import model.entity.enumeration.OrderStatus;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class DeleteUser implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        String view;
        long userId = Long.parseLong(request.getParameter("userId"));
        if(deleteOrders(userId)) {
            UserDAO userDAO = new UserDAOImpl();
            userDAO.remove(userId);
            request.setAttribute("success", "Пользователь успешно удалён");
            view = "/view/jsp/success.jsp";
        } else{
            request.setAttribute("error", "У пользователя ещё есть невыполненный заказ. Попробуйте позднее");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }

    private boolean deleteOrders(long userId){
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> listOrdersByUser = orderDAO.getByUserId(userId);
        boolean flag = false;
        for (Order order: listOrdersByUser) {
            OrderStatus orderStatus = order.getStatus();
            if(orderStatus == OrderStatus.IS_DONE){
                orderDAO.removeByUserId(userId);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }
}
