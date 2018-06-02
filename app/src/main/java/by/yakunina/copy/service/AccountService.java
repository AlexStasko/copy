package by.yakunina.copy.service;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.auth.Role;

import java.util.Collections;
import java.util.List;

public class AccountService {

    public Account authenticate(String username) {
        List<Role> roles = Collections.singletonList(new Role(1, "USER"));
        return new Account(1, username, "1234", 1, roles);
    }

    public Account findUser(int id) {
        return null;
    }

    public int createAccount(Account registration) {
        return 0;
    }
}
