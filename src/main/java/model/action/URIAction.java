package model.action;

import model.action.userActions.AutorAction;
import model.action.userActions.ExitAction;
import model.action.userActions.RegistrationAction;
import model.action.userActions.ShowFreeCars;

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
    }
}
