package com.rsd.service.impl;

import com.rsd.bean.SysFiles;
import com.rsd.dao.SysFilesDAO;
import com.rsd.service.ISysFilesService;

import java.util.List;

public class SysFileServiceImpl implements ISysFilesService {

    private SysFilesDAO sysFilesDAO = new SysFilesDAO();

    @Override
    public List<SysFiles> queryAllList() {
        return sysFilesDAO.queryAllList();
    }

    @Override
    public SysFiles getById(Integer id) {
        return sysFilesDAO.getById(id);
    }

    @Override
    public void insert(SysFiles sysFiles) {
        sysFilesDAO.insert(sysFiles);
    }

    @Override
    public void update(SysFiles sysFiles) {
        sysFilesDAO.update(sysFiles);
    }

    @Override
    public void delete(Integer id) {
        sysFilesDAO.delete(id);
    }
}
