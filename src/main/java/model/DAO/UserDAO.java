package model.DAO;

import model.entity.UserEntity;

import java.util.List;

public interface UserDAO extends AbstractDAO<UserEntity> {
    List<UserEntity> getByRole(int role);
    UserEntity getByLogin(String login);
}
