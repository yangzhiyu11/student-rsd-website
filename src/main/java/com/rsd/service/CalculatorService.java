package com.rsd.service;

import com.rsd.bean.Calculator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Float operation(Calculator calculator) {
        Float nub3 =null;
        Float nub1 = Float.valueOf(calculator.getNub1());
        Float nub2 = Float.valueOf(calculator.getNub2());
        if (calculator.getMyselect().equals("+")) {
            nub3= nub1+nub2;
        }else if (calculator.getMyselect().equals("-")) {
            nub3= nub1-nub2;
        }else if (calculator.getMyselect().equals("*")) {
            nub3= nub1*nub2;
        }else if (calculator.getMyselect().equals("/")) {
            nub3= nub1/nub2;
        }
        return nub3;
    }

    public void insert(Calculator calculator) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            String str = calculator.getNub1() + calculator.getMyselect() + calculator.getNub2() + "=" + calculator.getNub3();
            String sql = "INSERT INTO bu_calculator VALUES (null,'"+str+"')";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    public List<String> queryHistoryList() {
        List<String> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_calculator order by id desc ");

            while (rs.next()) {
                String operation = rs.getString("operation");

                list.add(operation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return list;
    }

    public void delete(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "delete from bu_calculator";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
