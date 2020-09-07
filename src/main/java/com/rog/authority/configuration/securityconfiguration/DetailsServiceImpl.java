package com.rog.authority.configuration.securityconfiguration;

import com.rog.authority.dao.sys.SysUserMapper;
import com.rog.authority.po.sys.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/6/22 21:36
 **/
@Service
public class DetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUserDetails sysUserDetails = new SysUserDetails();
        SysUser sysUser = sysUserMapper.selectByUserName(s);
        if (sysUser == null){
            throw  new UsernameNotFoundException("账号不存在！");
        }
        BeanUtils.copyProperties(sysUser,sysUserDetails);
        return sysUserDetails;

    }
}
