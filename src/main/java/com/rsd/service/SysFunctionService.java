package com.rsd.service;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysRole;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SysFunctionService {
    private static Logger logger = Logger.getLogger(SysUserService.class);
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<SysFunction> queryAllList() {
        List<SysFunction> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_sys_function");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                SysFunction sysFunction = new SysFunction();
                sysFunction.setId(id);
                sysFunction.setName(name);

                list.add(sysFunction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return list;
    }

    public List<SysFunction> queryAllListByUserId(Integer userId) {
        List<SysFunction> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "select * from bu_sys_function where id in (select function_id from bu_sys_permit where role_id = (select role_id from bu_sys_user where id = "+userId+"))";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                SysFunction sysFunction = new SysFunction();
                sysFunction.setId(id);
                sysFunction.setName(name);
                sysFunction.setUrl(url);

                list.add(sysFunction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return list;
    }

    public boolean insert(SysFunction sysFunction) {
        boolean result = true;
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into bu_sys_function values (null ,'"+sysFunction.getName()+"',null )";
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            logger.error("执行SQL时发生异常！");
        } finally {
            close(connection);
        }
        return result;
    }

    public boolean delete(Integer id) {
        boolean result = true;
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "delete from bu_sys_function where id="+id;
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            logger.error("执行SQL时发生异常！"+e.getMessage());
        } finally {
            close(connection);
        }
        return result;
    }

    public SysFunction getById(Integer id) {
        SysFunction sysFunction = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_sys_function where id=" + id);

            while (rs.next()){
                String name = rs.getString("name");

                sysFunction = new SysFunction();
                sysFunction.setId(Integer.valueOf(id));
                sysFunction.setName(name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return sysFunction;
    }

    public boolean update(SysFunction sysFunction) {
        boolean result = true;
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "update bu_sys_function set name='"+sysFunction.getName()+"' where id="+sysFunction.getId();
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            logger.error("执行修改时发生异常！");
        } finally {
            close(connection);
        }
      return result;
    }


    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

