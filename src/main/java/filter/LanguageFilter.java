package filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {
    private final static String LANG_PARAMETER = "lang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = ((HttpServletResponse) servletResponse);

        String langParameter = httpServletRequest.getParameter(LANG_PARAMETER);
        if(langParameter != null) {
            Cookie languageCookie = new Cookie(LANG_PARAMETER, langParameter);
            languageCookie.setMaxAge(60*30);
            httpServletResponse.addCookie(languageCookie);
            Config.set(httpServletRequest.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag(langParameter));
        } else {
            Cookie[] cookies = httpServletRequest.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    String cookieLangName = cookie.getName();
                    if (cookieLangName.equals(LANG_PARAMETER)) {
                        Config.set(httpServletRequest.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag(cookieLangName));
                        break;
                    }
                }
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
