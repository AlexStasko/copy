package by.yakunina.copy.storage.sql;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.dao.AccountDao;
import by.yakunina.copy.storage.mapper.AccountMapper;
import by.yakunina.copy.storage.support.AbstractCrudDao;
import by.yakunina.copy.storage.support.CrudDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AccountDaoImpl extends AbstractCrudDao<Account> implements AccountDao {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        return accountMapper.findByUsername(username);
    }

    @Override
    public void assignRoles(EntityId id, List<EntityId> roleIds) {
        accountMapper.assignRoles(id, roleIds);
    }

    @Override
    public void clearRoles(EntityId accountId) {
        accountMapper.clearRoles(accountId);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        accountMapper.changePassword(username, newPassword);
    }

    @Override
    public Account readWithRoles(EntityId id) {
        return accountMapper.readWithRoles(id);
    }

    @Override
    protected CrudDao<Account> getMapper() {
        return accountMapper;
    }
}
