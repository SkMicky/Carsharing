package model.DAO;

import model.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {
    List<User> getByRole(int role);
    User getByLogin(String login);
    User searchInDataBase(String login, String password);
}
