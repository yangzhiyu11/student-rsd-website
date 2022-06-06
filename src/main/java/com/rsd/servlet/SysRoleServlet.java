package com.rsd.servlet;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysRole;
import com.rsd.service.SysFunctionService;
import com.rsd.service.SysRoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/sysRole/list","/sysRole/toAddPage","/sysRole/doAdd","/sysRole/toUpdatePage","/sysRole/doUpdate","/sysRole/doDelete"})
public class SysRoleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/sysRole/list")) {
            list(request,response);
        }else if (request.getRequestURI().equals("/sysRole/toAddPage")) {
            toAddPage(request,response);
        }else if (request.getRequestURI().equals("/sysRole/doAdd")) {
            doAdd(request,response);
        }else if (request.getRequestURI().equals("/sysRole/toUpdatePage")) {
            toUpdatePage(request,response);
        }else if (request.getRequestURI().equals("/sysRole/doUpdate")) {
            doUpdate(request,response);
        }else if (request.getRequestURI().equals("/sysRole/doDelete")) {
            doDel(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SysRoleService sysRoleService = new SysRoleService();
        List<SysRole> list = sysRoleService.queryAllList();

        request.setAttribute("sysRoleList",list);
        request.getRequestDispatcher("/sysRole/list.jsp").forward(request,response);
    }

    private void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SysFunctionService sysFunctionService = new SysFunctionService();
        List<SysFunction> sysFunctionList = sysFunctionService.queryAllList();

        request.setAttribute("sysFunctionList",sysFunctionList);
        request.getRequestDispatcher("/sysRole/add.jsp").forward(request,response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        String[] functionIds = request.getParameterValues("functionId");

        SysRole sysRole = new SysRole();
        sysRole.setName(name);
        sysRole.setFunctionId(functionIds);
        sysRole.setCreateTime(new Date());

        SysRoleService sysRoleService = new SysRoleService();
        sysRoleService.insert(sysRole);

        response.sendRedirect("/sysRole/list");
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysRoleService sysRoleService = new SysRoleService();
        SysRole sysRole = sysRoleService.getById(Integer.valueOf(id));


        String[] functionIds = sysRoleService.getSysFunctionIdsByRole(sysRole.getId());
        sysRole.setFunctionId(functionIds);

        SysFunctionService sysFunctionService = new SysFunctionService();
        List<SysFunction> sysFunctionList = sysFunctionService.queryAllList();


        request.setAttribute("sysRole",sysRole);
        request.setAttribute("sysFunctionList",sysFunctionList);
        request.getRequestDispatcher("/sysRole/update.jsp").forward(request,response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String[] functionIds = request.getParameterValues("sysFunctionIds");

        SysRole sysRole = new SysRole();
        sysRole.setId(Integer.valueOf(id));
        sysRole.setName(name);
        sysRole.setCreateTime(new Date());
        sysRole.setFunctionId(functionIds);

        SysRoleService sysRoleService = new SysRoleService();
        sysRoleService.update(sysRole);

        response.sendRedirect("/sysRole/list");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysRoleService sysRoleService = new SysRoleService();
        sysRoleService.delete(Integer.valueOf(id));

        response.sendRedirect("/sysRole/list");
    }
}
