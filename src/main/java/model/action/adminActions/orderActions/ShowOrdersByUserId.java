package model.action.adminActions.orderActions;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.Order;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class ShowOrdersByUserId implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> listOrders = orderDAO.getByCarId(Long.parseLong(request.getParameter("userId")));
        request.setAttribute("orders", listOrders);
        return "/view/jsp/show/showOrderBy.jsp";
    }
}
