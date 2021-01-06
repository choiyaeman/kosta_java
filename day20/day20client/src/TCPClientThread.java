import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
class ClientThread extends Thread{
	Socket s;
	DataInputStream dis = null;
	ClientThread(Socket s) throws IOException{
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
	}
	public void run(){
		try {
			while(true) {
				String receiveMsg;
				receiveMsg = dis.readUTF();//수신
				System.out.println("ECHO>" + receiveMsg);
			}
		} catch (IOException e) {
		} finally {			
		}
	}
}
public class TCPClientThread {
	public static void main(String[] args) {
		Socket s=null;
		String serverIP = "192.168.0.17";
		int serverPort = 5432;//5555;
		DataOutputStream dos = null;
		Scanner sc = null;
		try {
			s = new Socket(serverIP, serverPort);
			ClientThread t = new ClientThread(s);
			t.start();
			dos = new DataOutputStream(s.getOutputStream());			
			sc = new Scanner(System.in);
			String keyboardLine = "quit";
			do {
				keyboardLine = sc.nextLine();//키보드입력하기
				dos.writeUTF(keyboardLine); //송신				
			}while(!keyboardLine.equals("quit"));
		} catch(EOFException e){
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