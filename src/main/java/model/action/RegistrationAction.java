package model.action;

import model.DAO.UserDAOImpl;
import model.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RegistrationAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        Validator validator = new Validator();
        if(validator.validateLogin(request.getParameter("login"))){
            if(checkUser(request.getParameter("firstName"), request.getParameter("lastName"),
                    Date.valueOf(request.getParameter("birthday")), request.getParameter("phoneNumber"),
                    request.getParameter("email"), request.getIntHeader("iin"),
                    request.getParameter("userAddress"), request.getIntHeader("driverLicense"),
                    request.getParameter("login"), request.getParameter("password"))){
                return "view/Success.jsp";
            } else{
                return "view/Error.jsp";
            }
        }
        return "view/Error.jsp";
    }

    private boolean checkUser(String firstName, String lastName, Date birthday,
                             String phoneNumber, String email, int iin, String userAddress, int drverLicense,
                             String login, String password){
        UserDAOImpl userDAO = new UserDAOImpl();
        for(UserEntity user : userDAO.getAll()){
            if(login.equals(user.getLogin())){
                return false;
            } else if (email.equals(user.getEmail())){
                return false;
            } else if (phoneNumber.equals(user.getPhoneNumber())){
                return false;
            }
        }
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setFirstName(lastName);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setIIN(iin);
        user.setUserAddress(userAddress);
        user.setDriverLicense(drverLicense);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole("Client");
        userDAO.saveOrUpdate(user);
        return true;
    }
}