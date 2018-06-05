package by.yakunina.copy.storage.dao;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.support.CrudDao;

import java.util.List;

public interface AccountDao extends CrudDao<Account> {

    Account findByUsername(String username);

    void assignRoles(EntityId id, List<EntityId> roleIds);

    void clearRoles(EntityId accountId);

    void changePassword(String username, String newPassword);

    Account readWithRoles(EntityId id);
}
