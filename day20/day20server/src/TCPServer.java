import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket ss = null;
		int port = 5432;
		Socket s = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			ss = new ServerSocket(port);
			while(true) {
				s = ss.accept();
				String clientName =  
						s.getInetAddress().getHostAddress();
				try {
					System.out.println(clientName + "가 접속됨!");
					dis = new DataInputStream(s.getInputStream());	
					dos = new DataOutputStream(s.getOutputStream());
					
					String receiveMsg = "quit";
					while( !(receiveMsg = dis.readUTF()).equals("quit")){
						System.out.println(clientName + "가 보낸 msg >" + receiveMsg);
						dos.writeUTF(receiveMsg);
					}
				} catch(EOFException e) {//클라이언트가 강제종료한 경우
				} catch(SocketException e) {//클라이언트가 강제종료한 경우	
				} catch (IOException e) {
					e.printStackTrace();
				} finally {					
					System.out.println(clientName +"가 접속해제했습니다");
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
							e.printStackTrace();
						}
					}
				}
			}//while			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {			
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}