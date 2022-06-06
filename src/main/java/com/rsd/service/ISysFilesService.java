package com.rsd.service;

import com.rsd.bean.SysFiles;

import java.util.List;

public interface ISysFilesService {
    List<SysFiles> queryAllList();

    SysFiles getById(Integer id);

    void insert(SysFiles sysFiles);

    void update(SysFiles sysFiles);

    void delete(Integer id);
}
