package com.rsd.service;

import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;



public class SysUserService {
    private static Logger logger = Logger.getLogger(SysUserService.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("在加载MySQL驱动时发生异常！"+e.getMessage());
        }
    }

    public List<SysUser> queryAllList(){

        List<SysUser> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select t1.*,t2.name role_name from bu_sys_user t1 left join bu_sys_role t2 on t1.role_id=t2.id");

            while (rs.next()) {
                int id = rs.getInt("id");
                String loginName = rs.getString("login_name");
                String password = rs.getString("password");
                String realName = rs.getString("real_name");
                String roleId = rs.getString("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Date createTime = rs.getTimestamp("create_time");
                String roleName = rs.getString("role_name");

                SysUser sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setLoginName(loginName);
                sysUser.setPassword(password);
                sysUser.setRealName(realName);
                sysUser.setRoleId(Integer.valueOf(roleId));
                sysUser.setSex(sex);
                sysUser.setTel(tel);
                sysUser.setCreateTime(createTime);
                sysUser.setRoleName(roleName);

                list.add(sysUser);

            }
        } catch (SQLException e) {
            logger.error("在查询列表时发生异常！"+e.getMessage());
        }finally {
            close(connection);
        }
        return list;
    }

    public SysUser getObjectByLoginName(String loginName) {
        SysUser sysUser = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website?useUnicode=true&characterEncoding=utf8", "root", "123456");
            boolean closed = connection.isClosed();
            Statement statement = connection.createStatement();
            String sql = "select * from bu_sys_user where login_name='"+loginName+"'";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String realName = rs.getString("real_name");
                String password = rs.getString("password");
                String roleId = rs.getString("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Timestamp createTime = rs.getTimestamp("create_time");

                sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setLoginName(loginName);
                sysUser.setPassword(password);
                sysUser.setRealName(realName);
                sysUser.setRoleId(Integer.valueOf(roleId));
                sysUser.setSex(sex);
                sysUser.setTel(tel);
                sysUser.setCreateTime(createTime);

            }
        } catch (SQLException e) {
            logger.error("在查询登录名时发生异常！"+e.getMessage());
        } finally {
            close(connection);
        }
        return sysUser;
    }

    public void insert (SysUser sysUser) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        String format = sdf.format(date);

        String sql = "insert into bu_sys_User values (null ,'"+sysUser.getLoginName()+"','"+sysUser.getPassword()+"','"+sysUser.getRealName()+"','"+sysUser.getHeadPicPath()+"',"+sysUser.getRoleId()+",'"+sysUser.getSex()+"','"+sysUser.getTel()+"','"+format+"')";
        JDBCUtil.execute(sql);
    }

    public void delete(Integer id) {
        String sql = "delete from bu_sys_user where id=" + id;
        JDBCUtil.execute(sql);
    }

    public SysUser getById (Integer id) {
        SysUser sysUser = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_sys_user where id=" + id);

            while (rs.next()) {
                String loginName = rs.getString("login_name");
                String password = rs.getString("password");
                String realName = rs.getString("real_name");
                String headPicPath = rs.getString("head_pic_path");
                String roleId = rs.getString("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Timestamp createTime = rs.getTimestamp("create_time");

                sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setLoginName(loginName);
                sysUser.setPassword(password);
                sysUser.setRealName(realName);
                sysUser.setHeadPicPath(headPicPath);
                sysUser.setRoleId(Integer.valueOf(roleId));
                sysUser.setSex(sex);
                sysUser.setTel(tel);
                sysUser.setCreateTime(createTime);
            }
        } catch (SQLException e) {
            logger.error("在查询Id时发生异常！"+e.getMessage());
        } finally {
            close(connection);
        }
        return sysUser;
    }


    public List<SysUser> queryListBySearch(SysUser searchSysUser){
        List<SysUser> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "select t1.*,t2.name role_name from bu_sys_user t1 left join bu_sys_role t2 on t1.role_id=t2.id";
            sql += " where 1=1";
            if (searchSysUser.getLoginName() != null && !searchSysUser.getLoginName().equals("")) {
                sql += " and login_name like '%"+searchSysUser.getLoginName()+"%' ";
            }
            if (searchSysUser.getRealName() != null && !searchSysUser.getRealName().equals("")) {
                sql += " and real_name like '%"+searchSysUser.getRealName()+"%' ";
            }
            if (searchSysUser.getTel() != null && !searchSysUser.getTel().equals("")) {
                sql += " and tel like '%"+searchSysUser.getTel()+"%' ";
            }
            if (searchSysUser.getRoleId() != null && !searchSysUser.getRoleId().equals("")) {
                sql += " and role_id ="+searchSysUser.getRoleId();
            }
            if (searchSysUser.getSex() != null && !searchSysUser.getSex().equals("")) {
                sql += " and sex ='"+searchSysUser.getSex()+"'";
            }
            if (searchSysUser.getSearchStartTime() != null && !searchSysUser.getSearchStartTime().equals("")) {
                sql += " and t1.create_time >= '" + searchSysUser.getSearchStartTime() + "00:00:00'";
            }
            if (searchSysUser.getSearchEndTime() != null && !searchSysUser.getSearchEndTime().equals("")) {
                sql += " and t1.create_time < '" + searchSysUser.getSearchEndTime() + "23:59:59'";
            }

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String loginName = rs.getString("login_name");
                String realName = rs.getString("real_name");
                String roleId = rs.getString("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Date createTime = rs.getTimestamp("create_time");
                String roleName = rs.getString("role_name");

                SysUser sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setLoginName(loginName);
                sysUser.setRealName(realName);
                sysUser.setRoleId(Integer.valueOf(roleId));
                sysUser.setSex(sex);
                sysUser.setTel(tel);
                sysUser.setCreateTime(createTime);
                sysUser.setRoleName(roleName);

                list.add(sysUser);

            }
        } catch (SQLException e) {
            logger.error("在进行模糊查询时发生异常！"+e.getMessage());
        }finally {
            close(connection);
        }
        return list;
    }

    public void update(SysUser sysUser) {
        String sql = "update bu_sys_user set login_name='" + sysUser.getLoginName() + "',password='" + sysUser.getPassword() + "',real_name='" + sysUser.getRealName()+"'";
        if (sysUser.getHeadPicPath() != null) {
            sql += ",head_pic_path='"+sysUser.getHeadPicPath()+"'";
        }
        sql += ",role_id=" + sysUser.getRoleId() + ",sex='" + sysUser.getSex() + "',tel='" + sysUser.getTel() + "' where id=" + sysUser.getId();
        JDBCUtil.execute(sql);
    }

    public void updatePassword(SysUser sysUser) {
        String sql = "update bu_sys_user set password='" + sysUser.getNewPassword() + "' where id=" + sysUser.getId();
        JDBCUtil.execute(sql);
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website","root","123456");
        } catch (SQLException e) {
            logger.error("在连接数据库时发生异常！"+e.getMessage());
        }
        return connection;
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("在关闭连接时发生异常"+e.getMessage());
            }
        }
    }

}
