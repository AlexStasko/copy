package by.yakunina.copy.storage.dao;

import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.storage.support.CrudDao;

public interface RoleDao extends CrudDao<Role> {

    Role readByName(String roleName);
}
