package model.DAO;

import model.entity.UserEntity;

public interface UserDAO extends AbstractDAO<UserEntity> {
    UserEntity getByRole(String role);
    UserEntity getByLogin(String login);
}
