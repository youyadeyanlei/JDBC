package comm.mapper.impl;

import comm.mapper.ICustomersMapper;
import comm.pojo.Customers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersMapper implements ICustomersMapper {

    @Override
    public Customers mapping(ResultSet rs) throws SQLException {
        int customerId = rs.getInt("customer_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        Date birthDate = rs.getDate("birth_date");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        String city = rs.getString("city");
        String state = rs.getString("state");
        int points = rs.getInt("points");
        return new Customers(customerId, firstName, lastName, birthDate, phone, address, city, state, points);
    }
}
