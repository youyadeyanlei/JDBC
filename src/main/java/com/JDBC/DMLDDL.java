package com.JDBC;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DMLDDL {
    @Test
    public void DMLDDL() throws Exception {
        String URL="jdbc:mysql://localhost:3306/school?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        Connection conn = null;
        try {
            conn= DriverManager.getConnection(URL,"root","root");//建立和数据库的连接，并返回表示连接的Connection对象
            System.out.println("数据库连接成功!!!");
        }catch(Exception e) {//未连接成功，执行下面的异常处理
            System.out.println("数据库连接失败!!!");
        }
        String sql="select * from account";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            double money = rs.getDouble("money");
            System.out.println(id + "name"+name+"monry"+money);
            System.out.println("----------");
        }

        rs.close();
        statement.close();
        conn.close();

    }
}
