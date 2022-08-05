package comm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // 在程序运行期间自动生成getter&setter、toString、hashCode、equals
@NoArgsConstructor // 默认构造器、无参构造器
@AllArgsConstructor // 全参构造器
public class Customers implements BaseBean {

    private int customerId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int points;

}
