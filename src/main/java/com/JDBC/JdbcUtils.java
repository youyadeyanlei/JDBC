package com.JDBC;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties pro = new Properties();
        try {
            pro.load(ClassLoader.getSystemResourceAsStream("druid.properties"));
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }


    public static int update(String sql,Object...parms) {
        int count = 0;
        Connection con = null;
        PreparedStatement pstms = null;

        try {
            con = getConnection();
            if (parms != null){
                pstms = con.prepareStatement(sql);
                for (int i = 0; i < parms.length; i++) {
                    pstms.setObject(i + 1,parms[i]);
                }
                count = pstms.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(null,pstms,con);
        }
        return count;
    }


    public static <T> List<T> findAll(Class<T> cls, String sql, Object...params){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<T> list = new ArrayList<T>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1, params[i]);
                }
            }
            res = pstmt.executeQuery();

            //获取到数据库返回的元数据
            ResultSetMetaData metaData = res.getMetaData();
            //获取返回数据中的列数
            int columnCount = metaData.getColumnCount();

            while (res.next()){
                //通过对象的无参构造，来反射创建一个对象
                T t = cls.newInstance();
                //循环列数，为对象设置参数
                for (int i = 1; i <= columnCount; i++) {
                    //获取该列的列名
                    String cName = metaData.getColumnLabel(i);
                    //通过反射获取定义的属性，并暴力反射为该属性设置值
                    Field field = cls.getDeclaredField(cName);
                    field.setAccessible(true);
                    field.set(t,res.getObject(cName));
                }
                list.add(t);
            }

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    public static <T> T findByPro(Class<T> cls,String sql,Object...params){
        Connection con = null;
        PreparedStatement pstmst = null;
        ResultSet res = null;
        T t = null;
        try {
            con = getConnection();
            pstmst = con.prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmst.setObject(i + 1,params[i]);
                }
            }
            res = pstmst.executeQuery();
            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (res.next()){
                t = cls.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String cNmae = metaData.getColumnLabel(i);
                    Field field = cls.getDeclaredField(cNmae);
                    field.setAccessible(true);
                    field.set(t,res.getObject(cNmae));
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }



    public static void close(ResultSet res, Statement stmt, Connection con){
        if (res != null){
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (con != null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}

