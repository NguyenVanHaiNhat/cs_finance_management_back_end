package org.example.cs_finance_management_back_end.config;

import org.example.cs_finance_management_back_end.model.entity.Roles;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsersPrinciple implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;

    public UsersPrinciple(String username, String password,
                         Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UsersPrinciple build(Users users) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles roles : users.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        }
        return new UsersPrinciple(users.getUsername(),
                users.getPassword(),authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
