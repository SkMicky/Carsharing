package model.action.userActions;

import model.DAO.UserDAOImpl;
import model.action.Action;
import model.action.Encryptor;
import model.entity.UserEntity;

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

    private UserEntity getInform(String login) throws SQLException, ClassNotFoundException {
        UserDAOImpl userDAO = new UserDAOImpl();
        UserEntity user=userDAO.getByLogin(login);
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
        long iin = Long.parseLong(request.getParameter("iin"));
        String address = request.getParameter("address");
        long driverLicense = Long.parseLong(request.getParameter("driverLicense"));
        String password = request.getParameter("password");
        if(validator.validateLogin(login) && validator.validatePassword(password) && validator.validateEmail(email)){
            if(checkUserDataBase(login)){
               doRegistration(firstName, lastName, birthday, phoneNumber, email, iin,
                    address, driverLicense, login, password);
                HttpSession httpSession=request.getSession();
                httpSession.setAttribute("User",getInform(login));
                request.setAttribute("Succes","Вы зарегистрированы");
                view = "/view/Success.jsp";
            } else {
                request.setAttribute("RegistrationError", "Такой логин уже есть");
                System.out.println("Такой логин уже есть");
                view = "/view/Error.jsp";
            }
        } else {
            request.setAttribute("IncorrectData","Вы ввели некорректные данные");
            System.out.println("Некорректные данные");
            view = "/view/Error.jsp";
          }
        return view;
    }

    private boolean checkUserDataBase(String login) throws SQLException, ClassNotFoundException {
        UserDAOImpl userDAO=new UserDAOImpl();
        UserEntity user = userDAO.getByLogin(login);
        boolean checkLogin=false;
        if (user.getLogin()==null){
            checkLogin=true;
        }
        return checkLogin;
    }

    private void doRegistration(String firstName, String lastName, Date birthday,
                             String phoneNumber, String email, long iin, String userAddress,
                             long driverLicense, String login, String password)
                                throws NoSuchAlgorithmException, LoginException {
        Encryptor encryptor = new Encryptor();
        String hashPassword = encryptor.getHashPassword(password);
        UserDAOImpl userDAO = new UserDAOImpl();
        UserEntity user = new UserEntity();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setIIN(iin);
        user.setUserAddress(userAddress);
        user.setDriverLicense(driverLicense);
        user.setLogin(login);
        user.setPassword(hashPassword);
        user.setRole(2);
        userDAO.saveOrUpdate(user);
    }
}