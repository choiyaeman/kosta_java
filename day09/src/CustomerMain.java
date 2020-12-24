import java.util.Scanner;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

public class CustomerMain {
	private CustomerDAO dao = new CustomerDAO(3);
	//Scanner타입의 sc멤버변수 선언
	private static Scanner sc = new Scanner(System.in);
	
	public void findAll() {
		System.out.println(">>1. 고객 전체 조회<<");
		Customer[] cAll = dao.selectAll();
		if(cAll != null) {
			for(int i=0; i<cAll.length; i++) {
				Customer c = cAll[i];
				System.out.println(c.getId() + "," + c.getPwd() + ", " + c.getName());
			}
		}
	}
	
	public void add() {
		System.out.println(">>2. 고객추가<<");
		//dao.insert(new Customer("id1", "p1", "n1"));
		//Scanner sc = new Scanner(System.in);
		
		System.out.print("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요:");
		String pwd = sc.nextLine();
		System.out.print("이름를 입력하세요:");
		String name = sc.nextLine();
		dao.insert(new Customer(id, pwd, name));
	}
	public void findById() {
		System.out.println(">>3. 고객 ID로 조회<<");
		
		//사용자로부터 입력받은 아이디로 조회한다
		//Scanner sc = new Scanner(System.in);
		System.out.print("아이디를 입력하세요:");
		
		String id = sc.nextLine();
		
		Customer c4 = dao.selectById(id);
		if(c4 != null) { //이곳이 출력
			System.out.println(c4.getId() + "," + c4.getPwd() + ", " + c4.getName());
		}else {
			System.out.println("ID가 존재하지 않습니다");
		}
	}	
	static public  void main(String[] args) {
		CustomerMain customerMain = new CustomerMain();
		//Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("사용법: 1.고객전체조회, 2.고객추가, 3.고객 ID로 조회, 9.종료");
			System.out.print("작업을 선택하세요:");
			String menu = sc.nextLine();
			//System.out.println(menu);
			if("1".equals(menu)) {
				customerMain.findAll();
			}else if("2".equals(menu)) {
				customerMain.add();
			}else if("3".equals(menu)) {
				customerMain.findById();
			}else if("9".equals(menu)) {				
				return;
			}
		}
	}
}
