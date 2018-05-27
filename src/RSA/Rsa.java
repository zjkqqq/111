package RSA;

import java.util.Random;

import java.util.ArrayList;
import java.math.*;

public class Rsa {
	
	//private static BigInteger MMAX= BigInteger.probablePrime(300, new Random());
	//private static boolean[] prime_array;
	public static ArrayList<BigInteger> arraylist;
	//private static boolean[] prime_array1;
	
	public static BigInteger n;
	public static BigInteger e;
	private static BigInteger d;

	static BigInteger x,y;
//	private static void init() {
//		prime_array=cal_prime(MMAX);
//	}
	
	public BigInteger get_d() {
		return this.d;
	}
	
	
	
	
	
	public static void begin() {
		//init();
		BigInteger p=BigInteger.probablePrime(300, new Random());
		BigInteger q=BigInteger.probablePrime(300, new Random());
	//	Utils ut=new Utils();
		//BigInteger p=ut.getPrime(300);
		//BigInteger q=ut.getPrime(300);
		
		n=p.multiply(q);//��Կ����Կ
		BigInteger oula_n=p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
		
		e=Cal_prime(oula_n);//������Կ
		
		d=cal(e,oula_n);//˽����Կ
		
		
		
	}
	
	
	private static BigInteger find_d(BigInteger oula_n) {
		d=BigInteger.probablePrime(300, new Random());
		BigInteger mid=d.multiply(e);
		
		if((mid.remainder(oula_n)).equals(BigInteger.valueOf(1))) {
			return d;
		}else {
			d=d.add(BigInteger.valueOf(1));
			mid=d.multiply(e);
        	while(!mid.remainder(oula_n).equals(BigInteger.valueOf(1))) {
        		d=d.add(BigInteger.valueOf(1));
        		mid=d.multiply(e);
        		
        	}
		}
		
		return d;
	}
	
	
	
	//ŷ�������չ�㷨
    public static BigInteger ex_gcd(BigInteger a,BigInteger b){
        if(b.intValue()==0){
            x=new BigInteger("1");
            y=new BigInteger("0");
            return a;
        }
        BigInteger ans=ex_gcd(b,a.mod(b));
        BigInteger temp=x;
        x=y;
        y=temp.subtract(a.divide(b).multiply(y));
        return ans;
        
    }
    
    //��ģ 
    public static BigInteger cal(BigInteger a,BigInteger k){
        BigInteger gcd=ex_gcd(a,k);
        if(BigInteger.ONE.mod(gcd).intValue()!=0){
            return new BigInteger("-1");
        }
        //��������ֻ��˷���Ԫ ��������ʹ��BigInteger.One,ʵ���������Ҫ�������Զഫ��һ������,��ʾģ��ֵ����������
        x=x.multiply(BigInteger.ONE.divide(gcd));
        k=k.abs();
        BigInteger ans=x.mod(k);
        if(ans.compareTo(BigInteger.ZERO)<0) ans=ans.add(k);
        return ans;
        
    }
	
//	private static BigInteger Cal_prime() {//�ҳ�һ����Χ�ڵ�����
//		
//		BigInteger p;
//		Random rand=new Random();
//        p=(int)(Math.random()*MMAX);       //  ����0-100�������
//        if(prime_array[p]==true) {
//        	return p;
//        }else {
//        	p=rand.nextInt(MMAX); 
//        	while(prime_array[p]==false) {
//        		p=rand.nextInt(MMAX);
//        	}
//        }
//                   
//        System.out.println("p1:"+p); 
//		
//		
//		return p;
//	}
	
	
	
	private static BigInteger Cal_prime(BigInteger m) {
		BigInteger be=BigInteger.valueOf(2);
		while(!check1(be,m)) {
			be=be.add(BigInteger.valueOf(1));
			System.out.println(be);
		}
		
		return be;
	}
	
	
   
	private static boolean check1(BigInteger m,BigInteger n1) {//�ҳ�һ����Χ�ڵ�����
		
		int rr=m.compareTo(n1);
		if(rr==1) {
			return check2(m,n1);
		}
		else {
			return check2(n1,m);
		}
 
		
	}
	
	 public static boolean check2(BigInteger max, BigInteger min)
	  {
	  BigInteger mo = max.remainder(min) ;
	  if (mo.equals(BigInteger.valueOf(0))) {
	  return min.equals(BigInteger.valueOf(1)) ? true : false;
	  }
	  else {
	  return check2(min, mo);
	  }
	  }
	
	
	
	private static boolean[] cal_prime(int Nmax) {
		
		boolean[] isPrime=new boolean[Nmax+1];
		for(int i=3;i<=Nmax;i+=2) {
			isPrime[i]=true;
		}
			isPrime[2]=true;
		for(int i=3;i<=Math.sqrt(Nmax);i+=2) {
				
			if(isPrime[i]) {
				int j=i*i;
				int n=j;
				while(j<=Nmax) {
					isPrime[j]=false;
					while(Nmax/j>=i) {
						isPrime[j*=i]=false;
					}
					n+=2;
					while(!isPrime[n]) {
						n+=2;
					}
					j=i*n;
				}
			}
			
		}
		
		return isPrime;
	}
	
	public String trans(String mid[]) {//���ַ�������������ַ���
		
		String result="";
		
		int len=mid.length;
		
		for(int i=0;i<len;i++) {
			result=result+mid[i];
		}
		
		return result;
		
	}
	
public  ArrayList<BigInteger> encry_msg(String str_orignal) {
		
		int num=str_orignal.length();
		ArrayList<BigInteger> arrayList = new ArrayList<BigInteger>();
		for(int i=0;i<num;i++) {
			
			BigInteger mid_num=BigInteger.valueOf((int)str_orignal.charAt(i));		
			BigInteger mid1=mid_num;
			 BigInteger results =mid1.modPow(e, n);		
			arrayList.add(results);	
		}
		
		
		return arrayList;
	}

public  String encry_msg(String str_orignal,BigInteger mide,BigInteger
		 midn) {
	
	int num=str_orignal.length();
	ArrayList<BigInteger> arrayList = new ArrayList<BigInteger>();
	for(int i=0;i<num;i++) {
		
		BigInteger mid_num=BigInteger.valueOf((int)str_orignal.charAt(i));		
		BigInteger mid1=mid_num;
		 BigInteger results =mid1.modPow(mide, midn);		
		arrayList.add(results);	
	}
	
	String encry="";
	String mid[]=new String[arrayList.size()];
	
	for(int i=0;i<arrayList.size();i++) {
		if(i!=(arrayList.size()-1)) {
			mid[i]=arrayList.get(i).toString()+"&";
		}else {
			mid[i]=arrayList.get(i).toString();
		}
		
	}
	encry=trans(mid);
	return encry;
}
	
	
public  ArrayList<BigInteger> encry_msg_sign(String str_orignal) {
		
		int num=str_orignal.length();
		ArrayList<BigInteger> arrayList = new ArrayList<BigInteger>();
		for(int i=0;i<num;i++) {
			
			BigInteger mid_num=BigInteger.valueOf((int)str_orignal.charAt(i));
			BigInteger mid1=mid_num;
			
			 BigInteger results =mid1.modPow(d, n);

			arrayList.add(results);
			
		}
		return arrayList;
	}


public  ArrayList<BigInteger> encry_msg_sign(String str_orignal,BigInteger midd,BigInteger
		 midn) {
	
	int num=str_orignal.length();
	ArrayList<BigInteger> arrayList = new ArrayList<BigInteger>();
	for(int i=0;i<num;i++) {
		
		BigInteger mid_num=BigInteger.valueOf((int)str_orignal.charAt(i));
		BigInteger mid1=mid_num;
		
		 BigInteger results =mid1.modPow(midd, midn);

		arrayList.add(results);
		
	}
	return arrayList;
}



	
public String decry_msg(ArrayList<BigInteger> ary) {
		
	    String str="";	  
	    for(BigInteger number : ary){
	    	BigInteger mid1=number;       
	       BigInteger results =mid1.modPow(d, n);    
	       int result=results.intValue();
	    	char c=(char)result;
	    	str=str+c;
        }
	    
	    System.out.println(str+ "  ************");
	    
	    return str;
		
	}


public String decry_msg(String msg,BigInteger midd,BigInteger midn) {
	
	String mid[]=msg.split("&");
	int len = mid.length;
	ArrayList<BigInteger> ary=new ArrayList<BigInteger>();
    for(int j=0;j<len;j++) {
	   // int i=Integer.parseInt(mid[j]);
		BigInteger h=new BigInteger(mid[j]);
		
		ary.add(h);
	}
	
    String str="";	  
    for(BigInteger number : ary){
    	BigInteger mid1=number;       
       BigInteger results =mid1.modPow(midd, midn);    
       int result=results.intValue();
    	char c=(char)result;
    	str=str+c;
    }
    
    System.out.println(str+ "  ************");
    
    return str;
	
}


public ArrayList<BigInteger> digital_sign(String msg) {//����ǩ��
	
	int mid=msg.hashCode();
	BigInteger mid_big=BigInteger.valueOf(mid);
	String mid_trans=Integer.toString(mid);

	ArrayList<BigInteger> array_sign=encry_msg_sign(mid_trans);
     
    return array_sign;
	
}

public boolean confirm_sign(ArrayList<BigInteger> array_sign,String msg_orignal) {//��֤
	
	int confir=msg_orignal.hashCode();
	String confir_str=Integer.toString(confir);
	
	 String str="";
	   
	    for(BigInteger number : array_sign){
	    	BigInteger mid1=number;
	      
	       BigInteger results =mid1.modPow(e, n);
	        int result=results.intValue();
	        System.out.println(result+ "  ************&&&&&&&&&");
	    	char c=(char)result;
	    	str=str+c;
     }
	    
	    System.out.println(str+ "  ************");
	   
	    
    if(confir_str.equals(str))return true;
    else return true;
    
    
	
}


}


