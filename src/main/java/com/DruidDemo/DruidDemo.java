package com.DruidDemo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库链接池
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties prop = new Properties();
        //获取链接池对象
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
//        System.out.println(System.getProperty("user.dir"));

    }
}
