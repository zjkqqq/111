package DES_METHOD;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class test {
	 public static String genRandomNum(){  
	     int  maxNum = 36;  
	     int i;  
	     int count = 0;  
	     char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
	       'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
	       'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
	     StringBuffer pwd = new StringBuffer("");  
	     Random r = new Random();  
	     while(count < 8){  
	      i = Math.abs(r.nextInt(maxNum));     
	      if (i >= 0 && i < str.length) {  
	       pwd.append(str[i]);  
	       count ++;  
	      }  
	     }  
	     return pwd.toString();  
	   }  
	public static void main(String[]args) throws UnsupportedEncodingException
	{  
		test c=new test();
		Scanner in = new Scanner(System.in);
		System.out.println("������Ҫ���ܵ�����:");
	    //�������
	    String s=in.nextLine();
	    //�����Կ
	    String s1=genRandomNum();
		DES des=new DES();
		//����
		String encrypttext=des.enc(s,s1);
		System.out.println("���ܺ�Ľ���ǣ�");
		System.out.println(encrypttext);
		System.out.print("���ܺ�Ľ���ǣ�");
		//����
		String decrypttext=des.dec(encrypttext,s1);
		System.out.println(decrypttext);
	}
}
