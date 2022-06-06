package com.rsd.dao;

import com.rsd.bean.SysFiles;
import com.rsd.service.impl.SysFileServiceImpl;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SysFilesDAO {

    private static Logger logger = Logger.getLogger(SysFileServiceImpl.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("在加载MySQL驱动时发生异常！" + e.getMessage());
        }
    }

    public List<SysFiles> queryAllList() {
        return JDBCUtil.queryList(SysFiles.class,"select * from bu_sys_files");
    }

    public SysFiles getById(Integer id) {
        return JDBCUtil.queryById(SysFiles.class,"select * from bu_sys_files where id=" +id);
    }

    public void insert(SysFiles sysFiles) {

        String sql = "insert into bu_sys_files values(null,'" + sysFiles.getName() + "','" + sysFiles.getPath() + "','" + sysFiles.getFileSize() + "','" + sdf.format(new Date()) + "')";
        JDBCUtil.execute(sql);
    }

    public void update(SysFiles sysFiles) {
        String sql = "update bu_sys_files set name='"+sysFiles.getName()+"',path='"+sysFiles.getPath()+"',file_size="+sysFiles.getFileSize()+",create_time='"+sdf.format(sysFiles.getCreateTime())+"' where id="+sysFiles.getId();
        JDBCUtil.execute(sql);
    }

    public void delete(Integer id) {
        String sql = "delete from bu_sys_files where id="+id;
        JDBCUtil.execute(sql);
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
        } catch (SQLException e) {
            logger.error("在连接数据库时发生异常！" + e.getMessage());
        }
        return connection;
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("在关闭连接时发生异常" + e.getMessage());
            }
        }
    }
}
