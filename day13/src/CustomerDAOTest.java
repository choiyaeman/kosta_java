import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOArray;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class CustomerDAOTest {
	public static void main(String[] args) {
		CustomerDAO dao;		
		dao = new CustomerDAOArray(2);
		
		//2.반복문외부에서 예외처리
		try {
			dao.insert(new Customer("id0","paaa") );
			for(int i=0; i<10; i++) {
				Customer c = new Customer("id"+i, "p"+i, "n"+i);
				dao.insert(c);
			}
		}catch(AddException e) {
//			//System.out.println("저장소가 꽉찼습니다. 현재인원은 " + dao.cnt+"입니다");
			System.out.println(e.getMessage());
		}
		
		Customer[] all;
		try {
			all = dao.selectAll();
			for(Customer c: all) {
				System.out.println(c.getId() + ":" + c.getName());
			}
		} catch (FindException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}		
	}
}