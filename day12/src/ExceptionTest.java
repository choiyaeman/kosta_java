
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	public static void test(int a) {
		int result =5/a;
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요:");
		try {
			int num = sc.nextInt(); //0->ArithmeticException
		                        //a->InputMisMatchException
			if(num != 0) {
				test(num);
			}
		}catch(InputMismatchException e) {
			System.out.println("숫자가 입력되지 않았습니다. 정확히 숫자로 입력하세요");
		}
	}

}
