package by.yakunina.copy.service;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.storage.dao.AccountDao;
import by.yakunina.copy.storage.dao.RoleDao;
import by.yakunina.copy.support.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Component
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private static final String USER_ROLE_NAME = "USER";

    @Resource
    private AccountDao accountDao;
    @Resource
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account authenticate(String username) {
        return accountDao.findByUsername(username);
    }

    public Account findUser(EntityId id) {
        return accountDao.readWithRoles(id);
    }

    public EntityId createAccount(Account registration) {
        EntityId accountId = accountDao.create(new Account.AccountBuilder()
                .withId(new EntityId(KeyGenerator.getUUID()))
                .withUsername(registration.getUsername())
                .withPassword(passwordEncoder.encode(registration.getPassword()))
                .build());
        LOGGER.info("New account [{}]", accountId);
        Role role = roleDao.readByName(USER_ROLE_NAME);
        LOGGER.info("User role [{}]", role);
        List<EntityId> userRoleId = Collections.singletonList(role.getId());
        accountDao.assignRoles(accountId, userRoleId);
        return accountId;
    }

    public void assignRoles(EntityId accountId, List<EntityId> rolesIds) {
        accountDao.clearRoles(accountId);
        accountDao.assignRoles(accountId, rolesIds);
    }

}
