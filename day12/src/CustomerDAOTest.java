import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOArray;
import com.my.vo.Customer;
public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO dao;
		//dao = new CustomerDAO();
		dao = new CustomerDAOArray(); //upcasting
		Customer[] all = dao.selectAll();
		if(all != null) {
			for(Customer c: all) {
				System.out.println(c.getId() + ":" + c.getName());
			}
		}
		
	}
}