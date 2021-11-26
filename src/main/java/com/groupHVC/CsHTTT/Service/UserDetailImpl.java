package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.RoleEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailImpl implements UserDetails {
    private UserEntity userEntity;

    public UserDetailImpl(UserEntity user) {
        this.userEntity = user;
    }

    public String getFullName() {
        return this.userEntity.getFullName();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return this.userEntity.getUsername();
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


    public String getEncryptedPassword() {
        return this.userEntity.getEncryptedPassword();
    }



    public String getPhoneNumber() {
        return this.userEntity.getPhoneNumber();
    }



    public String getAvatar() {
        return this.userEntity.getAvatar();
    }



    public String getSex() {
        return this.userEntity.getSex();
    }



    public Date getBithday() {
        return this.userEntity.getBithday();
    }



    public String getEmail() {
        return this.userEntity.getEmail();
    }



    public String getAddress() {
        return this.userEntity.getAddress();
    }


}
