/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import tcpchatroom.datagram.ServerDataGramAnalyzer;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * �ͻ������߳�
 * ���շ������˷�������Ϣ����ʾ
 *
 */

public class ClientThread extends Thread{
	private Socket socket;
	private BufferedReader in;
	private JTextArea textArea;
	private JTextArea textArea2;
	
	
	public ClientThread(JTextArea area,JTextArea area2, Socket socket,BufferedReader in) throws IOException{
		this.textArea = area;
		this.textArea2 = area2;
		this.socket = socket;
		this.in = in;
	}

	public void run(){
		try {
			//ѭ����ȡ
			while(!this.socket.isClosed()){
				String stocdatagram = in.readLine();//************************�׳��쳣
				switch(ServerDataGramAnalyzer.getType(stocdatagram)){
				
				
				case(0)://�յ���ͨ��Ϣ
//					System.out.println("���յ�server ��Ϣ:" + stocdatagram);
					//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+ ":"  + ServerDataGramAnalyzer.getMessage(stocdatagram)+"\n");
					break;
				
				case(4)://ע��ɹ�
				case(1)://��½�ɹ� ���ܺ����б�	
					String [] usernames = ServerDataGramAnalyzer.getNames(stocdatagram);
					for(int i = 1 ;i < usernames.length;i++){
						OnlineUsers.onlineUsers.add(usernames[i]);
						textArea2.append(usernames[i]+"\n");
//						System.out.println("at client thread ���ܺ����б�:" + OnlineUsers.onlineUsers.get(i-1));
					}
					
					break;
					//��ʾ��Ӵ�б�
				case(2)://��½ʧ��
					JOptionPane.showMessageDialog(null,"��¼ʧ��", "��Ϣ����",JOptionPane.ERROR_MESSAGE);break;
				case(3)://�ظ���½
					JOptionPane.showMessageDialog(null,"�Ѿ�������", "��Ϣ����",JOptionPane.ERROR_MESSAGE);break;
				case(5)://ע���ظ�
					JOptionPane.showMessageDialog(null,"�û����Ѿ�ע���", "��Ϣ����",JOptionPane.ERROR_MESSAGE);break;
				case(6):// �û��˳� ������ʾ�б�
					OnlineUsers.onlineUsers.remove(ServerDataGramAnalyzer.getName(stocdatagram));//�б���ʾ***************************************
					textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+"  ������"+ "\n");
					textArea2.setText(null);
					textArea2.append("		�����б�\n");
//					for (String name : OnlineUsers.onlineUsers){
//						System.out.println("******" + name);
//						textArea2.append(name + "\n");
//					}
//					
				break;
				case(7)://��������״̬
					break;//**********************************
				case(8)://�û���������
					OnlineUsers.onlineUsers.add(ServerDataGramAnalyzer.getName(stocdatagram));
					//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					//ÿ�ν��յ���Ϣ ��Ҫ��ӡһ�º����б�:
					textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+"  ������"+ "\n");
					textArea2.append(ServerDataGramAnalyzer.getName(stocdatagram)+ "\n");
					break;
				}
				
			
//				System.out.println("���ߺ�����:"+OnlineUsers.onlineUsers.size() + "   �����б�:\n");
//				for (String  username: OnlineUsers.onlineUsers){
//					System.out.println( "  "+ username + "��");
//				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}