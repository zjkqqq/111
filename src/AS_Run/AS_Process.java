package AS_Run;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;

import DES_METHOD.DES;
import DES_METHOD.test;
import RSA.Rsa;
import TGS_Run.TGS_7nums_Proc;
import X_509.X_509;

public class AS_Process {
	
	private String Ktgs="AAAAAAAA";
	

	//rec info form client's public key  rsa use;
	public BigInteger Client_e;
	public BigInteger Client_n;
	public String rec_sign;
	
	//info of AS's public key
	private BigInteger d;
	public BigInteger e;
	public BigInteger n;
	
	private static Rsa rsa;
	private static X_509 certificate;
	public static String pubs;
	
	
public String process(String msg,String address) throws Exception {
	
	
		
		int jud=judge_nums(msg);
		switch(jud) {
		case 1:{
			
			String result="";
			
			result=AS_process_1(msg,address);
			
			return result;
			
		}
		
		
		case 3:
		{
            String result="";
			
			result=AS_process_3(msg,address);
			
			return result;
		}
		case 32:
		{
			String result="";
			
			result=AS_process_32(msg);
			
			return result;
		}
		
		case 33:{
			return AS_process_33(msg);
		}
		default:{
			System.out.print("7 control package error");
			return "";
		}
		}
		
		
	}
	
public int judge_nums(String msg) {
	
    int num=6;
	
	String juge="";
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

public String AS_process_1(String msg,String address) throws Exception {//process 1 package and return 2 pakage
	
	String result="";
	
	String cipher=msg.substring(6, msg.length());
	String M=rsa.decry_msg(cipher, d, n);
	M="000001"+M;
	String username=M.substring(6, 26);
	String userADc=M.substring(26,29);
	String userpassword=M.substring(29,45);
	
	username=format_str(username);
	userADc=format_str(userADc);
	userpassword=format_str(userpassword);
	
	AS_login_Proc login=new AS_login_Proc();
	
	result=login.run(username, userpassword);
	
	System.out.println(M+" 真的假的啊");
	
	//AS_1nums_Proc pro=new AS_1nums_Proc();
	
	
	if(result.equals("注册成功")) {
	result="YES";}
	if(result.equals("已经注册")) {
		result="HIND";
	}
	else {
		result="NO";
	}
	String response=rsa.encry_msg(result, Client_e, Client_n);
	result="000010"+response;
	
	return result;
	
}

public String  format_str(String str) {//删除空格
	
	String mid="";
	for(int i=0;i<str.length();i++) {
		
		char c=str.charAt(i);
		if(c==' ') {
			continue;
		}else {
			mid=mid+c;
		}

	}
	 str="";
	str=mid;
	return str;
	
}


public String AS_process_3(String msg,String address) throws UnsupportedEncodingException {
	
	AS_3nums_Proc Proc3=new AS_3nums_Proc();
	
	String result=Proc3.process(msg, address);
	
	return result;
	
}

public 	String AS_process_32(String msg) {
	
	System.out.println(msg+" wokao确认收到的客户端的请求");
	
	String rec[]=msg.split("&");
	String sign_info[];
	int len=rec.length;
	
	
	sign_info=new String[len-4];
	for(int i=1;i<len;i++) {
		if(i==1) {
			String pube=rec[1];
			Client_e=new BigInteger(pube);
			
			continue;
		}
		if(i==2) {
			String pubn=rec[2];
			Client_n=new BigInteger(pubn);
			continue;
		}
		if(i==3) {
			String name=rec[3];
			rec_sign=name;
			continue;
		}
		
		for(int j=0;j<len-4;j++) {
			sign_info[j]=rec[i++];
		}
		
		
	}
	return "ok";
	
}

public String AS_process_33(String msg) {
	
	String result="";
	
	 rsa =new Rsa();
	    certificate=new X_509();
		rsa.begin();
		String pubn=rsa.n.toString();
		String pube=rsa.e.toString();
		String pubd=rsa.get_d().toString();
		
		e=new BigInteger(pube);
		n=new BigInteger(pubn);
		d=new BigInteger(pubd);
		
		certificate.write_name("AS");
		certificate.write_Pub_key_e(pube);
		certificate.write_Pub_key_n(pubn);
		certificate.write_serial_nums("123321");
	    String M="AStrue";
		ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);
		
		String mid[]=new String[encry.size()];
		
		for(int i=0;i<mid.length;i++) {
			if(i!=(mid.length-1)) {
			mid[i]=encry.get(i).toString()+"&";}
			else {
				mid[i]=encry.get(i).toString();	
			}	
		}
		
		
		certificate.write_sign(mid);
		
		pubs="100001&"+certificate.get_Pub_key_e()+"&"+certificate.get_Pub_key_n()+"&"+
		certificate.get_name()+"&"+trans(certificate.get_sign());
	
	return pubs;
	
}

public String trans(String mid[]) {//将字符串数组整理成字符串
	
	String result="";
	
	int len=mid.length;
	
	for(int i=0;i<len;i++) {
		result=result+mid[i];
	}
	
	return result;
	
}

	
public String AS_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
	msg=msg.substring(begin, end);
	
	test c=new test();
	

	DES des=new DES();
	//加密
	String encrypttext=des.enc(msg,k);
	System.out.println("加密后的结果是：");
	System.out.println(encrypttext);

	return encrypttext;
}


public String AS_DES_decry(String msg,int begin,int end) throws UnsupportedEncodingException{
	msg=msg.substring(begin, end+1);
	
	
	

	DES des=new DES();

	System.out.print("解密后的结果是：");
	//解密
	String decrypttext=des.dec(msg,Ktgs);
	System.out.println(decrypttext);
	return decrypttext;
	
}

	
	

}
