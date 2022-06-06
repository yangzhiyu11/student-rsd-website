package com.rsd.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsd.anno.Nonparticipation;

import java.util.Date;

public class SysUser {
    private Integer id;
    private String loginName;
    private String password;
    private String realName;
    private String headPicPath;
    private Integer roleId;
    private String sex;
    private String tel;
    private Date createTime;
    private String roleName;
    private String oldPassword;
    private String newPassword;
    private String searchStartTime;
    private String searchEndTime;
    @JsonIgnore
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getHeadPicPath() {
        return headPicPath;
    }

    public void setHeadPicPath(String headPicPath) {
        this.headPicPath = headPicPath;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    @Nonparticipation(supportSQL = false,supportJson = false)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
