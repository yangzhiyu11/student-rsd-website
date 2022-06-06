package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.anno.FirstAnno;
import com.rsd.bean.SysFunction;
import com.rsd.service.SysFunctionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = {"/sysFunction/list","/sysFunction/doAdd","/sysFunction/toUpdatePage","/sysFunction/doUpdate","/sysFunction/doDelete"})
public class SysFunctionServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/sysFunction/list")) {
            list(request,response);
        }else if (request.getRequestURI().equals("/sysFunction/doAdd")) {
            doAdd(request,response);
        }else if (request.getRequestURI().equals("/sysFunction/toUpdatePage")) {
            toUpdatePage(request,response);
        }else if (request.getRequestURI().equals("/sysFunction/doUpdate")) {
            doUpdate(request,response);
        }else if (request.getRequestURI().equals("/sysFunction/doDelete")) {
            doDel(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SysFunctionService sysFunctionService = new SysFunctionService();
        List<SysFunction> list = sysFunctionService.queryAllList();

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(list);//[{"id":1,"name":"aaa"},{"id":2,"name":"bbb"},{"id":3,"name":"ccc"}]

        PrintWriter out = response.getWriter();
        out.print(json);

//        request.setAttribute("sysFunctionList",list);
//        request.getRequestDispatcher("/sysFunction/list.jsp").forward(request,response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");

        SysFunction sysFunction = new SysFunction();
        sysFunction.setName(name);

        SysFunctionService sysFunctionService = new SysFunctionService();
        boolean b = sysFunctionService.insert(sysFunction);

        PrintWriter out = response.getWriter();
        out.print(b);
//
//        response.sendRedirect("/sysFunction/list");
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFunctionService sysFunctionService = new SysFunctionService();
        SysFunction sysFunction = sysFunctionService.getById(Integer.valueOf(id));

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(sysFunction);

        PrintWriter out = response.getWriter();
        out.print(json);
//        request.setAttribute("sysFunction",sysFunction);
//        request.getRequestDispatcher("/sysFunction/update.jsp").forward(request,response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(Integer.valueOf(id));
        sysFunction.setName(name);

        SysFunctionService sysFunctionService = new SysFunctionService();
        boolean b = sysFunctionService.update(sysFunction);

        PrintWriter out = response.getWriter();
        out.print(b);

//        response.sendRedirect("/sysFunction/list");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFunctionService sysFunctionService = new SysFunctionService();
        boolean b = sysFunctionService.delete(Integer.valueOf(id));

        PrintWriter out = response.getWriter();
        out.print(b);
//        response.sendRedirect("/sysFunction/list");
    }
}
