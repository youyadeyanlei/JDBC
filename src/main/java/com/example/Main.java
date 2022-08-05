package com.example;

import org.junit.Test;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        //1.加载驱动
        // Class.forName("com.mysql.cj.jdbc.Driver");//可以不用写，自动加载，驱动版本高于4
        //2.创建链接
        String url = "jdbc:mysql://localhost:3306/sql_store?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "root";
        ResultSet resultSet = null;
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            //3.准备sql
            String sql = "select * from customers";
            //4.编译
            statement = con.createStatement();
            //5.执行
            statement.execute(sql);

            //6.获取结果

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int c_id=    resultSet.getInt("customer_id");
                String name = resultSet.getString("first_name");
                Date date = resultSet.getDate("birth_date");
                int points = resultSet.getInt("points");
                System.out.println(c_id + ":"+name+":"+date + ":"+points);
            }
            //7.关闭资源
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (con != null) con.close();

        }
    }
}
