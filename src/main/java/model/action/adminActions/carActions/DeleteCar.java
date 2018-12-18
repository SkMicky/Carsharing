package model.action.adminActions.carActions;

import model.DAO.CarDAO;
import model.DAO.CarDAOImpl;
import model.action.Action;
import model.entity.enumeration.CarStatus;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeleteCar implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        String view;
        int carStatus = Integer.parseInt(request.getParameter("status"));
        if(carStatus == CarStatus.IS_FREE.getId()) {
            CarDAO carDAO = new CarDAOImpl();
            carDAO.remove(Long.parseLong(request.getParameter("carId")));
            request.setAttribute("success", "Машина успешно удалена");
            view = "/view/jsp/success.jsp";
        } else {
            request.setAttribute("error", "Попробуйте позднее");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }
}
