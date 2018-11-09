package model.action;

import model.action.adminActions.carActions.*;
import model.action.adminActions.orderActions.*;
import model.action.adminActions.clientActions.*;
import model.action.userActions.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class URIAction implements ActionFactory {
    private final Map<String, Action> actionMap = new HashMap<>();

    @Override
    public Action getAction(HttpServletRequest request){
        String uri = request.getRequestURI();
        Action action = actionMap.get(uri);
        if(action == null){
            action = new ErrorAction();
        }
        return action;
    }
    public URIAction(){
        actionMap.put("/authorization", new AutorAction());
        actionMap.put("/registration", new RegistrationAction());
        actionMap.put("/exit", new ExitAction());
        actionMap.put("/listFreeCars", new ShowFreeCars());
        actionMap.put("/booking", new BookingCar());
        actionMap.put("/carReturn", new CarReturner());
        actionMap.put("/showAllCars", new ShowAllCars());
        actionMap.put("/showCarById", new ShowCarById());
        actionMap.put("/showCarByGosNo", new ShowCarByGosNo());
        actionMap.put("/showCarsByColor", new ShowCarsByColor());
        actionMap.put("/addCar", new AddCar());
        actionMap.put("/editCar", new EditCar());
        actionMap.put("/deleteCar", new DeleteCar());
        actionMap.put("/showAllUsers", new ShowAllUsers());
        actionMap.put("/showUserById", new ShowUserById());
        actionMap.put("/showUserByLogin", new ShowUserByLogin());
        actionMap.put("/editUser", new EditUser());
        actionMap.put("/deleteUser", new DeleteUser());
        actionMap.put("/showAllOrders", new ShowAllOrders());
        actionMap.put("/showOrderById", new ShowOrderById());
        actionMap.put("/showOrdersByUserId", new ShowOrdersByUserId());
        actionMap.put("/showOrdersByCarId", new ShowOrdersByCarId());
        actionMap.put("/deleteOrder", new DeleteOrder());
    }
}
