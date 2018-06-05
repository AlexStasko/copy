package by.yakunina.copy.storage.sql;

import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.storage.dao.RoleDao;
import by.yakunina.copy.storage.mapper.RoleMapper;
import by.yakunina.copy.storage.support.AbstractCrudDao;
import by.yakunina.copy.storage.support.CrudDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RoleDaoImpl extends AbstractCrudDao<Role> implements RoleDao {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role readByName(String roleName) {
        return roleMapper.readByName(roleName);
    }

    @Override
    protected CrudDao<Role> getMapper() {
        return roleMapper;
    }
}
