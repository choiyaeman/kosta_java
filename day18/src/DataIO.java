import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIO {

	public static void main(String[] args) {
		String fileName = "a.dat";
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			//fos.write
			dos.writeShort(1);
			dos.writeInt(2);
			dos.writeLong(3);
			dos.writeUTF("HELLO");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			short s = dis.readShort();
			int i = dis.readInt();
			long l = dis.readLong();
			String str = dis.readUTF();
//			int i = dis.readInt();
//			long l = dis.readLong();
//			short s = dis.readShort();
			System.out.println(s + ":" + i + ":" + l + ":" + str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		

	}

}
