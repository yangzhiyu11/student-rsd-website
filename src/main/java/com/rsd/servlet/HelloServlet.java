package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.SysUser;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(HelloServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("加载MySQL驱动异常！");
        }
        SysUser sysUser = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "select * from bu_sys_user where id=4";
            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            int id = rs.getInt("id");
            String loginName = rs.getString("login_name");
            String realName = rs.getString("real_name");
            int roleId = rs.getInt("role_id");
            String sex = rs.getString("sex");
            String tel = rs.getString("tel");
            Timestamp createTime = rs.getTimestamp("create_time");

            sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setLoginName(loginName);
            sysUser.setRealName(realName);
            sysUser.setRoleId(roleId);
            sysUser.setSex(sex);
            sysUser.setTel(tel);
            sysUser.setCreateTime(createTime);

        } catch (SQLException e) {
            logger.error("链接数据库时发生异常!" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("关闭链接时发生异常！");
                }
            }
        }

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(sysUser);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
