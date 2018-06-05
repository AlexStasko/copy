package by.yakunina.copy.model.auth;

import by.yakunina.copy.model.support.EntityId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CopyUser extends User implements UserDetails {

    private static final long serialVersionUID = -1L;

    private final String id;


    public CopyUser() {
        super("N/A", "N/A", Collections.emptyList());
        this.id = null;
    }

    public CopyUser(CopyUserBuilder builder) {
        super(builder.username, builder.password, builder.authorities);
        this.id = builder.id;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static final class CopyUserBuilder {

        private String id;
        private String username;
        private String password;
        private boolean enabled = true;
        private boolean accountNonExpired = true;
        private boolean credentialsNonExpired = true;
        private boolean accountNonLocked = true;
        private Collection<? extends GrantedAuthority> authorities;

        /**
         * Adds id.
         *
         * @param pId id of user.
         * @return this builder.
         */
        public CopyUserBuilder withId(String pId) {
            this.id = pId;
            return this;
        }

        /**
         * Adds username.
         *
         * @param pUsername name of user.
         * @return this builder.
         */
        public CopyUserBuilder withUsername(String pUsername) {
            this.username = pUsername;
            return this;
        }

        /**
         * Adds password.
         *
         * @param pPassword password.
         * @return this builder.
         */
        public CopyUserBuilder withPassword(String pPassword) {
            this.password = pPassword;
            return this;
        }

        /**
         * Is user enabled.
         *
         * @param pEnabled is user enabled.
         * @return this builder.
         */
        public CopyUserBuilder withEnabled(boolean pEnabled) {
            this.enabled = pEnabled;
            return this;
        }

        /**
         * Is account not expired.
         *
         * @param pAccountNonExpired is account not expired.
         * @return this builder.
         */
        public CopyUserBuilder withAccountNonExpired(boolean pAccountNonExpired) {
            this.accountNonExpired = pAccountNonExpired;
            return this;
        }

        /**
         * Is credentials not expired.
         *
         * @param pCredentialsNonExpired is credentials not expired.
         * @return this builder.
         */
        public CopyUserBuilder withCredentialsNonExpired(boolean pCredentialsNonExpired) {
            this.credentialsNonExpired = pCredentialsNonExpired;
            return this;
        }

        /**
         * Is account not locked.
         *
         * @param pAccountNonLocked is account not locked.
         * @return this builder.
         */
        public CopyUserBuilder withAccountNonLocked(boolean pAccountNonLocked) {
            this.accountNonLocked = pAccountNonLocked;
            return this;
        }

        /**
         * Adds authorities to user.
         *
         * @param pAuthorities authorities of user.
         * @return this builder.
         */
        public CopyUserBuilder withAuthorities(Collection<? extends GrantedAuthority> pAuthorities) {
            this.authorities = pAuthorities;
            return this;
        }

        public CopyUser build() {
            return new CopyUser(this);
        }
    }
}
