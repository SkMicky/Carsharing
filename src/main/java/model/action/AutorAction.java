package model.action;

import model.DAO.UserDAOImpl;
import model.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AutorAction implements Action {

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean outputString;
        try{
            auth(request, response);
            outputString = true;
        } catch(SQLException se){
            se.printStackTrace();
            outputString = false;
        }
        if(outputString == true){
            return "view/Success.jsp";
        }
        return "view/Error.jsp";
    }

    void auth(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String login = request.getParameter("login");

        UserEntity user = userDAO.getByLogin(login);
        long userId = user.getId();

        if (userId == 0) {
            request.getRequestDispatcher("/authError.jsp").forward(request, response);
        } else if (user.getRole().equals("admin")) {
            request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
        } else {
            request.setAttribute("id", userId);
            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        }
    }
}
