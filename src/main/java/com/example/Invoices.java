package com.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Invoices {
    /**
     * 查询
     */

    @Test
    public void testSelectAll() throws Exception {


        Properties prop = new Properties();
        //获取链接池对象
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取Connection
        Connection conn = dataSource.getConnection();

        //sql
        String sql = " select * from invoices ";
        //获得pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置参数

        //执行
        ResultSet rs = pstmt.executeQuery();
        com.pojo.Invoices invoices =null;
        List<com.pojo.Invoices> list =new ArrayList< com.pojo.Invoices >();
        //处理结果
        while (rs.next()) {

            //获取数据
            int id = rs.getInt("customer_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            Date birth_date = rs.getDate("birth_date");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String state = rs.getString("state");
            int points = rs.getInt("points");
            //封装对象

             invoices =new com.pojo.Invoices();
            invoices.setCustomerId(id);
            invoices.setFirstName(first_name);
            invoices.setLastName(last_name);
            invoices.setBirthDate(birth_date);
            invoices.setPhone(phone);
            invoices.setAddress(address);
            invoices.setCity(city);
            invoices.setState(state);
            invoices.setPoints(points);

            //封装到集合
            list.add(invoices);

        }
        list.stream().forEach(System.out::println);
          //释放
        rs.close();
        pstmt.close();
        conn.close();
    }
    @Test
    public void testAdd() throws Exception {

        com.pojo.Invoices invoices1 = new com.pojo.Invoices();
//        invoices.setCustomerId();
        invoices1.setFirstName("gugu");
        invoices1.setLastName("gu");
        invoices1.setBirthDate( new java.util.Date());
        invoices1.setPhone("781-932-9754");
        invoices1.setAddress("chansg");
        invoices1.setCity("changs");
        invoices1.setState("CS");
        invoices1.setPoints(55555);

        Properties prop = new Properties();
        //获取链接池对象
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取Connection
        Connection conn = dataSource.getConnection();

        //sql
        String sql = "insert into invoices values (default,?,?,?,?,?,?,?,?)";
        //获得pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置参数

        pstmt.setString(1,invoices1.getFirstName());
        pstmt.setString(2,invoices1.getLastName());
        long time =invoices1.getBirthDate().getTime();
        pstmt.setDate(3,new Date(time));
        pstmt.setString(4,invoices1.getPhone());
        pstmt.setString(5,invoices1.getAddress());
        pstmt.setString(6,invoices1.getCity());
        pstmt.setString(7,invoices1.getState());
        pstmt.setInt(8,  invoices1.getPoints());


        //执行
        int count = pstmt.executeUpdate();


        //释放

        pstmt.close();
        conn.close();
    }




}
