/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * ����TCP�Ĳ������߳�������
 * 
 * 
 */
public class Server{
	private ServerSocket ss;
//	public static  Map<Socket,String> ssm = new HashMap<Socket,String>();
//	public static ArrayList<String> onlineUsers = new ArrayList<String>(); 
//	public static ArrayList<Socket> clientSocket = new ArrayList<Socket>();
	public Server(int port){
		try {
			ss = new ServerSocket(port);
			System.out.println("�����������ɹ���	port=" + ss.getLocalPort());
			while(true){
				Socket socket = ss.accept();
				
				ServerThread thread = new ServerThread(socket);//**********************
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			System.out.println(e.getMessage());
		}
		
	}

	public static void main(String[] args) {	
		OnlineUsers.ssm = new HashMap<String,Socket>();
		OnlineUsers.onlineUsers  = new ArrayList<String>();
		OnlineUsers.clientList = new ArrayList<Socket>();
		new Server(5439);
	}
}