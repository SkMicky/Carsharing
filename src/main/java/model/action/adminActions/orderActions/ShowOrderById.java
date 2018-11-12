package model.action.adminActions.orderActions;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.action.Action;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ShowOrderById implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        OrderDAO orderDAO = new OrderDAOImpl();
        request.setAttribute("order", orderDAO.getById(Long.parseLong(request.getParameter("orderId"))));
        return "/view/jsp/show/showOrderBy.jsp";
    }
}
