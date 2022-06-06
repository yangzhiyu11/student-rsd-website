package com.rsd.servlet;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysUser;
import com.rsd.service.SysFunctionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/menu")
public class MenuPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        SysFunctionService sysFunctionService = new SysFunctionService();
        List<SysFunction> functionList = sysFunctionService.queryAllListByUserId(sysUser.getId());

        request.setAttribute("functionList",functionList);
        request.getRequestDispatcher("/menu.jsp").forward(request,response);
    }
}
