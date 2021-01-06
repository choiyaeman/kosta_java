import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	public static void main(String[] args) {
		Socket s=null;
		String serverIP = "192.168.0.17";
		int serverPort = 5432;//5555;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		Scanner sc = null;
		try {
			s = new Socket(serverIP, serverPort);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			//dos.writeUTF("안녕하세요 오문정강사입니다~~~");
			
			sc = new Scanner(System.in);
//			while(true) {
//				String keyboardLine = sc.nextLine();			
//				dos.writeUTF(keyboardLine);
//				if(keyboardLine.equals("quit")) {
//					break;
//				}
//			}
			String keyboardLine = "quit";
			do {
				keyboardLine = sc.nextLine();//키보드입력하기
				dos.writeUTF(keyboardLine); //송신
				String receiveMsg = dis.readUTF(); //수신
				System.out.println("ECHO>" + receiveMsg);
			}while(!keyboardLine.equals("quit"));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();		
		} catch(ConnectException e) {
			System.out.println("서버가 켜졌는지, 또는 포트번호가 정확한지 확인하세요");
		} catch(SocketException e) {
			System.out.println("서버와의 연결이 끊겼습니다");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sc != null) {
				sc.close();
			}
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {					
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {					
				}
			}
		}
	}
}