package com.Utils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

    public class JdbcUtil {
        private static Properties properties = new Properties();

        static {
            try {
                properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Class.forName(properties.getProperty("driver"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection () {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

        //调用方法的时候
        public static  void close (AutoCloseable ... closeables) {
            //关闭资源需要遵从，后使用先关闭
            for (int i = 0 ; i < closeables.length ; i++) {
                if (null != closeables[i]) {
                    try {
                        closeables[i].close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

    }
}
