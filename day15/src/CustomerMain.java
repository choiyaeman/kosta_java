import java.util.List;
import java.util.Scanner;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOList;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public class CustomerMain {
	private CustomerDAO dao = new CustomerDAOList(3);
	//Scanner타입의 sc멤버변수 선언
	private static Scanner sc = new Scanner(System.in);
	
	public void findAll() {
		System.out.println(">>1. 고객 전체 조회<<");
		try {
			List<Customer> cAll = dao.selectAll();			
			for(int i=0; i<cAll.size(); i++) {
				Customer c = cAll.get(i);
				System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
			}
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void add() {
		System.out.println(">>2. 고객추가<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
		System.out.print("이름를 입력하세요:");
		String name = sc.nextLine();
		try {
			dao.insert(new Customer(id, pwd, name));
		} catch (AddException e) {
			System.out.println(e.getMessage());
		}
	}
	public void findById() {
		System.out.println(">>3. 고객 ID로 조회<<");
		System.out.print("아이디를 입력하세요:");		
		String id = sc.nextLine();
		Customer c4;
		try {
			c4 = dao.selectById(id);
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		} catch (FindException e) {
			System.out.println(e.getMessage());
		}		
	}	
	public void modify() {
		System.out.println(">>4. 고객 정보 수정<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		try {
			dao.selectById(id);
			System.out.println("비밀번호를 입력하세요.수정안하려면 enter누르세요:");
			String pwd = sc.nextLine(); //enter인 경우 ""이 됨
			System.out.println("이름을 입력하세요.수정안하려면 enter누르세요:");
			String name = sc.nextLine();
			
			Customer c = new Customer(id, pwd, name);
			Customer c1 = dao.update(c);
			System.out.println("수정 성공!"+ c1.getId() + ", " + c1.getPwd() + ", " + c1.getName());
		}catch(FindException e) {
			System.out.println(e.getMessage());
		} catch (ModifyException e) {
			System.out.println(e.getMessage());
		}
	}
	public void remove() {
		System.out.println(">>5. 고객 정보 삭제<<");
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		try {
			dao.delete(id);
			System.out.println("삭제성공");
		} catch (RemoveException e) {
			System.out.println(e.getMessage());
		}
	}
	static public  void main(String[] args) {
		CustomerMain customerMain = new CustomerMain();
		while(true) {
			System.out.println("사용법: 1.고객전체조회, 2.고객추가, 3.고객 ID로 조회, 4.고객수정, 5.고객삭제,  9.종료");
			System.out.print("작업을 선택하세요:");
			String menu = sc.nextLine();
			//System.out.println(menu);
			if("1".equals(menu)) {
				customerMain.findAll();
			}else if("2".equals(menu)) {
				customerMain.add();
			}else if("3".equals(menu)) {
				customerMain.findById();
			}else if("4".equals(menu)) {
				customerMain.modify();
			}else if("5".equals(menu)) {
				customerMain.remove();
			}else if("9".equals(menu)) {				
				return;
			}
		}
	}
}
