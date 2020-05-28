package ru.itis.task.security.details;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserAuth implements Authentication {
    Boolean isAuth;
    UserDetails userDetails;

    public UserAuth(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("BUYER"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return Objects.equals(isAuth, userAuth.isAuth) &&
                Objects.equals(userDetails, userAuth.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAuth, userDetails);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuth == null ? userDetails != null : isAuth;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuth = isAuthenticated;
    }

    @Override
    public String getName() {
        return String.valueOf(userDetails.hashCode());
    }
}
