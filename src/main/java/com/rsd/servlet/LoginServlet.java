package com.rsd.servlet;

import com.rsd.bean.SysUser;
import com.rsd.service.SysUserService;
import com.rsd.util.Md5Util;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");


        String encrypt = null;
        try {
            encrypt = Md5Util.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SysUserService sysUserService = new SysUserService();
        SysUser sysUser = sysUserService.getObjectByLoginName(loginName);

        if (sysUser != null) {

            if (sysUser.getPassword().equals(encrypt)){
                HttpSession session = request.getSession();
                session.setAttribute("sysUser",sysUser);
                session.setMaxInactiveInterval(5 * 60);

                response.sendRedirect("/index.jsp");
            } else {
                System.out.println("密码错误");
                request.setAttribute("loginResult","密码错误");

                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        } else {
            System.out.println("用户名不存在");
            logger.info("[]用户名不存在！");
            request.setAttribute("loginResult","用户名不存在");
            
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
