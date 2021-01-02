import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
		//원본파일명 : day15\src\CustomerMain.java
		String originFileName = "d:\\omj\\myse\\day15\\src\\CustomerMain.java";
		
		//대상파일명 : CustomerMainCopy.java
		String destFileName = "CustomerMainCopy.java";
		
		//0.대상파일 쓰기
		// 1)파일문자단위출력스트림객체생성

		//1.원본파일 읽기
		// 1)파일문자단위입력스트림객체생성
		// 2)읽기
		// 3)0-1)에 쓰기
		
		//2.자원,목적지와의 연결해제
		
		FileWriter fw = null;
		FileReader fr = null;
		try {
			fw = new FileWriter(destFileName);
			fr = new FileReader(originFileName);
			int readValue = -1;
			char[] arr = new char[100];
			while(true) {
				int cnt = fr.read(arr, 0, arr.length);
				if(cnt == -1) {
					break;
				}
				//fw.write(arr);
				fw.write(arr, 0, cnt);
			}
			//while((readValue = fr.read()) != -1){
			//	fw.write(readValue);
			//}			
		} catch (IOException e) {
			e.printStackTrace();			
		} finally {
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
