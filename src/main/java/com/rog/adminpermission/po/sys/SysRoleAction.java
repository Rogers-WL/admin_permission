package com.rog.adminpermission.po.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role_action")
public class SysRoleAction {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "action_id")
    private Integer actionId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return action_id
     */
    public Integer getActionId() {
        return actionId;
    }

    /**
     * @param actionId
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}