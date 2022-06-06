package com.rsd.util;

import com.rsd.anno.Nonparticipation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = Logger.getLogger(JDBCUtil.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动时发生异常！" + e.getMessage());
        }
    }

    public static String selectSQL(Class clazz) {
        String name = clazz.getSimpleName();//getSimpleName只要后面名，没有前缀

        String tableName = "bu";
        String[] fs = name.split("(?=[A-Z])");
        for (String f : fs) {
            tableName += "_" + f.toLowerCase();//toLowerCase变成全小写的
        }
        return "select * from " + tableName;
    }

    public static String deleteSQL(Object obj) {
        Class clazz = obj.getClass();
        String simpleName = clazz.getSimpleName();

        String tableName = "bu";
        String[] strs = simpleName.split("(?=[A-Z])");
        for (String str : strs) {
            tableName += "_" + str.toLowerCase();
        }

        Integer id = null;
        try {
            Method method = clazz.getMethod("getId");
            id = (Integer) method.invoke(obj);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "delete from " + tableName + " where id=" + id;
    }

    public static String insertSQL(Object obj) {
        String tableName = "bu";
        String fields = "";
        String values = "";
        Class clazz = obj.getClass();
        String clazzName = clazz.getSimpleName();
        String[] strs = clazzName.split("(?=[A-Z])");
        for (String str : strs) {
            tableName += "_" + str.toLowerCase();//toLowerCase变成全小写的
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && !methodName.equals("getId") && !methodName.equals("getClass")) {

                try {
                    Object value = method.invoke(obj);
                    if (value != null) {
                        if (method.getReturnType().getName().equals(Integer.class.getName())) {
                            values += "," + value;
                        }
                        if (method.getReturnType().getName().equals(String.class.getName())) {
                            values += ",'" + value + "'";
                        }
                        if (method.getReturnType().getName().equals(Date.class.getName())) {
                            values += ",'" + sdf.format(value) + "'";
                        }

                        methodName = methodName.replace("get", "");

                        String filed = "";
                        String[] mStrs = methodName.split("(?=[A-Z])");
                        for (String mStr : mStrs) {
                            filed += "_" + mStr.toLowerCase();
                        }
                        filed = filed.substring(1);

                        fields += "," + filed;
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        fields = fields.substring(1);
        values = values.substring(1);
        return "insert into " + tableName + "(" + fields + ") values(" + values + ")";
    }

    public static String updateSQL(Object obj) {
        String tableName = "bu";
        Integer id = null;
        String filedAadValues = "";

        try {
            Class clazz = obj.getClass();
            String clazzName = clazz.getSimpleName();

            String[] strs = clazzName.split("(?=[A-Z])");
            for (String str : strs) {
                tableName += "_" + str.toLowerCase();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getId") && !methodName.equals("getClass")) {
                    Object value = method.invoke(obj);
                    Nonparticipation annotation = method.getAnnotation(Nonparticipation.class);
                    if (annotation == null || annotation.supportSQL()) {
                        methodName = methodName.replace("get", "");
                        String[] mstrs = methodName.split("(?=[A-Z])");
                        String temp = "";
                        for (String str : mstrs) {
                            temp += "_" + str.toLowerCase();
                        }

                        methodName = temp.substring(1);
                        if (method.getReturnType().getName().equals(String.class.getName())) {
                            filedAadValues += "," + methodName + "='" + value + "'";
                        }
                        if (method.getReturnType().getName().equals(Integer.class.getName())) {
                            filedAadValues += "," + methodName + "=" + value;
                        }
                        if (method.getReturnType().getName().equals(Date.class.getName())) {
                            filedAadValues += "," + methodName + "='" + sdf.format(value) + "'";
                        }
                    }
                }
            }
            filedAadValues = filedAadValues.substring(1);

            Method method_getId = clazz.getMethod("getId");
            id = (Integer) method_getId.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "update " + tableName + " set " + filedAadValues + " where id=" + id;
    }

    public static void execute(String sql) {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            logger.error("执行SQL时发生异常！" + e.getMessage());
        } finally {
            close(connection);
        }
    }

    public static <T> List<T> queryList(Class<T> clazz, String sql) {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnTypeName = metaData.getColumnTypeName(i);

                    Object value = null;
                    if (columnTypeName.equals("VARCHAR")) {
                        value = rs.getString(columnName);
                    }
                    if (columnTypeName.equals("INT")) {
                        value = rs.getInt(columnName);
                    }
                    if (columnTypeName.equals("DATETIME")) {
                        value = rs.getTimestamp(columnName);
                    }

                    String temp = "";
                    String[] strs = columnName.split("_");
                    for (String str : strs) {
                        temp += str.substring(0, 1).toLowerCase() + str.substring(1);
                    }
                    columnName = temp.substring(0, 1).toLowerCase() + temp.substring(1);

                    BeanUtils.setProperty(t, columnName, value);
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static<T> T queryById(Class<T> clazz, String sql) {
        T t = null;
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();

            if (rs.next()) {
                t = clazz.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnTypeName = metaData.getColumnTypeName(i);

                    Object value = null;
                    if (columnTypeName.equals("VARCHAR")) {
                        value = rs.getString(columnName);
                    }
                    if (columnTypeName.equals("INT")) {
                        value = rs.getInt(columnName);
                    }
                    if (columnTypeName.equals("DATETIME")) {
                        value = rs.getTimestamp(columnName);
                    }

                    String temp = "";
                    String[] strs = columnName.split("_");
                    for (String str : strs) {
                        temp += str.substring(0, 1).toLowerCase() + str.substring(1);
                    }
                    columnName = temp.substring(0, 1).toLowerCase() + temp.substring(1);

                    BeanUtils.setProperty(t, columnName, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
        } catch (SQLException e) {
            logger.error("在连接数据库时时发生异常！" + e.getMessage());
        }
        return connection;
    }

    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("在关闭连接时发生异常！" + e.getMessage());
            }
        }
    }


}
