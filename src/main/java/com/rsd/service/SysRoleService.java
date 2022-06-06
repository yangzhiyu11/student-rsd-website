package com.rsd.service;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SysRoleService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<SysRole> queryAllList() {
        List<SysRole> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_sys_role");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp createTime = rs.getTimestamp("create_time");

                SysRole sysRole = new SysRole();
                sysRole.setId(id);
                sysRole.setName(name);
                sysRole.setCreateTime(createTime);

                list.add(sysRole);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return list;
    }

    public String[] getSysFunctionIdsByRole(Integer roleId) {
        String[] functionIds = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select function_id from bu_sys_permit where role_id=" + roleId);

            List<String> list = new ArrayList<>();
            while (rs.next()) {
                int functionId = rs.getInt("function_id");
                list.add(String.valueOf(functionId));
            }

            functionIds = new String[list.size()];
            for (int i = 0; i < list.size();i ++) {
                functionIds[i] = list.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return functionIds;
    }

    public void insert(SysRole sysRole) {
        String format = sdf.format(sysRole.getCreateTime());

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.execute("insert into bu_sys_role values (null ,'"+sysRole.getName()+"','"+format+"')",Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int roleId = rs.getInt(1);

            String[] functionIds = sysRole.getFunctionId();
            if (functionIds != null && functionIds.length>0){
                for (int i =0; i < functionIds.length; i ++) {
                    Integer function = Integer.valueOf(functionIds[i]);
                    String sql = "insert into bu_sys_permit values (null,"+roleId+","+function+")";
                    statement.execute(sql);
                    connection.commit();
                }
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            close(connection);
        }
    }

    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute("delete from bu_sys_role where id="+id);
            statement.execute("delete from bu_sys_permit where role_id="+id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    public SysRole getById(Integer id) {
        SysRole sysRole = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_sys_role where id=" + id);

            while (rs.next()){
                String name = rs.getString("name");
                Timestamp createTime = rs.getTimestamp("create_time");

                sysRole = new SysRole();
                sysRole.setId(id);
                sysRole.setName(name);
                sysRole.setCreateTime(createTime);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return sysRole;
    }

    public void update(SysRole sysRole) {
        Connection connection = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        String format = simpleDateFormat.format(sysRole.getCreateTime());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.execute("update bu_sys_role set name='"+sysRole.getName()+"',create_time='"+format+"' where id="+sysRole.getId());

            statement.execute("delete from bu_sys_permit where role_id="+sysRole.getId());

            String[] functionIds = sysRole.getFunctionId();
            if (functionIds != null && functionIds.length>0){
                for (int i =0; i < functionIds.length; i ++) {
                    Integer functionId = Integer.valueOf(functionIds[i]);

                    String sql = "insert into bu_sys_permit values (null,"+sysRole.getId()+","+functionId+")";
                    statement.execute(sql);
                    connection.commit();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            close(connection);
        }
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
