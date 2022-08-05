package comm.utlis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//数据库连接池工具类
public class DruidUtils {

    private static DruidDataSource druidDataSource;

    static
    {

        try {
            InputStream is=DruidUtils.class.getResourceAsStream("druid.properties");
            Properties properties=new Properties();
            properties.load(is);
            druidDataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //获取连接池数据源
    public static DataSource getDataSource()
    {
        return druidDataSource;
    }


    //从数据库连接池获取连接对象
    public static Connection getConnection()
    {
        Connection connection=null;
        try {
            connection=druidDataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

}