package comm.Dao;

import comm.mapper.RowMapper;
import comm.utlis.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> implements DbHelper {
    Connection conn=null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public List<T> findAll(String sql, RowMapper<T> mapper) throws SQLException {
        List<T> list = new ArrayList<T>();
     //   Connection conn = null;
      //  PreparedStatement pstmt = null;
      //  ResultSet rs = null;

        try {
            conn = getConnection();
//           conn = JdbcUtil.getConnection();
 //           conn= DruidUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T mapping = mapper.mapping(rs);
                list.add(mapping);
            }
        }finally {
            close(conn,pstmt,rs);
  //      JdbcUtil.close(conn,pstmt,rs);
        }

        return list;
    }
    public void saveOrUpdate(String sql,Object ...aObjects)throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 1; i <= aObjects.length; i++) {
                pstmt.setObject(i,aObjects[i-1]);

            }pstmt.executeUpdate();
        }finally {
            close(conn, pstmt, null);
        }

    }

    public void delete(String sql,int id)throws SQLException {
        try {
            conn =getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        }finally {
            close(conn, pstmt, null);
        }
    }


}
