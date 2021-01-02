import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	public static void writeTest() {
		String fileName = "a.txt";
		
		//파일바이트단위출력스트림
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(65);
			fos.write('B');
			fos.write('1');
			byte[] bytes = "가".getBytes();
//			for(byte b: bytes) {
//				fos.write(b);
//			}
			fos.write(bytes);
			//fos.write('가');//버퍼에 쓰기됨, 목적지에 전달안됨
			fos.flush();//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileName2 = "d:\\b.txt"; //\n \t \b:백슬래시특수문자
		                                //\\:일반\문자
		//파일 문자단위 출력스트림
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName2);//???
//			fw.write("A");
//			fw.write("B");
//			fw.write("1");
//			fw.write("가");
			fw.write("AB1가");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) {
		String fileName = "a.txt";
		//파일문자입력스트림이용해서 파일내용 읽기
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			int readValue = -1;
			while((readValue = fr.read()) != -1) {
				System.out.print((char)readValue);
			}
			//fr.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			//fr.close();
		} catch (IOException e) {
			e.printStackTrace();
			//fr.close();
		} finally {
			if(fr != null) {
				try {				
					fr.close();
				}catch(Exception e) {
				}
			}
			
		
//			try {							
//				if(fr != null) {
//					fr.close();
//				}
//			}catch(Exception e) {
//			}

			
			
		}
		
		
		//파일 바이트입력스트림
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
