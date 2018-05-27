/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;





import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Socket_Run.Socket_Kerberos_Client;
import tcpchatroom.datagram.ClientDataGram;
import vo.User;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * �ͻ��˴��ڳ���
 *
 *
 */

public class Client extends JFrame implements WindowListener{
	/**
	 * 
	 */
	
	public Socket_Kerberos_Client c=new Socket_Kerberos_Client();
	public static final long serialVersionUID = 1L;
	public Container container=this.getContentPane();
	public JLabel label_username = new JLabel("�û��� ");
	//public JLabel label_password = new JLabel("���� ");
	public JLabel label_privatename = new JLabel("˽�Ķ���");
	public String[] friends = null;
	
	public User user = new User();

	//private JTextField textField_username = new JTextField(15);
	//private JTextField textField_password = new JTextField(6);
	public JTextField textField_inputText = new JTextField(30);
	public JTextField textField_name = new JTextField(22);

	//private JButton button_join = new JButton("��½");
	//private JButton button_regist = new JButton("ע��");
	public JButton button_sent = new JButton("Ⱥ��");
	public JButton button_privatechat = new JButton("˽��");
	
	
	/* ��Ϣ���� */
	public JTextArea textarea_messagerecord = new ContentArea();
	public  JScrollPane scrollPane = new JScrollPane(textarea_messagerecord);

	public JTextArea textarea_friends = new ContentArea();
	public  JScrollPane scrollPane2 = new JScrollPane(textarea_friends);

	
	public Socket socket;
	public PrintWriter out;
	public  BufferedReader in;

	public Client(Socket s){
		
		try{
			socket = s;
			if(!socket.isBound()){//��ʧ��
				textarea_messagerecord.setText("��ʧ��");
			}
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("client socket open!!!!!!!!!!!!!!!!!!!!!");
		}catch(Exception e){
			System.out.println(e.getMessage()+" xuxixixixiixixixixix");
		}
		
		
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		setTitle("TCP Chat Room Client");
		setSize(500,300);
		setResizable(true);

		textarea_messagerecord.setLineWrap(true);
		textarea_messagerecord.setEditable(false);

//		JPanel p1 = new JPanel();//��壬Ƕ��
//		p1.setSize(500, 40);
//		p1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
//		p1.add(label_username);
//		p1.add(textField_username);
//		p1.add(label_password);
//		p1.add(textField_password);
		
		
//		JPanel p2 = new JPanel();
//		p2.setSize(600, 40);		
//		p2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
//		p2.add(button_join);
//		button_join.setBounds(600, 40, 30, 20);
//		
//		p2.add(button_regist);

		JPanel p3 = new JPanel();
		p3.setSize(600, 40);
		p3.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
		textField_inputText.setVisible(false);
		button_sent.setVisible(false);
		p3.add(textField_inputText);		
		p3.add(button_sent);

		JPanel p4 = new JPanel();
		p4.setSize(600, 40);
		p4.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
		button_privatechat.setVisible(false);
		textField_name.setVisible(false);
		label_privatename.setVisible(false);
		p4.add(label_privatename);
		p4.add(textField_name);
		p4.add(button_privatechat);

		
//		container.add(p1);
//		container.add(p2);
		scrollPane.setVisible(false);
		scrollPane2.setVisible(false);
		container.add(scrollPane);
		container.add(scrollPane2);		
		container.add(p4);
		container.add(p3);
		textarea_friends.setEditable(false);

		//ButtonJoinListener joinListener = new ButtonJoinListener();
		//button_join.addActionListener(joinListener);
		ButtonSentListener sentListener = new ButtonSentListener();
		button_sent.addActionListener(sentListener);
		
		//ButtonRegistListener registListener = new ButtonRegistListener();
		//button_regist.addActionListener(registListener);
		ButtonPrivateChatListener privateChatListener = new ButtonPrivateChatListener();
		button_privatechat.addActionListener(privateChatListener);
		
		//Ĭ���û��������붼�ǿ�
//		textField_username.setText("admin");
//		textField_password.setText("admin");
//		
//		button_join.setVisible(false);
//		button_regist.setVisible(false);
//		//label_username.setVisible(false);
//		label_password.setVisible(false);
//		textField_password.setVisible(false);
//		textField_username.setVisible(false);
		
		this.addWindowListener(this);
		//���رհ�ť��ɶ��Ҳ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	//	signin_client(username,passwd);
		

	}


	
	class ContentArea extends JTextArea{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected ContentArea(){
			super(10, 40);
		}
	}
	
//	class ButtonJoinListener implements ActionListener{//��½
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			//c.begin_login();
//			
//			
//			
//			/*login use*?**************************/
//			user.setUserName(textField_username.getText());
//			user.setPasswd(textField_password.getText());
//			
//			
//			ClientDataGram logindatagram = new ClientDataGram((short) 1,user.getUserName(),user.getPasswd(),true);//��¼
//			out.println(logindatagram.toString());
//			
//			//���������Һ��ֹ�޸Ĳ���		
//			setSize(600,600);
//			button_join.setVisible(false);
//			button_regist.setVisible(false);
//			//label_username.setVisible(false);
//			label_password.setVisible(false);
//			textField_password.setVisible(false);
//			textField_username.setVisible(false);
//			
//			
//			scrollPane.setVisible(true);
//			scrollPane2.setVisible(true);
//			textField_name.setVisible(true);
//			textField_inputText.setVisible(true);
//			button_sent.setVisible(true);
//			button_privatechat.setVisible(true);
//			label_privatename.setVisible(true);
//			
//			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
//			textarea_friends.setText(null);
//			textarea_friends.setText("		�����б�\n");
//			
//		}
//	}
	
//	//��½���ú���
//	public void signin_client(String username,String passwd) {
//		
//		//c.begin_login();
//		
//		
//		
//		/*login use*?**************************/
//		//user.setUserName(textField_username.getText());
//		//user.setPasswd(textField_password.getText());
//		user.setUserName(username);
//		user.setPasswd(passwd);
//		//c.begin_login();
//		
//		
//		
//		/*login use*?**************************/
//	
//		
//		
//		ClientDataGram logindatagram = new ClientDataGram((short) 1,user.getUserName(),user.getPasswd(),true);//��¼
//		out.println(logindatagram.toString());
//		
//		//���������Һ��ֹ�޸Ĳ���		
//		setSize(600,600);
//		button_join.setVisible(false);
//		button_regist.setVisible(false);
//		//label_username.setVisible(false);
//		label_password.setVisible(false);
//		textField_password.setVisible(false);
//		textField_username.setVisible(false);
//		
//		
//		scrollPane.setVisible(true);
//		scrollPane2.setVisible(true);
//		textField_name.setVisible(true);
//		textField_inputText.setVisible(true);
//		button_sent.setVisible(true);
//		button_privatechat.setVisible(true);
//		label_privatename.setVisible(true);
//		
//		label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
//		textarea_friends.setText(null);
//		textarea_friends.setText("		�����б�\n");
//		
//	}
	
	
//	class ButtonRegistListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			
//			user.setUserName(textField_username.getText());
//			user.setPasswd(textField_password.getText());
//			
//			
//			ClientDataGram logindatagram = new ClientDataGram((short) 4,user.getUserName(),user.getPasswd());//ע��
//			out.println(logindatagram.toString());
//			
//			//���������Һ��ֹ�޸Ĳ���		
//			setSize(600,600);
//			button_join.setVisible(false);
//			button_regist.setVisible(false);
//			//label_username.setVisible(false);
//			label_password.setVisible(false);
//			textField_password.setVisible(false);
//			textField_username.setVisible(false);
//			
//			
//			scrollPane.setVisible(true);
//			scrollPane2.setVisible(true);
//			textField_name.setVisible(true);
//			textField_inputText.setVisible(true);
//			button_sent.setVisible(true);
//			button_privatechat.setVisible(true);
//			label_privatename.setVisible(true);
//			
//			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
//			textarea_friends.setText(null);
//			textarea_friends.append("		�����б�\n");	
//		}
//	}

	class ButtonSentListener implements ActionListener{//������Ϣ
		public void actionPerformed(ActionEvent arg0) {//**********************************��Ҫ�޸�
			String message_text = textField_inputText.getText();		
			friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String [OnlineUsers.onlineUsers.size()]);
			ClientDataGram clientdatagram = new ClientDataGram((short) 0,friends,message_text);//��ͨ��Ϣ	
			try {
				out.println(clientdatagram.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
			//textarea_messagerecord.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
			textarea_messagerecord.append(user.getUserName() + ":  "+ textField_inputText.getText()+'\n');
			textField_inputText.setText("");
		}

	}
	

	class ButtonPrivateChatListener implements ActionListener{//˽�ķ�����Ϣ
		public void actionPerformed(ActionEvent arg0) {//**********************************��Ҫ�޸�
			String message_text = textField_name.getText();		
			friends = new String []{message_text};
//			System.out.print(message_text + "\n" + " ˽�ĺ���");
//			for (String name:  friends)
//				System.out.println("    " + name );
			//friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String [OnlineUsers.onlineUsers.size()]);
			ClientDataGram clientdatagram = new ClientDataGram((short) 0,friends,textField_inputText.getText());//��ͨ��Ϣ	
			try {
				out.println(clientdatagram.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
			//textarea_messagerecord.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
			textarea_messagerecord.append(user.getUserName() + ": "+ textField_inputText.getText()+'\n');
			textField_inputText.setText("");
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		   int option = JOptionPane.showConfirmDialog(this, "ȷ���˳�ϵͳ?", "��ʾ",JOptionPane.YES_NO_OPTION);
			       if (option == JOptionPane.YES_OPTION)
			       {
			               if (e.getWindow() == this) {
			                      this.dispose();
			                      ClientDataGram exitdatagram = new ClientDataGram((short)2);//�˳�
			                      out.print(exitdatagram.toString());
			                      try {
			                    	  out.close();
			                    	  in.close();
									socket.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
			                      
			                      System.exit(0);
			       } else {
			              return;
			       }
			    }
			   else if(option == JOptionPane.NO_OPTION){
			          if (e.getWindow() == this) {
			                   return;
			          }else{
			        	  return;
			          }
			     }
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public static void main(String[] args) throws IOException {
//		OnlineUsers.onlineUsers  = new ArrayList<String>();
//		
//		//Client chatRoom = new Client();
//		
//		
//		ClientThread thread = new ClientThread(chatRoom.textarea_messagerecord,chatRoom.textarea_friends, chatRoom.socket,chatRoom.in);
//		thread.start();
//		
//		chatRoom.setLocationRelativeTo(null);
//		//chatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		chatRoom.setVisible(true);
//	}
}