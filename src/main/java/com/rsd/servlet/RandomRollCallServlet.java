package com.rsd.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/tool/random1","/tool/random2"})
public class RandomRollCallServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals("/tool/random1")) {
            reandom1(request,response);
        }
        else if (uri.equals("/tool/random2")) {
            reandom2(request,response);
        }
    }

    private void reandom1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = new ArrayList<>();
        list.add("樊书琪");
        list.add("杨治宇");
        list.add("毛晓睿");
        list.add("许文韬");
        list.add("高超");
        list.add("杨争鸣");

        double random = Math.random();
        Double floor = Math.floor(random * 6);
        String studentName = list.get(floor.intValue());

        request.setAttribute("studentName",studentName);
        request.getRequestDispatcher("/tool/randomName.jsp").forward(request,response);
    }

    private void reandom2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("樊书琪");
        list.add("杨治宇");
        list.add("毛晓睿");
        list.add("许文韬");
        list.add("高超");
        list.add("杨争鸣");

        double random = Math.random();
        Double floor = Math.floor(random * 6);
        String studentName = list.get(floor.intValue());

        PrintWriter out = response.getWriter();
        out.print(studentName);
    }

}
