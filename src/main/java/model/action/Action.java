package model.action;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface Action {
    String execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException, LoginException;
}
