import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeyboardIO {

	public static void main(String[] args) {
		//키보드자원
		InputStream is = System.in;
//		try {
//			int readValue = is.read();//1byte읽어서 int타입으로 형변환
//			char c = (char)readValue;
//			System.out.println("byte=" + (readValue)+ ",char=" + c);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//Reader r = (Reader)System.in;
		InputStreamReader isr = new InputStreamReader(is);
//		try {
//			while(true) {
//				int readValue = isr.read();//1char읽어서 int타입으로 형변환
//				if(readValue == -1) {
//					break;
//				}
//			int readValue = -1;
//			while((readValue = isr.read()) != -1) {
//				char c = (char)readValue;
//				System.out.println("byte=" + (readValue)+ ",char=" + c);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		Scanner sc = new Scanner(System.in);
                            		//입력값예:A BC	가나다
//		String str = sc.next();     //A
//		System.out.println(str);
//		
//		String str1 = sc.next();    //BC
//		System.out.println(str1);
		                             //입력값예:A BC	가나다
//		String str = sc.nextLine();  //A BC	가나다
//		System.out.println(str);
//                            		//입력값예:엔터값
//		String str1 = sc.nextLine();//"" 
//		System.out.println(str1);    
									//입력값예:1
		                            //       2
		int a = sc.nextInt();
		System.out.println(a);
		int b = sc.nextInt();
		System.out.println(b);
		String str = sc.nextLine(); 
		System.out.println("str=" + str);
		
		//주소입력받기 : sc.next()X  sc.nextLine()O
		//nextInt()와 nextLine()혼용하면 불편
		//nextLine()로만 사용
		//nextLine()으로 입력받은 숫자문자열(ex: "123")은 int타입으로 변환해서 사용한다
		//String numStr = sc.nextLine();
		//int num = Integer.parseInt(numStr);
		
		
	}
}
