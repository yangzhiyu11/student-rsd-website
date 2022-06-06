package com.rsd.servlet;

import com.rsd.bean.Calculator;
import com.rsd.service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.List;

@WebServlet("/servlet/calculator")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Calculator calculator = null;
        CalculatorService calculatorService = new CalculatorService();
        if (request.getParameter("nub1") != null) {
            String nub1 = request.getParameter("nub1");
            String nub2 = request.getParameter("nub2");
            String myselect = request.getParameter("myselect");


            calculator = new Calculator();
            calculator.setNub1(nub1);
            calculator.setNub2(nub2);
            calculator.setMyselect(myselect);


            Float nub3 = calculatorService.operation(calculator);
            calculator.setNub3(NumberFormat.getInstance().format(nub3));

            calculatorService.insert(calculator);
        }


        List<String> historyList = calculatorService.queryHistoryList();

        request.setAttribute("calculator",calculator);
        request.setAttribute("historyList",historyList);
        request.getRequestDispatcher("/tool/calculator.jsp").forward(request,response);
    }
}
