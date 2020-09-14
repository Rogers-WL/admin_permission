package com.rog.authority.configuration.securityconfiguration;

import com.rog.authority.po.sys.SysRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/6/22 21:26
 **/
@Data
public class SysUserDetails  implements UserDetails {


    private Integer id;
    private String username;
    private String password;
    private List<SysRole> roles;

    /**
     * @Description //获取当前用户的所有角色
     * @author Rogers_WL
     * @Date  2020/6/22 21:28
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(sysRole -> authorities.add(new SimpleGrantedAuthority(sysRole.getRoleName())));
        return authorities;
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
}
