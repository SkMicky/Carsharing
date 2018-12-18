package model.action.userActions;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.action.Action;
import model.action.Encryptor;
import model.entity.User;
import model.entity.enumeration.UserRole;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

public class RegistrationAction implements Action {

    private User getInform(String login) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAOImpl();
        User user=userDAO.getByLogin(login);
        return user;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException,
            NoSuchAlgorithmException, ClassNotFoundException, LoginException{
        Validator validator = new Validator();
        String view = null;
        String login = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date birthday = Date.valueOf(request.getParameter("birthDate"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String iin = request.getParameter("iin");
        String address = request.getParameter("address");
        String driverLicense = request.getParameter("driverLicense");
        String password = request.getParameter("password");
        if(validator.validateLogin(login) && validator.validatePassword(password) && validator.validateEmail(email)){
            if(checkUserDataBase(login)){
               doRegistration(firstName, lastName, birthday, phoneNumber, email, iin,
                    address, driverLicense, login, password);
                HttpSession httpSession=request.getSession();
                httpSession.setAttribute("authorizedUser",getInform(login));
                request.setAttribute("Succes","Вы зарегистрированы");
                view = "/view/jsp/success.jsp";
            } else {
                request.setAttribute("error", "Такой логин уже есть");
                view = "/view/jsp/error.jsp";
            }
        } else {
            request.setAttribute("IncorrectData","Вы ввели некорректные данные");
            view = "/view/jsp/error.jsp";
          }
        return view;
    }

    private boolean checkUserDataBase(String login) throws SQLException, ClassNotFoundException {
        UserDAO userDAO=new UserDAOImpl();
        User user = userDAO.getByLogin(login);
        boolean checkLogin=false;
        if (user.getLogin()==null){
            checkLogin=true;
        }
        return checkLogin;
    }

    private void doRegistration(String firstName, String lastName, Date birthday,
                             String phoneNumber, String email, String iin, String userAddress,
                             String driverLicense, String login, String password)
                                throws NoSuchAlgorithmException, LoginException {
        Encryptor encryptor = new Encryptor();
        String hashPassword = encryptor.getHashPassword(password);
        UserDAO userDAO = new UserDAOImpl();
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setIin(iin);
        user.setUserAddress(userAddress);
        user.setDriverLicense(driverLicense);
        user.setLogin(login);
        user.setPassword(hashPassword);
        user.setRole(UserRole.CLIENT);
        userDAO.saveOrUpdate(user);
    }
}