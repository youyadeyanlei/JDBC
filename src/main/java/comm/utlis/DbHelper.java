package comm.utlis;

import java.sql.*;

public interface DbHelper {

    String classNaem ="com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/sql_store";
    String user ="root";
    String password = "root";
    default Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    default void close(Connection conn, Statement stmt, ResultSet rs){
        try {
            if (rs != null)rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null)stmt.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (conn != null)conn.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
