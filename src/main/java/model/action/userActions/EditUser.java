package model.action.userActions;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.action.Action;
import model.entity.User;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

public class EditUser implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException {
        Validator validator = new Validator();
        String view;
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("authorizedUser");
        long userId = user.getId();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date birthday = Date.valueOf(request.getParameter("birthDate"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String iin = request.getParameter("iin");
        String address = request.getParameter("address");
        String driverLicense = request.getParameter("driverLicense");
        String login = request.getParameter("login");
        if (validator.validateLogin(login) && validator.validateEmail(email)){
            doEdit(userId, lastName, firstName, birthday, phoneNumber, email, iin, address, driverLicense, login);
            httpSession.setAttribute("authorizedUser", user);
            request.setAttribute("success","Вы успешно изменили данные");
            view = "/view/jsp/success.jsp";
        } else{
            request.setAttribute("IncorrectData","Вы ввели некорректные данные");
            view = "/view/jsp/error.jsp";
        }
        return view;
    }

    private void doEdit(long userId, String lastName, String firstName, Date birthday, String phoneNumber, String email,
                        String iin, String address, String driverLicense, String login){
        User user = new User();
        user.setId(userId);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setIin(iin);
        user.setUserAddress(address);
        user.setDriverLicense(driverLicense);
        user.setLogin(login);
        UserDAO userDAO = new UserDAOImpl();
        userDAO.saveOrUpdate(user);
    }
}
