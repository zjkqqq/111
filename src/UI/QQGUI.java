package UI;
//ģ��qq��¼����
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.*;
import javax.swing.*;

import Socket_Run.Socket_Kerberos_Client;
import tcpchatroom.Client;
import tcpchatroom.ClientThread;
import tcpchatroom.OnlineUsers;
import tcpchatroom.datagram.ClientDataGram;

//��֤�������
import java.awt.*;
import java.util.*;


//�������ʵ��
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QQGUI extends JFrame implements ActionListener{
  private JLabel userLa;
  private JLabel pwdLa;
  private JLabel verCodeLa;//��֤��
  private JTextField userTxt;
  private JPasswordField pwdTxt;
  private JTextField verCodeTxt;//��֤��
  private JButton sureBt;
  private JButton quitBt;
  private Mypanel mp;
  public Socket_Kerberos_Client c;
 // public Socket s_as;

  //���췽��
  public QQGUI()
  {
      Init();
  }
  public void Init()
  {
      Frame frame = new Frame("�����ҵ�¼");

      //�������ؼ�������Ϊ����ֻ��������������û�и���ʵ�ʵĿռ�)

      //�û��ı�
      userLa = new JLabel();
      userLa.setText("�û�����");
      userLa.setSize(60, 50);
      userLa.setLocation(100, 80);

      //�����ı�
      pwdLa = new JLabel();
      pwdLa.setText("���룺");
      pwdLa.setSize(50, 50);
      pwdLa.setLocation(100, 120);

      //�û������
      userTxt = new JTextField();
      userTxt.setSize(100, 20);
      //this.setSize(width, height)
      userTxt.setLocation(170, 95);

      //���������
      pwdTxt = new JPasswordField();
      pwdTxt.setSize(100, 20);
      pwdTxt.setLocation(170, 135);

      //ȷ�ϰ�ť
      sureBt = new JButton("��¼");
      sureBt.setSize(60, 25);
      sureBt.setLocation(135, 260);

      //�˳���ť
      quitBt = new JButton("ע��");
      quitBt.setSize(60, 25);
      quitBt.setLocation(240, 260);

      //��֤���ı�
      verCodeLa = new JLabel();
      verCodeLa.setText("��֤�룺");
      verCodeLa.setSize(60, 50);
      verCodeLa.setLocation(100,165);

      //��֤���ı���
      verCodeTxt = new JTextField();
      verCodeTxt.setSize(100, 20);
      verCodeTxt.setLocation(170, 180);

      //��֤��
      mp = new Mypanel();
      mp.setSize(100, 30);
      mp.setLocation(280, 175);

      //��¼��ʽѡ���
      JComboBox xlk=new JComboBox();
      xlk.setSize(60, 20);
      xlk.setLocation(250, 220);
      xlk.addItem("����");
      xlk.addItem("����");
      xlk.addItem("�뿪");


      this.setLayout(null);
      this.setSize(500, 400);
      this.add(userLa);
      this.add(pwdLa);
      this.add(userTxt);
      this.add(pwdTxt);
      this.add(sureBt);
      this.add(quitBt);
      this.add(verCodeLa);
      this.add(verCodeTxt);
      this.add(mp);
      this.add(xlk);
      sureBt.addActionListener(this);
      quitBt.addActionListener(this);
      this.setVisible(true);
  }
  //�����¼��Ĵ���
   public void actionPerformed(ActionEvent e)
   {
       //��ȡ�����¼����¼�Դǿ��ת��
       JButton bt = (JButton)e.getSource();
       //��ȡ��ť����ʾ���ı�
       String str = bt.getText();
       if(str.equals("��¼"))
       {
    	   
    	  
    	   
    	   
   		try {
   			
   			if(c!=null) {
			c.begin_as();
   			}else {
   				c=new Socket_Kerberos_Client ();
   				c.begin_as();
   			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   		
   		c.begin_tgs();
   		Socket s_v=c.begin_v();
   		if(s_v.isClosed()) {
   			System.out.println("��֤�ɹ�,����s�ر���");
   		}
   		
    	   
   	    //����������

		try {
			OnlineUsers.onlineUsers  = new ArrayList<String>();
			
			
			String username=userTxt.getText();
			@SuppressWarnings("deprecation")
			String passwd=pwdTxt.getText();
			
			
			
			Client chatRoom = new Client(s_v);
			//�ͻ��˶��߳�����
			ClientThread thread;
			
			thread = new ClientThread(chatRoom.textarea_messagerecord,chatRoom.textarea_friends, chatRoom.socket,chatRoom.in);
		
			thread.start();
			

			
			chatRoom.setLocationRelativeTo(null);
			//chatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			chatRoom.setVisible(true);
			
				
				//c.begin_login();
				
				
				
				/*login use*?**************************/
				chatRoom.user.setUserName(username);
				chatRoom.user.setPasswd(passwd);
				
				
				ClientDataGram logindatagram = new ClientDataGram((short) 1,chatRoom.user.getUserName(),chatRoom.user.getPasswd(),true);//��¼
				System.out.println(logindatagram.toString()+" ^^^^^^^^^^^^^^^^^^^^^^");
				
				
				chatRoom.out.println(logindatagram.toString());
				
				//���������Һ��ֹ�޸Ĳ���		
				setSize(600,600);
//				chatRoom.button_join.setVisible(false);
//				chatRoom.button_regist.setVisible(false);
//				//label_username.setVisible(false);
//				chatRoom.label_password.setVisible(false);
//				chatRoom.textField_password.setVisible(false);
//				chatRoom.textField_username.setVisible(false);
				
				
				chatRoom.scrollPane.setVisible(true);
				chatRoom.scrollPane2.setVisible(true);
				chatRoom.textField_name.setVisible(true);
				chatRoom.textField_inputText.setVisible(true);
				chatRoom.button_sent.setVisible(true);
				chatRoom.button_privatechat.setVisible(true);
				chatRoom.label_privatename.setVisible(true);
				
				chatRoom.label_username.setText(chatRoom.user.getUserName() + "   is   "+ (chatRoom.user.getState()?"onine":"offline"));
				chatRoom.textarea_friends.setText(null);
				chatRoom.textarea_friends.setText("		�����б�\n");
				
			
			
			
			
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	   
    	   
    	   
//           if(!CheckIsNull())
//           {
//               //��ȡ�û���������û���
//               String user = userTxt.getText().trim();
//               //��ȡ�û������������
//               String pwd = pwdTxt.getText().trim();
//               if(checkUserAndPwd(user,pwd))
//               {
//
//                   //���ص�ǰ��¼����
//                   this.setVisible(false);
//                   //��֤�ɹ�����һ��������
//                   MainFrame frame = new MainFrame();
//               }
//               else
//               {
//                   //��������򵯳�һ����ʾ��
//                   JOptionPane pane = new JOptionPane("�û����������");
//                   JDialog dialog  = pane.createDialog(this,"����");
//                   dialog.show();
//               }
//           }
//           
           
           
           
           
           
       }
       if(str.equals("ע��")) {
    	   
    	   UI_Register login=new UI_Register();
           login.frame.setVisible(true);
    	  
    	   
    	   
       }
//       else
//       {
//           //����ϵͳ���е�һ�������˳�
//           System.exit(0);
//       }
   }
   private boolean CheckIsNull()
   {
       boolean flag = false;
       if(userTxt.getText().trim().equals(" "))
       {
           flag = true;
       }
       else
       {
           if(pwdTxt.getText().trim().equals(" "))
           {
               flag = true;
           }
       }
       return flag;
   }
   private boolean checkUserAndPwd(String user,String pwd)
   {
       boolean result = false;
       try
       {
           FileReader  file = new FileReader("D:\\Workspaces\\MyEclipse 8.5\\testGUI.txt"); 
           BufferedReader bre =  new BufferedReader(file);
           String str = bre.readLine();

          while(str!=null)
          {
               String[] strs = str.split(",");
               if(strs[0].equals(user))
               {
                   if(strs[1].equals(pwd))
                   result = true;
               }
               str = bre.readLine();
          }
          file.close();
       }catch(Exception ex)
       {
           System.out.print("");
       }
       return result;
   }
}





//����
