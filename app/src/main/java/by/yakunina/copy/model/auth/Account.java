package by.yakunina.copy.model.auth;

import by.yakunina.copy.model.User;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * Account entity
 */
@Alias("Account")
public class Account implements Identifiable, Serializable {

    private static final long serialVersionUID = 1L;

    private EntityId id;
    private User user;
    private String username;
    private String password;
    private List<Role> roles;

    public Account() {
        this.id = null;
        this.user = null;
        this.username = null;
        this.password = null;
        this.roles = null;
    }

    public Account(AccountBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.roles = builder.roles;
        this.username = builder.username;
        this.password = builder.password;
    }

    @Override
    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("user", user)
                .append("username", username)
                .append("password", password)
                .append("roles", roles)
                .toString();
    }

    public static final class AccountBuilder implements Builder<Account> {
        private EntityId id;
        private User user;
        private String username;
        private String password;
        private List<Role> roles;

        public AccountBuilder withId(EntityId id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withUserId(User user) {
            this.user = user;
            return this;
        }

        public AccountBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder withRoles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
