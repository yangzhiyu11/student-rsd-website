package com.rsd.servlet;

import com.rsd.bean.SysUser;
import com.rsd.service.SysUserService;
import com.rsd.util.Md5Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        String encrypt = Md5Util.encrypt(oldPassword);
        String encrypt1 = Md5Util.encrypt(newPassword);



        SysUserService sysUserService = new SysUserService();
        SysUser sysUser = sysUserService.getById(Integer.valueOf(id));
        sysUser.setNewPassword(encrypt1);
        sysUser.setId(Integer.valueOf(id));

        if (sysUser.getPassword().equals(encrypt)){
            sysUserService.updatePassword(sysUser);
            response.sendRedirect("/sysUser/list");
        }else {
            request.setAttribute("ww","原密码错误");
            request.getRequestDispatcher("/sysUser/reset.jsp").forward(request,response);
        }
    }
}
