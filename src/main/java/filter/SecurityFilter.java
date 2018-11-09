package filter;

import model.entity.UserEntity;
import model.entity.enumeration.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class SecurityFilter implements Filter {
    private static final String ACTIVE_INIT_PARAM_NAME = "active";
    private static final Map<String, Integer> ACCESS_MAP = new HashMap<>();
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) {
        String activeString = filterConfig.getInitParameter(ACTIVE_INIT_PARAM_NAME);
        if (activeString != null) {
            active = (activeString.equalsIgnoreCase("true"));
        }
        ACCESS_MAP.put("/showAllCars", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showCarById", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showCarByGosNo", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showCarsByColor", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/addCar", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/deleteCar", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/editCar", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showAllUsers", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showUserById", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showUserByLogin", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/deleteUser", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showAllOrders", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showOrderById", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showOrderByUserId", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/showOrderByCarId", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/deleteOrder", UserRole.ADMIN.getId());
        ACCESS_MAP.put("/editUser", UserRole.CLIENT.getId());
        ACCESS_MAP.put("/booking", UserRole.CLIENT.getId());
        ACCESS_MAP.put("/listFreeCars", UserRole.CLIENT.getId());
        ACCESS_MAP.put("/carReturn", UserRole.CLIENT.getId());
        ACCESS_MAP.put("/exit", UserRole.CLIENT.getId());
        ACCESS_MAP.put("/registration", UserRole.GUEST.getId());
        ACCESS_MAP.put("/authorization", UserRole.GUEST.getId());
        ACCESS_MAP.put("/setLocal", UserRole.GUEST.getId());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (active) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            UserEntity user = (UserEntity) httpServletRequest.getSession().getAttribute("authorizedUser");
            if (user == null) {
                user = new UserEntity();
                user.setRole(UserRole.GUEST);
            }
            String reqURI = httpServletRequest.getRequestURI();
            Integer accessLevel = ACCESS_MAP.get(reqURI);
            if (accessLevel != null) {
                if (accessLevel <= user.getRole().getId()) {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                    httpServletRequest.setAttribute("error", SC_FORBIDDEN);
                    httpServletResponse.sendError(SC_FORBIDDEN);
                }
            } else {
                httpServletRequest.setAttribute("error", SC_NOT_FOUND);
                httpServletResponse.sendError(SC_NOT_FOUND);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
