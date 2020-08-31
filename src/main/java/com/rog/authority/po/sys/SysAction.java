package com.rog.authority.po.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_action")
public class SysAction {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "action_url")
    private String actionUrl;

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
     * @return action_name
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName
     */
    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    /**
     * @return action_url
     */
    public String getActionUrl() {
        return actionUrl;
    }

    /**
     * @param actionUrl
     */
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
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