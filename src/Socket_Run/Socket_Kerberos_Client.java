package Socket_Run;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import C_Run.*;
import tcpchatroom.ClientThread;

public class Socket_Kerberos_Client {

	public C_Process run= new C_Process();
	
	 public String st3="��3";
	 public String st4="��4";
	 public String st5="��5";
	 public String st6="��6";
	 public String st7="��7";
	 public String st8="";
	 public String stas;
	 public String response;
	 public String send_sign;
	 
	 
	 public String name,password,ADc="001";
	 
	 
	 public Socket begin_login() throws UnknownHostException, IOException {
		 Socket s = new Socket("127.0.0.1", 5437); 
		 
		 try {
		
		 
			OutputStream out = s.getOutputStream(); 
			 
			DataOutputStream dout = new DataOutputStream(out);
			InputStream in = s.getInputStream();
			DataInputStream din = new DataInputStream(in);
			
			run.name=this.name;
			run.password=this.password;
			run.ADc=this.ADc;
			
			dout.writeUTF("000000");//���߷���������ʼ֤�齻��
			
			
			response = din.readUTF(); //���շ�����X.509,33 package
			run.process(response,"");//����33�Ű�
			
			send_sign=run.process("100000","");
			dout.writeUTF(send_sign);//send client x.509,32package
			String form=din.readUTF();
			if(!form.equals("ok")) {
				System.out.println("error");
				return s;
			}
			
			
			String info=run.process("000001","");
			dout.writeUTF(info);//send user's infomation
			
			response = din.readUTF();//receive 2 package
			run.process(response,"");//process 2 package
			
			
			dout.writeUTF("end");//���߷�����,��������
			
			in.close();
			out.close();
			s.close(); 
			
			} 
			catch (IOException e) 
			{} 
		return s;
	 }
	 
	public void begin_as() throws UnknownHostException, IOException
	{ 

		 Socket	s = new Socket("127.0.0.1", 5437);	

	try
	{ 
		
	 
	OutputStream out = s.getOutputStream(); 
	 
	DataOutputStream dout = new DataOutputStream(out);
	InputStream in = s.getInputStream();
	DataInputStream din = new DataInputStream(in);
	
	dout.writeUTF("000011");

	st3=run.process("000011",null);
	dout.writeUTF(st3);//3�Ű�
	System.out.println("3�Ű�����!!!!!!!!!!!!!!!!!!!");
	System.out.println(st3);

	
	st4 = din.readUTF();//4�Ű�
	System.out.println("4�Ű� �յ�!!!!!!!!!!!!!!!!!!!"+st4);
	
	 
	System.out.println(stas+" stas!!!!!!!!!!!!!!!!!!!");
	in.close();
	out.close();
	s.close();
	} 
	catch (IOException e) 
	{}

	} 
	
	
	public void begin_tgs()
	{ 
	try
	{ 
		Socket s = new Socket("127.0.0.1", 5434); 
		 
		OutputStream out = s.getOutputStream(); 
		 
		DataOutputStream dout = new DataOutputStream(out);
		
		InputStream in = s.getInputStream();
		DataInputStream din = new DataInputStream(in);

	//	dout.writeUTF(");
		
		st5=run.process(st4,null);

		System.out.println("5�Ű�����!!!!!!!!!!!!!!!!!!!");
		System.out.println(st5);
		dout.writeUTF(st5);//5�Ű�
		
		
		st6 = din.readUTF();//6�Ű�
		
		System.out.println("�յ�6�Ű�!!!!!!!!!!!!!!!!!!!");
		System.out.println(st6);
		String address=s.getInetAddress().getHostAddress();
		st7=run.process(st6,address);
		
		in.close();
		out.close();
		s.close(); 
	} 
	catch (IOException e) 
	{} 
	} 
	
	public Socket begin_v()
	{ 
	try
	{ 
		Socket s = new Socket("127.0.0.1", 5439); 
		
		OutputStream out = s.getOutputStream(); 
		DataOutputStream dout = new DataOutputStream(out);
		System.out.println("7�Ű�����!!!!!!!!!!!!!!!!!!!");
		System.out.println(st7);
		dout.writeUTF(st7);
		
		InputStream in = s.getInputStream();
		
		DataInputStream din = new DataInputStream(in);
		
		st8 = din.readUTF();//8�Ű�
		System.out.println("8�Ű��յ�!!!!!!!!!!!!!!!!!!!");
		System.out.println(st8);
		if(s.isClosed()) {
			System.out.println("fuck !!!");
		
			
		}
		//in.close();
	//	out.close();
		//s.close(); 
		return s;
	} 
	catch (IOException e) 
	{}
	return null; 
	} 
	
	public static void main(String args[]) {
		Socket_Kerberos_Client c=new Socket_Kerberos_Client();
//		
//		
//		
//		
//		//c.begin_login();
//		
//		c.begin_as();
//		
//		c.begin_tgs();
//		c.begin_v();
		System.out.println("��֤�ɹ�");
	}
	
}
