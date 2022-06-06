package com.rsd.servlet;

import com.rsd.service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/doDelete")
public class CalculatorDoDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CalculatorService calculatorService = new CalculatorService();
        calculatorService.delete();

        response.sendRedirect("/servlet/calculator");

    }
}
