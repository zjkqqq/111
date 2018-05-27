package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Socket_Run.Socket_Kerberos_Client;

import javax.swing.JLabel;
import javax.swing.JButton;

public class UI_Register extends JFrame implements ActionListener{

	public JFrame frame;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JButton button;
	public JButton button_1;
	//public Socket s;
	public Socket_Kerberos_Client c=new Socket_Kerberos_Client();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public UI_Register() {
		initialize();
		
		System.out.println("begin run");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 274);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(140, 38, 159, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 75, 159, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140, 112, 159, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(52, 41, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(54, 78, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setBounds(54, 115, 72, 18);
		frame.getContentPane().add(label_2);
		
		button = new JButton("�ύ");
		button.setBounds(52, 174, 113, 27);
		frame.getContentPane().add(button);
		
		button.addActionListener(this);
		
		
		button_1 = new JButton("ȡ��");
		button_1.setBounds(209, 174, 113, 27);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(this);
		
		
	}
	
	 //�����¼��Ĵ���
	   public void actionPerformed(ActionEvent e)
	   {
	       //��ȡ�����¼����¼�Դǿ��ת��
	       JButton bt = (JButton)e.getSource();
	       //��ȡ��ť����ʾ���ı�
	       String str = bt.getText();
	       if(str.equals("�ύ"))
	       {
	    	   String username=textField.getText();
	    	   String passwd=textField_1.getText();
	    	
	    	   c.name=username;
	    	   c.password=passwd;
	    	   try {
				c.begin_login();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	   
	       }
	       
	       if(str.equals("ȡ��")) {
	    	   
	    	   this.frame.setVisible(false);
	       }
	       
	       }
	   
	  // if()
	   
}
