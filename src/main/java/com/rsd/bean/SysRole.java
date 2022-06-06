package com.rsd.bean;

import java.util.Date;

public class SysRole {
    private Integer id;
    private String name;
    private Date createTime;
    private String[] functionId;
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String[] getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String[] functionId) {
        this.functionId = functionId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
