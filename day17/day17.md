# day17

```java
package day17;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeyboardIO {
	
	public static void main(String[] args) {
		//키보드 자원
		InputStream is = System.in;
//		try {
//			int readValue = is.read(); //1byte읽어서 int타입으로 형변환 //enter값이 눌러질때까지 기다린다. 문자ABC를 넣어도 첫 바이트(A)를 readValue변수에 대입 //컴파일에서 통과되지 않는 IOException발생하므로 try~catch해주자
//			char c = (char)readValue;
//			System.out.println("byte=" + (readValue) + ", char=" + c); 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Reader r = (Reader)System.in;  //(X)전혀 별개의 타입이기대문에 못쓴다
//		InputStreamReader isr = new InputStreamReader(is);
//		try {
////			while(true) { //사용자로부터 ctrl+z누를때 까지 반복
////				int readValue = isr.read(); //1char읽어서 int타입으로 형변환 // readValue에는 문자단위로 읽어 값이 int타입으로 upcasting readValue에 담아온다.
////				if(readValue == -1) {
////					break;
////				}
//			int readValue = -1;
//			while((readValue = isr.read()) != -1) { // is.read()값을 readValue에 넣고 -1이 아니면 반복해라
//				char c = (char)readValue;
//				System.out.println("byte=" + (readValue) + ", char=" + c); 
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Scanner sc = new Scanner(System.in);
//		String str = sc.next(); //엔터값 바로앞에 있는 내용들을 읽어 올 수 있다.
//		System.out.println(str);
		
		Scanner sc = new Scanner(System.in); 
								//입력값예:A BC	가나다 
//		String str = sc.next();
//		System.out.println(str); //A
//		
//		String str1 = sc.next();
//		System.out.println(str1); //BC
		
									//입력값예:A BC	가나다
//		String str = sc.nextLine();	//A BC 가나다
//		System.out.println(str);
//									//입력값예:엔터값
//		String str1 = sc.nextLine();//""
//		System.out.println(str1);
								//입력값예:1
								//      2
		int a = sc.nextInt();
		System.out.println(a);
		int b = sc.nextInt();
		System.out.println(b);
		String str = sc.nextLine();
		System.out.println("str=" + str); //str의 값이 빈 문자열.. 1,2값은?? 소모가되서 빈 문자열만 출력한다.
		//주소 입력받기 : sc.next() (X) , sc.nextLine() (O)
		//nextInt()와 nextLine()혼용하면 불편
		//nextLine()로만 사용
		//nextLine()으로 입력받은 숫자 문자열(ex: "123")은 int타입으로 변환해서 사용한다
		//String numStr = sc.nextLine();
		//int num = Integer.parseInt(numStr);

		
	}
}
```

### **next()**

-공백이나 탭을 제외한 컴플릿 토큰을 반환하는 메소드

ex) A BC 일 경우 A만 반환, 또 sc.next()를 하면 공백을 제외한 BC가 반환 된다.

### **nextLine()**

-줄 바꿈은 스킵. 엔터값 바로 앞에 내용만 반환하는 메소드

ex) A BC 일 경우 ABC반환

### nextInt()

-int타입으로 값을 반환하는 메소드

nextLine메소드랑 nextInt메소드를 섞어 쓰면 굉장히 위험할 수 있다. 엔터값을 nextLine에서 소모를 할 수 있으므로

---

```java
package day17;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	public static void writeTest() {
		String fileName ="a.txt"; //경로를 지정하지 않으면  현재경로이다. 현재경로를 정확히 알리려면 -> ./ , 상위경로는 -> ../
		
		//파일 바이트 단위 출력 스트림
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(65); //A <-65에 해당하는 문자
			fos.write('B'); //B
			fos.write('1'); //1
			byte[] bytes = "가".getBytes(); //가를 구성했던 2바이트를 반복수행하자, utf8로 설정했기 때문에 실제 가라는 문자는 3byte로 처리 되어있다
//			for(byte b : bytes) {
//				fos.write(b);
//			}
			fos.write(bytes);
			//fos.write('가'); //2byte이상 쓰여지게되면 버퍼에 쓰기됨, 목적지에 전달안됨.
							//가라는 문자는 2byte로 구성되어있다. write가 하는일은 1byte만 쓴다 
			fos.flush(); //버퍼의 내용을 즉각 목적지에 전달하겠다는 뜻
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileName2 = "d:\\b.txt"; // \n \t \b 가 붙는 문자는 특수문자로 인식한다. 파일 이름으로 적합하지않다. \\를 써야한다.
		//파일 문자단위 출력 스트림
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName2);
//			fw.write("A");
//			fw.write("B");
//			fw.write("1");
//			fw.write("가");
			fw.write("AB1가");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch(Exception e) {
				}
			}
		}
	
	}
	public static void main(String[] args) {
		String fileName = "a.txt";
		//파일 문자 입력스트림 이용해서 파일 내용 읽기
		FileReader fr = null;
		try {
			fr = new FileReader(fileName); //a.txt자원과 fr과 연결되는거다라는 의미 //file을 찾지 못하면 FileNotFoundException 발생 catch구문으로 이동
			int readValue = -1;
			while((readValue = fr.read()) != -1) { //fr.read()메소드를 이용해서 계속 반복하면서 한문자 한문자 반환 하면서 -1되면 종료
				System.out.print((char)readValue);
			}
			fr.close(); //자원과의 연결을 닫는다라는 의미.
		} catch (FileNotFoundException e) {
			//e.printStackTrace(); //디버깅 테스트할 때 쓰인다.
			System.out.println(e.getMessage());
		} catch (IOException e) { // file을 읽다가 문제가 발생할 경우. 즉 다른 파일을 읽다가 갑자기 컴퓨터가 꺼져버리면 read를 더 이상 못할때 발생
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch(Exception e) {
				}
			}
		}
		
		//파일 바이트 입력스트림
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(fileName);
//			int readValue = -1;
//			while( (readValue = fis.read()) != -1) {
//				char c = (char)readValue;
//				System.out.print(c);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
```

![day17%207b044a90e0e74734a1283f8a2fac6416/Untitled.png](day17%207b044a90e0e74734a1283f8a2fac6416/Untitled.png)

a.txt파일은 day17프로젝트 경로에 만들어진다. 

eclipse에서 실행하는 거랑 자바 명령어로 D:~~day17>java -cp bin FileIO 방법과 똑같다. 실행 경로 기준점이 day17 실행을 했기 때문에 a.txt파일 만들어진 경로도 day17 프로젝트 경로에 만들어지는 거다.

![day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%201.png](day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%201.png)

close()는 자원과의 연결을 끊기 위해서 필요한 메소드이다.

JVM을 바꾸게 될 때 fr.close(); 코드가 없으면 JVM이 fr객체를 쓸모없는 메모리에 남길 수 있다. 계속 자원과의 연결을 할 수 있다. 그러므로 반드시 close를 해줘야 한다.

fr이 참조하고 있는 객체가 있고 그 객체는 FileReader타입 객체이고 그 안쪽에 a.txt파일이 있는 거다

fr과 연결되어 있는 자원을 끊는 것을 close라 한다. catch구문에서도 close를 해주는게 바람직하다. 그러나 각각 쓸 필요 없이 맨 아래 finally에 close해주면 된다. 컴파일이 성공되나 실행시에 nullpointexception에 빠질 수 있는 코드이므로 fr이 null 아닐 때만 close를 해라! 마지막에도 try~catch 해준다.

![day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%202.png](day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%202.png)

전달된 값이 배열 총 8개방중에  arr의 2번 인덱스부터 3개 만큼 넣는다. read메서드의 리턴 타입은 int타입. 사이즈 값은 3이다.

![day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%203.png](day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%203.png)

위와 동일하게 전달된 값이 배열 총 8개 방 중에  arr의 2번 인덱스부터 3개 만큼 넣는다. 사이즈 값은 3이다. read메소드가 한번 더 호출됨으로 a가 없어지고 1 b가 없어지고 2하고 끝 사이즈는 2

```java
package day17;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
		//원본파일명: day15\src\CustomerMain.java
		String originFileName = "d:\\cym\\myse\\day15\\src\\CustomerMain.java";
		
		//대상파일명 : CustomerMainCopy.java
		String destFileName = "CustomerMainCopy.java";
		
		
		//0.대상파일 쓰기
		// 1)파일 문자단위 출력 스트림 객체생성
		
		//1.원본파일 읽기
		// 1)파일 문자단위 입력 스트림 객체생성
		// 2)읽기
		// 3)0-1)에 쓰기
		
		//2.자원, 목적지와의 연결해제
		
		FileWriter fw = null;
		FileReader fr = null;
		try {
			fw = new FileWriter(destFileName);
			fr = new FileReader(originFileName);
//			int readValue = fr.read();
//			fw.write(readValue);
			int readValue = -1;
			while((readValue = fr.read()) != -1) {
				fw.write(readValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 지전분해도 안전하게 각각 try~catch로 close해라!
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
```

![day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%204.png](day17%207b044a90e0e74734a1283f8a2fac6416/Untitled%204.png)

```java
package day17;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
		//원본파일명: day15\src\CustomerMain.java
		String originFileName = "d:\\cym\\myse\\day15\\src\\CustomerMain.java";
		
		//대상파일명 : CustomerMainCopy.java
		String destFileName = "CustomerMainCopy.java";
		
		
		//0.대상파일 쓰기
		// 1)파일 문자단위 출력 스트림 객체생성
		
		//1.원본파일 읽기
		// 1)파일 문자단위 입력 스트림 객체생성
		// 2)읽기
		// 3)0-1)에 쓰기
		
		//2.자원, 목적지와의 연결해제
		
		FileWriter fw = null;
		FileReader fr = null;
		try {
			fw = new FileWriter(destFileName);
			fr = new FileReader(originFileName);
			int readValue = -1;
			char[] arr = new char[100]; // null값이 채워질거다
			while(true) {
				int cnt = fr.read(arr, 0, arr.length); // 100개의 문자를 원본에서  읽어서 배열의 0번 인덱스부터 채운다.
				if(cnt == -1) { //cnt는 읽어온 문자개수
					break;
				}
				//fw.write(arr); //(X) 이전에 쓰였던 내용이 더 쓰여질수있다
				fw.write(arr, 0, cnt); //offset시작점 , length개수 . 0번 인덱스부터 읽어온 문자개수만큼만 쓰기
			}
//			while((readValue = fr.read()) != -1) {
//				fw.write(readValue);
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 지전분해도 안전하게 각각 try~catch로 close해라!
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
```

read메소드를 만날 때마다 자원을 찾아간다 한문자씩 읽으면서.. 근데 이 읽는 작업이 속도가 떨어진다. 

fw.write(arr)하면 100개 채워서 읽고 쓰기 다 했습니다. 100개의 문자를 읽어야 하는데 문자의 개수가 57개 읽었다면 배열 0~56번 방까지 새로운 내용을 채우겠죠. 그러면 나머지 57~99번 방까지 내용은 이전 내용이 있는 상태로 유지된 상태이므로 fw.write(arr, 0, cnt)로 0번부터 읽어온 문자 개수만큼 쓰기를 해야 한다.