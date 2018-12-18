package controller.listener;

import model.entity.enumeration.Color;
import model.entity.enumeration.Language;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Language[] langs = Language.values();
        List<Language> languageList = new ArrayList<>(Arrays.asList(langs));
        Color[] colors = Color.values();
        List<Color> colorList = new ArrayList<>(Arrays.asList(colors));
        servletContext.setAttribute("langsList", languageList);
        servletContext.setAttribute("colorsList", colorList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
