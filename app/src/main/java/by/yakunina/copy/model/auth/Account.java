package by.yakunina.copy.model.auth;

import by.yakunina.copy.model.support.Entity;

import java.util.List;

/**
 * Account entity
 */
public class Account extends Entity{

    private static final long serialVersionUID = 1L;

    private final int userId;
    private final String username;
    private final String password;
    private final List<Role> roles;

	public Account(int id, String username, String password, int userId, List<Role> roles) {
        super(id);
        this.userId = userId;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
