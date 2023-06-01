package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Document(collection = "users")
public class User extends BaseEntity implements UserDetails {

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @DBRef
    private MediaFile profilePicture;

    @Override
    public String getUsername(){
        return this.login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
