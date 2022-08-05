import comm.Dao.CustomersDao;
import comm.mapper.impl.CustomersMapper;
import comm.pojo.Customers;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Demo01 {

    @Test
    public void test() throws SQLException {
        CustomersDao dao = new CustomersDao();


//         List<Customers> list = dao.findAll("select * from customers", new CustomersMapper());
//         list.forEach(System.out::println);
//        dao.saveOrUpdate(
//                "insert into customers values(null,?,?,?,?,?,?,?,?)",
//                "Gavin", "King", new Date(), "000-000", "HN", "CS", "YL", 3000);

//                 dao.delete("delete from customers where customer_id = ?", 13);
//         dao.saveOrUpdate(
//                 "update customers set first_name = ?, birth_date = ?, points = ? where customer_id = ?",
//                 "Gavin", "1990-01-01", 3000, 1);

    }
}
