package com.rog.authority.dao.sys;

import com.rog.authority.po.sys.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

    public  SysUser selectByUserName(String userName);
}