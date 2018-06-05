package by.yakunina.copy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//                .antMatchers("/**").permitAll();
                .antMatchers("/css/**", "/js/**","/index", "/upload", "/role", "/account").permitAll()
                .antMatchers("/user/**", "/greeting", "/files").hasRole("USER")
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }
}
