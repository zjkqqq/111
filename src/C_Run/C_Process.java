package C_Run;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DES_METHOD.DES;
import DES_METHOD.test;
import RSA.Rsa;
import X_509.X_509;

public class C_Process {
	
	private static Rsa rsa;
	private static X_509 certificate;
	public static String pubs;
	
	//client info conclude pub and private
	private BigInteger d;
	public BigInteger e;
	public BigInteger n;
	
	//recive the as pub info
	public BigInteger AS_e;//e
	public BigInteger AS_n;//n
	public String rec_sign;//sign info
	
	
	private String kc="AAAAAAAA";
	private C_3nums_Proc Proc3;
	private C_5nums_Proc Proc5;
	private C_7nums_Proc Proc7;
	private String Kctgs="";
	
	public String name,password,ADc;
	
	 public String C_get_time() {
	    	Date now = new Date(); 
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ


	    	String hehe = dateFormat.format( now ); 
	    	System.out.println(hehe); 
	    	int len=hehe.length();
	    	System.out.println(len); 
	    	return hehe;
	    }
	
	public String process(String msg,String address)throws UnsupportedEncodingException, UnknownHostException {
		
		int jud=judge_nums(msg);
		System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println(jud+"   !!!!!!!!!!!!!!!!!!!!!");
		switch(jud) {
		case 1:{
			
			String result="";
			
			result=C_process_1(msg);
			
			return result;
		}
		
		case 2:{
			String result="";
			
			System.out.println(msg);//check the info of AS
			result=C_process_2(msg);
			
			
			return result;
			
		}
		
		case 3:
		{
			 String result=C_process_3();
			 return result;
		}
		case 4:
		{
			String result="";
			result=C_process_5(msg);
			return result;
		}
		case 6:
		{
			String result=C_process_7(msg,address);
			return result;
		}
		
		case 32:{
			
			String result=C_process_32(msg);
			return result;
			
		}
		
		case 33:{
			
			String result="";
			
			C_process_33(msg);
			
			return result;
			
		}
		
		default:
		{
			System.out.println("C process error");
			String result="";
			return result;
		}
		}
		
	
		
		
	}
public int judge_nums(String msg) {
		
        int num=6;
    	
    	int mid=0;
    	for(int i=0;i<num;i++) {
    		char c=msg.charAt(i);
    		
    		
    		if(i==(num-1)) {
    			if(c=='0') {
    				
        			continue;
        		}
        		if(c=='1') {
        			
        			mid=mid+1;
        		}
    	
    		}else {
    			if(c=='0') {
        			continue;
        		}
        		if(c=='1') {
        			
        			mid=mid+(int)Math.pow(2, (num-1-i));
        		}
    		}
    		
    	}
    	
    	
    	
    	return mid;
	}


public String C_process_1(String msg) {
	
	String result="";
	
	C_1nums_Proc pro1=new C_1nums_Proc();
	
	pro1.process(name, password, ADc);
	
	result=pro1.process_1(msg, AS_e, AS_n);
	
	
	
	
	return result;
	
}

public String C_process_2(String msg) {
	
	String result="";
	
	String mid=msg.substring(6,msg.length());
	mid=rsa.decry_msg(mid, d, n);
	
	
	System.out.println(mid+" 099909090909090909090909090xuxuxxuxuxuxuxuxuxu");
	if(mid.equals("YES")) {
		 JOptionPane pane = new JOptionPane("ע��ɹ�");
	      JDialog dialog  = pane.createDialog("��ϲ");
	      dialog.show();
		System.out.println("ע��ɹ�");
	}
	
	if(mid.equals("HIND")) {
		
      JOptionPane pane = new JOptionPane("�Ѵ��ڴ��û�");
      JDialog dialog  = pane.createDialog("����");
      dialog.show();
		System.out.println("�Ѿ����ڴ��û�");
	}
	
	if(mid.equals("NO")) {
		 JOptionPane pane = new JOptionPane("ע��ʧ��");
	      JDialog dialog  = pane.createDialog("����");
	      dialog.show();
		System.out.println("ע��ʧ��");
	}
	
	return result;
	
}







	
public String C_process_3() {

	String result="";
	Proc3=new C_3nums_Proc();
	Proc3.process();
	result=Proc3.trans_thirdString();
	return result;
	
}
	
	
//	public String C_process_4(String msg) throws UnsupportedEncodingException{
//		String result="";
//		String decry=C_DES_decry(msg,6,111);
//		decry="000100"+decry;
//		Proc4.process(decry);
//		
//		return result;
//	}
	
	public String C_process_5(String msg) throws UnknownHostException, UnsupportedEncodingException {
		String result="";
		Proc5=new C_5nums_Proc();
		result=Proc5.process(msg);
		Kctgs = Proc5.C_get_Kctgs();
		return result;
	}
	
	
	
//	public String C_process_6(String msg) throws UnsupportedEncodingException {
//		
//		String result="";
//		String decry=C_DES_decry(msg,6,107);
//		decry="000100"+decry;
//		Proc6.process(msg);
//		
//		return result;
//	}

	
	
	
	
public String C_process_7(String msg,String address) throws UnsupportedEncodingException {
		
		String result="";
		
		String decry=C_DES_decry(msg,6,msg.length());
		//System.out.println("decry========"+decry);
		if(decry.equals("error"))
		{
			System.out.println("��֤ʧ�ܣ�");
			return null;
		}
		else {
		Proc7=new C_7nums_Proc();
		decry="000110"+decry;
	    result =Proc7.process(decry,address);
	    return result;
		}
		
	}
	
	public String C_process_8(String msg) throws UnsupportedEncodingException {
		
		String result="";
		String info=C_DES_decry(msg,6,8);
		String TS5=C_DES_decry(msg,9,28);
		
		System.out.println(info);
		
		return info;
	}
	
	
	public String C_process_32(String msg) //RSA�ͻ�����֤������AS
	{
		//String result="";
		
		 rsa =new Rsa();
		    certificate=new X_509();
			rsa.begin();
			String pubn=rsa.n.toString();//�ͻ��˱��εĹ�Կ
			String pube=rsa.e.toString();
			String pubd=rsa.get_d().toString();//client's private key
			
			e=new BigInteger(pube);
			n=new BigInteger(pubn);
			d=new BigInteger(pubd);
			
			

			certificate.write_name("Client");
			certificate.write_Pub_key_e(pube);
			certificate.write_Pub_key_n(pubn);
			certificate.write_serial_nums("123321");//ģ�����кţ�ûʲô��.
		    String M="Clienttrue";
			ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);//��������������ǩ��
			
			
			
			String mid[]=new String[encry.size()];
			
			for(int i=0;i<mid.length;i++) {
				if(i!=(mid.length-1)) {
				mid[i]=encry.get(i).toString()+"&";}
				else {
					mid[i]=encry.get(i).toString();	
				}	
			}
		
			
			certificate.write_sign(mid);
			
			pubs="100000&"+certificate.get_Pub_key_e()+"&"+certificate.get_Pub_key_n()+"&"+
			certificate.get_name()+"&"+trans(certificate.get_sign());
		    System.out.println(pubs);
			
		return pubs;
	}
	
	public void C_process_33(String msg) {//get the public key of as 
		C_33nums_Proc pro33=new C_33nums_Proc();
		pro33.process(msg);
		AS_e=pro33.AS_e;
		AS_n=pro33.AS_n;
		rec_sign=pro33.rec_sign;
		System.out.println(AS_e+" "+AS_n+" "+" ȷ���յ���as��Կ����ȷ��");
		
	}
	
	public String trans(String mid[]) {
		
		String result="";
		
		int len=mid.length;
		
		for(int i=0;i<len;i++) {
			result=result+mid[i];
		}
		
		return result;
		
	}


	 public String C_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
	    	msg=msg.substring(begin, end+1);
	    	
	    	test c=new test();
			
		
			DES des=new DES();
			//����
			String encrypttext=des.enc(msg,k);
			System.out.println("���ܺ�Ľ���ǣ�");
			System.out.println(encrypttext);
		
	    	return encrypttext;
	    }

	 
	 public String C_DES_decry(String msg,int begin,int end) throws UnsupportedEncodingException{
		    msg=msg.substring(begin, end);
			DES des=new DES();
			System.out.println("Kctgs========"+Kctgs);
			String decrypttext=des.dec(msg,Kctgs);
			
			return decrypttext;
	    	
	    }
	   
}
	


