/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import AS_Run.AS_Process;
import V_Run.V_Process;
import factory.DaoFactory;
import tcpchatroom.datagram.ClientDataGramAnalyzer;
import tcpchatroom.datagram.ServerDataGram;
import vo.User;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * ���������׽������߳�
 * ��Ӧÿһ���ͻ���

 */

public class ServerThread extends Thread{
	
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private User user = new User();
	public ServerThread(Socket socket){
		this.socket = socket;
	}

	public void run(){
		try {
			
			String st8=kerberos();
			
			if(st8!="") {
				System.out.println("");
			}else {
				return;
			}
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			while(!this.socket.isClosed()){
				
				String clientdatagram = in.readLine();
				//System.out.println("��½�û�:"+this.user.getUserName()+"	"+"�û�����:"+ this.user.getPasswd() + "	online state:" + ClientDataGramAnalyzer.getState(clientdatagram));
				
	
				switch (ClientDataGramAnalyzer.getType(clientdatagram)){
					case(0)://������Ϣ��
						String accepters[] = ClientDataGramAnalyzer.getAccepters(clientdatagram);	
//						System.out.println("���յ���Ϣ:" + user.getUserName()+":"+" "+ ClientDataGramAnalyzer.getMessage(clientdatagram));
						
						for(int i = 1; i< accepters.length;i++){//�׸��ַ��� һ����null ���Բ���
								PrintWriter toclient = new PrintWriter(OnlineUsers.ssm.get(accepters[i]).getOutputStream(),true);
								///����������Ϣ  **************************************
								ServerDataGram message = new ServerDataGram((short)0,this.user.getUserName(),ClientDataGramAnalyzer.getMessage(clientdatagram));
								toclient.println(message.toString());
						}break;		
					case(1)://login
						this.user.setUserName(ClientDataGramAnalyzer.getName(clientdatagram));
//						System.out.println("login name : " + this.user.getUserName());
						this.user.setPasswd(ClientDataGramAnalyzer.getPassword(clientdatagram));
//						System.out.println("login password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
						try {
							
							if(OnlineUsers.onlineUsers.contains(this.user.getUserName())){
								ServerDataGram logdulp = new ServerDataGram((short)3);//���û��Ѿ���¼^^^^^^^^^^^^^^^
								out.println(logdulp.toString());
								this.in.close();
								this.out.close();
								this.socket.close();
								break;
							}
								
							this.user = DaoFactory.getIUserInstance().findByUserNameandPasswd(this.user.getUserName(), this.user.getPasswd());
							if(this.user==null){
								ServerDataGram loginfailedmessage = new ServerDataGram((short)2);//�û������������
								out.println(loginfailedmessage.toString());
								this.in.close();
								this.out.close();
								this.socket.close();
							}
							else{
								//�������ߺ����б�
								String[] friends = null ;
								friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String[OnlineUsers.onlineUsers.size()]);
								
								ServerDataGram loginsuccessedmessage = new ServerDataGram((short)1,friends);//��¼�ɹ�  ^^^^^^^^^^^^^^^^^
								
//								System.out.println("server datagram: "+  loginsuccessedmessage.toString());
								out.println(loginsuccessedmessage.toString());
								//�������ѣ���������������
								//if(ClientDataGramAnalyzer.getState(clientdatagram).equals("online")){
		
								ServerDataGram onlineinform = new ServerDataGram((short)8,this.user.getUserName());//����������Ϣ^^^^^^^^^^
								for(Socket client : OnlineUsers.clientList){
										PrintWriter messagetofriend = new PrintWriter(client.getOutputStream(),true);
										messagetofriend.println(onlineinform.toString());
									}
								//}
								OnlineUsers.clientList.add(this.socket);
								OnlineUsers.onlineUsers.add(this.user.getUserName());//*****************	
								OnlineUsers.ssm.put(this.user.getUserName(),this.socket);
								
//								System.out.print("���ߺ�����"+ OnlineUsers.onlineUsers.size() + "    online users: ");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
						
					case(2)://�û��˳�				
						OnlineUsers.onlineUsers.remove(OnlineUsers.onlineUsers.indexOf(this.user.getUserName()));// ***************δ������½���û� �˳�ʱ�׳��쳣
						//OnlineUsers.clientList.remove(this.socket);
						OnlineUsers.ssm.remove(OnlineUsers.onlineUsers.indexOf(this.user.getUserName()));
						
						OnlineUsers.clientList.remove(OnlineUsers.clientList.indexOf(this.socket));
						
						
						for(Socket socket :OnlineUsers.clientList){
							PrintWriter toclient= new PrintWriter(socket.getOutputStream(),true);
							toclient.println(new ServerDataGram((short)6,this.user.getUserName()).toString());
						}
					
						this.in.close();
						this.out.close();
						this.socket.close();
						break;
					case(3)://��������״̬
						break;
					case(4)://ע��
						
						
					
						
						
						
						this.user.setUserName(ClientDataGramAnalyzer.getName(clientdatagram));
//						System.out.println("regist name : " + this.user.getUserName());
						this.user.setPasswd(ClientDataGramAnalyzer.getPassword(clientdatagram));
//						System.out.println("regist password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
						try {
							user.setRegisterTime(new Date());
							if(DaoFactory.getIUserInstance().findByUserName(this.user.getUserName()) != null){
								ServerDataGram logdulp = new ServerDataGram((short)5);//�û����Ѿ�ע��
								out.println(logdulp.toString());
								break;
							}
							
							DaoFactory.getIUserInstance().doCreate(user);//�������ݿ���
							//�������ߺ����б�
							String[] friends = (String[]) OnlineUsers.onlineUsers.toArray(new String[OnlineUsers.onlineUsers.size()]);
								
							ServerDataGram registermessage = new ServerDataGram((short)4,friends);//ע��ɹ�
								
							out.println(registermessage.toString());
							//�������ѣ���������������
							//if(ClientDataGramAnalyzer.getState(clientdatagram).equals("online")){
		
							ServerDataGram onlineinform = new ServerDataGram((short)8,this.user.getUserName());//**************
							for(Socket client : OnlineUsers.clientList){
									PrintWriter messagetofriend = new PrintWriter(client.getOutputStream(),true);
									messagetofriend.println(onlineinform.toString());
							}
							//}
							OnlineUsers.clientList.add(this.socket);
							OnlineUsers.onlineUsers.add(this.user.getUserName());//*****************	
							OnlineUsers.ssm.put(this.user.getUserName(),this.socket);
							
//							for(String user: OnlineUsers.onlineUsers)
//								System.out.print("���ߺ�����" + OnlineUsers.onlineUsers.size()+ "online users: " + user);
							
						}
						 catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
				}
//				String clientSentence = in.readLine();
//				//��ʾ�ͻ��˷�������Ϣ
//				textArea.append(clientSentence + "\n");
//				//Ⱥ�������пͻ���
//				for(Socket client : OnlineUsers.clientList){
//					PrintWriter out = new PrintWriter(client.getOutputStream(),true);
//					out.println(clientSentence);
//				}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(user.getUserName()+"exit!");
	}

	public String kerberos() throws Exception {
		 InputStream in = socket.getInputStream();
         DataInputStream din = new DataInputStream(in);
         String st7  = din.readUTF();
         System.out.println("7�Ű��յ�!!!!!!!!!!!!!");
         System.out.println(st7);
         V_Process V=new V_Process();
         
         String st8=V.process(st7);
         System.out.println("8�Ű�����!!!!!!!!!!!!!");
         System.out.println(st8);
         OutputStream out = socket.getOutputStream();
         DataOutputStream dos = new DataOutputStream(out);
         dos.writeUTF(st8);
       return st8;
        
         
        
	}
	
}