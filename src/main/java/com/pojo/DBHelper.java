package com.pojo;

import java.sql.*;

public class DBHelper {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sql_store?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8 ";
    private static final String uSERNAME = "root";
    private static final String PASSWORD = "root";

    //注册驱动
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

    //创建数据库链接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, uSERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //关闭链接
    public static void close(Statement stmt, Connection conn) {
        close(stmt, conn);
    }

    public static void close(ResultSet resultSet, Statement stmt, Connection conn) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
