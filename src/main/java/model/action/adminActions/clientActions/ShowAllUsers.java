package model.action.adminActions.clientActions;

import model.DAO.UserDAOImpl;
import model.action.Action;
import model.entity.UserEntity;
import model.entity.enumeration.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllUsers implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAOImpl userDAO = new UserDAOImpl();
        List<UserEntity> listUsers = userDAO.getByRole(UserRole.CLIENT.getId());
        request.setAttribute("users", listUsers);
        return "/view/jsp/show/showAllUsers.jsp";
    }
}
