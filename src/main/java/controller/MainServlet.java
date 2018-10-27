package controller;

import model.action.Action;
import model.action.ActionFactory;
import model.action.URIAction;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Action action = new URIAction().getAction(request);
        System.out.println(new URIAction().getAction(request));
        try {
            String view = action.execute(request, response);
            request.getRequestDispatcher(view).forward(request, response);
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void init()throws ServletException{
        super.init();
    }
}
