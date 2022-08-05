package comm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapping(ResultSet rs) throws SQLException;
}
