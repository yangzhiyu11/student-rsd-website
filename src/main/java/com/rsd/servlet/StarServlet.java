package com.rsd.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tool/star")
public class StarServlet extends HttpServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String showNumber = request.getParameter("showNumber");

        String stars= "";
        int lineNumber = Integer.valueOf(showNumber);
        for (int i = 1; i<=lineNumber; i++) {
            for (int j = 0; j< i ;j++){
                stars += "*";
            }
            stars += "<br/>";
        }

        request.setAttribute("stars",stars);

        List<String> starList = new ArrayList<>();
        for (int i = 1; i<=lineNumber; i++) {
            String s = "";
            for (int j =0; j<i; j++){
                s += "*";
            }
            starList.add(s);
        }

        request.setAttribute("starList",starList);

        List<String> ssList = new ArrayList<>();
        for (int i = 1 ;i<=9; i++){
            String sss = "";
            for (int j = 1 ; j<=i;j++){
                sss += j+"*"+i+"="+(j*i)+"  ";
            }
            ssList.add(sss);
        }

        request.setAttribute("ssList",ssList);
        request.setAttribute("showNumber",showNumber);
        request.getRequestDispatcher("/tool/star.jsp").forward(request,response);
    }
}
