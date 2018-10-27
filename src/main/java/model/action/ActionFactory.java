package model.action;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface ActionFactory {

    Action getAction(HttpServletRequest request) throws SQLException, ClassNotFoundException;
}
