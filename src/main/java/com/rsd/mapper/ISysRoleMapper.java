package com.rsd.mapper;

import com.rsd.bean.SysRole;

import java.util.List;

public interface ISysRoleMapper {
    List<SysRole> queryList();

    SysRole getById(Integer id);
}
