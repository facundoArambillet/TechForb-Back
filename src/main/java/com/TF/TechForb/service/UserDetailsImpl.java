package com.TF.TechForb.service;

import com.TF.TechForb.model.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private Long idUser;
    private String dniNumber;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long idUser, String dniNumber, String password, Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.dniNumber = dniNumber;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User authUser) {
        Collection<GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority(authUser.getUserDocumentType().getType())
        );

        return new UserDetailsImpl(
                authUser.getIdUser(),
                authUser.getDocumentNumber().toString(),
                authUser.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.dniNumber;
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
