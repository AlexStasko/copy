package by.yakunina.copy.service;

import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.dao.RoleDao;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RoleService {

    @Resource
    private RoleDao roleDao;

    public EntityId save(Role newRole) {
        Role role = new Role(new EntityId(KeyGenerator.getUUID()), newRole.getName());
        return roleDao.create(role);
    }

    public Role find(EntityId id) {
        return roleDao.read(id);
    }

    public Role findByName(String name) {
        return roleDao.readByName(name);
    }

    public void update(Role role) {
        if (null == roleDao.read(role.getId())) {
            throw new RuntimeException(String.format("Can't find role with id [%s]", role.getId()));
        }
        roleDao.update(role);
    }

    public void remove(EntityId id) {
        roleDao.delete(id);
    }

}
