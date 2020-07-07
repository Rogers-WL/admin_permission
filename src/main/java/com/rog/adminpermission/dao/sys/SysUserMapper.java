package com.rog.adminpermission.dao.sys;

import com.rog.adminpermission.po.sys.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

    public  SysUser selectByUserName(String userName);
}