package comm.Dao;

import comm.mapper.RowMapper;
import comm.utlis.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BEan<T> implements DbHelper {
    Connection conn=null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public List<T> findAll(String sql, RowMapper<T> rowMapper){
        List<T> list = new ArrayList<T>();
        try{
            conn=getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T mapping = rowMapper.mapping(rs);
              list.add(mapping);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close( conn,pstmt,rs);
        }
        return list;
    }
    public void delete(String sql,int id){
        try {
            conn=getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, pstmt, null);
        }

    }
    public void update(String sql,Object ...args){
       try {
           conn = getConnection();
           pstmt = conn.prepareStatement(sql);
           for (int i = 1; i <= args.length; i++) {
               pstmt.setObject(i,args[i-1]);
           }
           pstmt.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }finally {
           close(conn, pstmt, null);
       }
    }
}
