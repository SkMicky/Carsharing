package model.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LanguageChanger implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, SQLException {
        ServletContext servletContext = request.getServletContext();
        String lang = request.getParameter("lang");
        servletContext.setAttribute("currentLang", lang);
        HttpSession session = request.getSession();
        session.setAttribute("langLocale", lang);
        return "index.jsp";
    }
}
