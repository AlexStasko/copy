package by.yakunina.copy.controller.auth;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.auth.CopyUser;
import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.service.AccountService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CopyUserDetails implements UserDetailsService {

    @Resource
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(accountService.authenticate(username)).map(this::buildUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private CopyUser buildUser(Account account) {
        return new CopyUser.CopyUserBuilder()
                .withId(account.getId().getId())
                .withUsername(account.getUsername())
                .withPassword(account.getPassword())
                .withAuthorities(Optional.ofNullable(account.getRoles())
                        .map(this::applyRoles).orElseThrow(() ->
                                new RuntimeException(String.format("%s: %s",
                                        "User does not have any roles", account.getUsername()))))
                .build();
    }

    private List<SimpleGrantedAuthority> applyRoles(List<Role> roles) {
        if (!roles.isEmpty()) {
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
