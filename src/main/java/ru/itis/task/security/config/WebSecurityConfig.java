package ru.itis.task.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.itis.task.filter.CookieFilter;
import ru.itis.task.model.Role;
import ru.itis.task.model.User;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.session.MySession;
import ru.itis.task.utils.AuthSuccessHandler;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Optional;

@EnableWebSecurity
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CookieFilter filter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MySession session;

    @Autowired
    @Qualifier(value = "myUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/signIn").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/products").authenticated()
                .antMatchers("/addProduct").hasAnyAuthority("ADMIN", "BUYER")
                .antMatchers("/makeOrder").hasAnyAuthority("ADMIN", "BUYER")
                .antMatchers("/orders").hasAnyAuthority("SELLER", "ADMIN")
                .antMatchers("/order/**").hasAnyAuthority("SELLER", "ADMIN")
                .antMatchers("/createProduct").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/signIn")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/products")
                .failureUrl("/signIn?error=1")
                .successHandler(successHandler)
                .permitAll();


        http.addFilterAfter(filter, BasicAuthenticationFilter.class);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String login = (String) map.get("sub");
            Optional<User> userOptional = userRepository.findUserByLogin(login);
            User user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                user = User.builder()
                        .hashPassword("")
                        .login(login)
                        .email((String) map.get("email"))
                        .role(Role.BUYER)
                        .orders(new ArrayList<>()).build();
                userRepository.save(user);
            }
            session.setUser(user);
            return user;
        };
    }
}
