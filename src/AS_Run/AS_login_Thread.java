package AS_Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import factory.DaoFactory;
import tcpchatroom.OnlineUsers;
import tcpchatroom.datagram.ClientDataGramAnalyzer;
import tcpchatroom.datagram.ServerDataGram;
import vo.User;

public class AS_login_Thread {
	
		
		
		
		private User user = new User();
	

		public String run(String username,String passwd) throws Exception{
			

							
							this.user.setUserName(username);
//							System.out.println("regist name : " + this.user.getUserName());
							this.user.setPasswd(passwd);
//							System.out.println("regist password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
						
								user.setRegisterTime(new Date());
								if(DaoFactory.getIUserInstance().findByUserName(this.user.getUserName()) != null){
									ServerDataGram logdulp = new ServerDataGram((short)5);//�û����Ѿ�ע��
									String result="�Ѿ�ע��";;
									return result;
								}
								
								DaoFactory.getIUserInstance().doCreate(user);//�������ݿ���
								return "ע��ɹ�";								
							}
}
								//�������ߺ����б�
							//	String[] friends = (String[]) OnlineUsers.onlineUsers.toArray(new String[OnlineUsers.onlineUsers.size()]);
									
							//	ServerDataGram registermessage = new ServerDataGram((short)4,friends);//ע��ɹ�
									
								//out.println(registermessage.toString());
								//�������ѣ���������������
								//if(ClientDataGramAnalyzer.getState(clientdatagram).equals("online")){
			
//								ServerDataGram onlineinform = new ServerDataGram((short)8,this.user.getUserName());//**************
//								for(Socket client : OnlineUsers.clientList){
//										PrintWriter messagetofriend = new PrintWriter(client.getOutputStream(),true);
//										messagetofriend.println(onlineinform.toString());
//								}
								//}
								
								//OnlineUsers.onlineUsers.add(this.user.getUserName());//*****************	
							
								
//								for(String user: OnlineUsers.onlineUsers)
//									System.out.print("���ߺ�����" + OnlineUsers.onlineUsers.size()+ "online users: " + user);
								
							
							
					
//					String clientSentence = in.readLine();
//					//��ʾ�ͻ��˷�������Ϣ
//					textArea.append(clientSentence + "\n");
//					//Ⱥ�������пͻ���
//					for(Socket client : OnlineUsers.clientList){
//						PrintWriter out = new PrintWriter(client.getOutputStream(),true);
//						out.println(clientSentence);
//					}
				
				
			 
//			System.out.println(user.getUserName()+"exit!");
		

	

