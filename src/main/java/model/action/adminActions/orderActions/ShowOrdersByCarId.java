package model.action.adminActions.orderActions;

import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.OrderEntity;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class ShowOrdersByCarId implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        long carId = Long.parseLong(request.getParameter("carId"));
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        List<OrderEntity> listOrders = orderDAO.getByCarId(carId);
        request.setAttribute("orders", listOrders);
        return "/view/jsp/show/showOrderBy.jsp";
    }
}
