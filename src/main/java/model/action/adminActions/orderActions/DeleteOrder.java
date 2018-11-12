package model.action.adminActions.orderActions;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.action.Action;
import model.entity.enumeration.OrderStatus;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeleteOrder implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        String view;
        int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
        if(orderStatus == OrderStatus.IS_DONE.getId()){
            OrderDAO orderDAO = new OrderDAOImpl();
            orderDAO.remove(Long.parseLong(request.getParameter("orderId")));
            request.setAttribute("success", "Заказ успешно удалён");
            view = "/view/jsp/success.jsp";
        } else {
            request.setAttribute("error", "Этот заказ ещё не закончен, попробуйте позже");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }
}
